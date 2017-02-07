package org.hisp.metadata.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageStatus;
import org.hisp.metadata.api.domain.PackageVersion;
import org.hisp.metadata.api.services.MetaDataPackageService;
import org.hisp.metadata.api.services.PackageVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zubair on 06.02.17.
 */
@RestController
@RequestMapping( value = "/api/metadatapackages")
public class MetaDataPackageController
{
    private static Log log = LogFactory.getLog( MetaDataPackageController.class );

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    MetaDataPackageService metaDataPackageService;

    @Autowired
    PackageVersionService packageVersionService;

    // -------------------------------------------------------------------------
    // GET
    // -------------------------------------------------------------------------

    @RequestMapping( method = RequestMethod.GET )
    public void getIndex( HttpServletRequest request, HttpServletResponse response )
    {

        MetaDataPackage metaDataPackage = new MetaDataPackage();

        PackageVersion version = new PackageVersion();
        version.setAutoFields();
        version.setVersionUrl("http://localhost");
        version.setVersion("1.0");

        metaDataPackage.setAutoFields();
        metaDataPackage.getVersions().add( version );
        metaDataPackage.setDescription(" Pakistan package ");
        metaDataPackage.setStatus( PackageStatus.PENDING );

        String uid = metaDataPackage.getUid();

        log.info("Metadata package created with uid " + uid);

        metaDataPackageService.save( metaDataPackage );

        log.info(" Fetching ................... ");

        log.info( metaDataPackageService.get( uid  ));
    }
}
