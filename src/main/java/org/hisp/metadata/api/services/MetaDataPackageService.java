package org.hisp.metadata.api.services;

import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageStatus;
import org.hisp.metadata.api.domain.PackageVersion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zubair on 03.02.17.
 */
public interface MetaDataPackageService
{
    MetaDataPackage get( int id );

    MetaDataPackage get( String uid );

    List<MetaDataPackage> getPackagesByStatus( PackageStatus status );

    void update( MetaDataPackage metaDataPackage );

    void delete( MetaDataPackage metaDataPackage );

    void setPackageApproval( MetaDataPackage metaDataPackage, PackageStatus status );

    void addVersionToPackage( MetaDataPackage metaDataPackage, PackageVersion version, MultipartFile file);

    void removeVersionFromPackage( MetaDataPackage metaDataPackage, PackageVersion version );

    void uploadPackage( MetaDataPackage metaDataPackage );

    void removePackage( MetaDataPackage metaDataPackage );

    void save( MetaDataPackage metaDataPackage );

    List<MetaDataPackage> getAll();
}
