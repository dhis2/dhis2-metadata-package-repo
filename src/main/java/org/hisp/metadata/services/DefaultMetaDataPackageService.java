package org.hisp.metadata.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.metadata.api.domain.*;
import org.hisp.metadata.api.services.CurrentUserService;
import org.hisp.metadata.api.services.FileStorageService;
import org.hisp.metadata.api.services.MetaDataPackageService;
import org.hisp.metadata.repositories.MetaDataPackageRepository;
import org.hisp.metadata.utils.WebMessageException;
import org.hisp.metadata.utils.WebMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by zubair on 06.02.17.
 */

@Service
@Transactional
public class DefaultMetaDataPackageService implements MetaDataPackageService
{
    private static final Log log = LogFactory.getLog( DefaultMetaDataPackageService.class );

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MetaDataPackageRepository repository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CurrentUserService currentUserService;

    // -------------------------------------------------------------------------
    // Implementation methods
    // -------------------------------------------------------------------------

    @Override
    public MetaDataPackage get( int id )
    {
        return repository.findOne( id );
    }

    @Override
    public MetaDataPackage get( String uid )
    {
        return repository.getPackageByUid( uid );
    }

    @Override
    public void update( MetaDataPackage metaDataPackage )
    {
        repository.save( metaDataPackage );
    }

    @Override
    public void delete( MetaDataPackage metaDataPackage )
    {
        repository.delete( metaDataPackage );
    }

    @Override
    public void setPackageApproval( MetaDataPackage metaDataPackage, PackageStatus status )
    {
        metaDataPackage.setStatus( status );

        repository.save( metaDataPackage );

        log.info( String.format( "Status changed for metadata package: %s" + metaDataPackage.getName() ) );
    }

    @Override
    public void addVersionToPackage( MetaDataPackage metaDataPackage, PackageVersion version, MultipartFile file ) throws WebMessageException
    {
        FileUploadStatus status = fileStorageService.uploadFile( file );

        if ( !status.isUploaded() )
        {
            throw new WebMessageException( WebMessageUtils.conflict( "Version uploading failed" ) );
        }

        version.setVersionUrl( status.getDownloadUrl() );
        metaDataPackage.getVersions().add( version );

        repository.save( metaDataPackage );

        log.info( String.format( "Version with id: %s added to package: %s", version.getUid(),metaDataPackage.getName() ) );
    }

    @Override
    public void addResourceToPackage( MetaDataPackage metaDataPackage, Resource resource, MultipartFile file ) throws WebMessageException
    {
        FileUploadStatus status = fileStorageService.uploadFile( file );

        if ( !status.isUploaded() )
        {
            throw new WebMessageException( WebMessageUtils.conflict( "Resource uploading failed" ) );
        }

        resource.setResourceUrl( status.getDownloadUrl() );
        metaDataPackage.getResources().add( resource );

        repository.save( metaDataPackage );

        log.info( String.format( "Resource with id: %s add to package: %s", resource.getUid(), metaDataPackage.getName() ) );
    }

    @Override
    public void removeVersionFromPackage( MetaDataPackage metaDataPackage, PackageVersion version )
    {
        String resourcekey = getKeyFromResourceUrl( version.getVersionUrl() );

        fileStorageService.deleteFile( resourcekey );

        metaDataPackage.getVersions().remove( version );

        repository.save( metaDataPackage );

        log.info( String.format( "Version: %s has been removed from metadata package: %s", version.getVersion(), metaDataPackage.getName() ) );
    }

    @Override
    public void uploadPackage( MetaDataPackage metaDataPackage, MultipartFile file ) throws WebMessageException
    {
        FileUploadStatus status = fileStorageService.uploadFile(  file );

        if ( !status.isUploaded() )
        {
            throw new WebMessageException( WebMessageUtils.conflict( "File uploading failed" ) );
        }

        metaDataPackage.setOwner( currentUserService.getCurrentUserId() );

        metaDataPackage.getVersions().stream().forEach( v -> v.setVersionUrl( status.getDownloadUrl() ) );

        MetaDataPackage metaData = repository.save( metaDataPackage );

        log.info( String.format( "MetaData package uploaded with id: %s", metaData.getUid() ) );
    }

    @Override
    public void removePackage( MetaDataPackage metaDataPackage )
    {
        repository.delete( metaDataPackage );

        log.info( String.format( "MetaData package: %s has been removed", metaDataPackage.getName() ) );
    }

    @Override
    public List<MetaDataPackage> getAll()
    {
        List<MetaDataPackage> metaDataPackages = StreamSupport.stream( repository.findAll().spliterator(), false )
            .collect( Collectors.toList() );

        return metaDataPackages;
    }

    @Override
    public void save(MetaDataPackage metaDataPackage)
    {
        repository.save( metaDataPackage );

        log.info( String.format( "MetaData package saved with id: %s", metaDataPackage.getUid() ) );
    }

    @Override
    public List<MetaDataPackage> getPackagesByStatus( PackageStatus status )
    {
        return repository.getPackagesByStatus( status );
    }

    // -------------------------------------------------------------------------
    // Supportive methods
    // -------------------------------------------------------------------------

    private String getKeyFromResourceUrl( String resourceUrl )
    {
        return StringUtils.substringAfterLast( resourceUrl, "/").trim();
    }
}
