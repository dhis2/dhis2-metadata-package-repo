package org.hisp.metadata.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.metadata.api.domain.FileUploadStatus;
import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageStatus;
import org.hisp.metadata.api.domain.PackageVersion;
import org.hisp.metadata.api.services.CurrentUserService;
import org.hisp.metadata.api.services.MetaDataPackageService;
import org.hisp.metadata.api.services.PackageVersionService;
import org.hisp.metadata.api.services.RenderService;
import org.hisp.metadata.utils.WebMessageException;
import org.hisp.metadata.utils.WebMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zubair on 06.02.17.
 */
@RestController
@RequestMapping( value = "/api/metadataPackages")
public class MetaDataPackageController
{
    private static final String NOT_FOUND = "No Package found with id: ";

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MetaDataPackageService metaDataPackageService;

    @Autowired
    private PackageVersionService packageVersionService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private RenderService renderService;

    // -------------------------------------------------------------------------
    // GET
    // -------------------------------------------------------------------------

    @RequestMapping( method = RequestMethod.GET )
    public void getApprovedPackages( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        renderService.toJson( response.getOutputStream(), metaDataPackageService.getPackagesByStatus( PackageStatus.APPROVED ));
    }

    @PreAuthorize( "hasRole('ROLE_MANAGER')" )
    @RequestMapping( value = "/all", method = RequestMethod.GET )
    public void getAllPackages( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        renderService.toJson( response.getOutputStream(), metaDataPackageService.getAll() );
    }

    @PreAuthorize( "isAuthenticated()" )
    @RequestMapping( value = "/{uid}", method = RequestMethod.GET )
    public void getPackage( @PathVariable( value = "uid" ) String packageUid,
                        HttpServletRequest request, HttpServletResponse response ) throws IOException, WebMessageException
    {
        MetaDataPackage metaDataPackage = metaDataPackageService.get( packageUid );

        if ( metaDataPackage == null )
        {
            throw new WebMessageException( WebMessageUtils.notFound( NOT_FOUND + packageUid ) );
        }

        if ( !PackageStatus.APPROVED.equals( metaDataPackage.getStatus()) )
        {
            decideAccess( metaDataPackage );
        }

        response.setContentType( "application/json" );

        renderService.toJson( response.getOutputStream(), metaDataPackage );
    }

    // -------------------------------------------------------------------------
    // POST
    // -------------------------------------------------------------------------

    @PreAuthorize( "isAuthenticated()" )
    @RequestMapping( method = RequestMethod.POST )
    public void uploadPackage( @RequestPart( name = "file" ) MultipartFile file,
                               @RequestPart( name = "metadataPackage" ) MetaDataPackage metadataPackage ,HttpServletResponse response, HttpServletRequest request )
                               throws IOException, WebMessageException
    {
        metaDataPackageService.uploadPackage( metadataPackage, file );

        renderService.renderCreated( response, request, "Package Uploaded");
    }

    @PreAuthorize( "isAuthenticated()" )
    @RequestMapping ( value = "/{uid}/version", method = RequestMethod.POST )
    public void addVersionToPackage( @RequestPart( name = "file" ) MultipartFile file,
                                     @RequestPart( name = "version" ) PackageVersion version, @RequestPart( "uid" ) String packageUid,
                                     HttpServletResponse response, HttpServletRequest request )
                                     throws IOException, WebMessageException
    {
        MetaDataPackage metaDataPackage = metaDataPackageService.get( packageUid );

        if ( metaDataPackage == null )
        {
            throw new WebMessageException( WebMessageUtils.notFound( NOT_FOUND + packageUid ) );
        }

        decideAccess( metaDataPackage );

        metaDataPackageService.addVersionToPackage( metaDataPackage, version, file);

        renderService.renderCreated( response, request, "Package version added" );
    }

    @PreAuthorize( "hasRole('ROLE_MANAGER')" )
    @RequestMapping ( value = "/{uid}/approval", method = RequestMethod.POST )
    public void approvePackage( @PathVariable( "uid" ) String packageUid,
                                @RequestParam( name = "status" ) PackageStatus status,
                                HttpServletResponse response, HttpServletRequest request )
            throws IOException, WebMessageException
    {
        MetaDataPackage metaDataPackage = metaDataPackageService.get( packageUid );

        if ( metaDataPackage == null )
        {
            throw new WebMessageException( WebMessageUtils.notFound( NOT_FOUND + packageUid ) );
        }

        metaDataPackageService.setPackageApproval( metaDataPackage, status );

        renderService.renderOk( response, request, "Status changed for package: " + metaDataPackage.getName() );
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @PreAuthorize( "isAuthenticated()" )
    @RequestMapping ( value = "/{uid}", method = RequestMethod.DELETE )
    public void deletePackage( @PathVariable( "uid" ) String packageUid,
                           HttpServletResponse response, HttpServletRequest request )
                           throws IOException, WebMessageException
    {
        MetaDataPackage metaDataPackage = metaDataPackageService.get( packageUid );

        if ( metaDataPackage == null )
        {
            throw new WebMessageException( WebMessageUtils.notFound( NOT_FOUND + packageUid ) );
        }

        decideAccess( metaDataPackage );

        metaDataPackageService.delete( metaDataPackage );

        renderService.renderOk( response, request, "Package Removed" );
    }

    @PreAuthorize( "isAuthenticated()" )
    @RequestMapping ( value = "/{uid}/version/{ruid}", method = RequestMethod.DELETE )
    public void removeVersionFromPackage( @PathVariable( "uid" ) String packageUid,
                                      @PathVariable( "vuid" ) String versionUid,
                                      HttpServletResponse response, HttpServletRequest request )
                                      throws IOException, WebMessageException
    {
        MetaDataPackage metaDataPackage = metaDataPackageService.get( packageUid );

        PackageVersion version = packageVersionService.get( versionUid );

        if ( metaDataPackage == null || version == null )
        {
            throw new WebMessageException( WebMessageUtils.notFound( "Entities not found with given ids" ) );
        }

        decideAccess( metaDataPackage );

        metaDataPackageService.removeVersionFromPackage( metaDataPackage, version );

        renderService.renderOk( response, request, "Version Removed" );
    }

    private void decideAccess( MetaDataPackage metaDataPackage ) throws WebMessageException
    {
        if ( !currentUserService.getCurrentUserId().equals( metaDataPackage.getOwner() ) && !currentUserService.isManager() )
        {
            throw new WebMessageException( WebMessageUtils.denied( "Access denied" ) );
        }
    }
}
