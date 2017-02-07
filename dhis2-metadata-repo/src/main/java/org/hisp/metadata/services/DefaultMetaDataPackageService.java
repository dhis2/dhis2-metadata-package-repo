package org.hisp.metadata.services;

import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageStatus;
import org.hisp.metadata.api.domain.PackageVersion;
import org.hisp.metadata.api.services.MetaDataPackageService;
import org.hisp.metadata.repositories.MetaDataPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zubair on 06.02.17.
 */
@Service
@Transactional
public class DefaultMetaDataPackageService implements MetaDataPackageService
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MetaDataPackageRepository repository;

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
    }

    @Override
    public void addVersionToPackage( MetaDataPackage metaDataPackage, PackageVersion version, MultipartFile file )
    {

    }

    @Override
    public void removeVersionFromPackage( MetaDataPackage metaDataPackage, PackageVersion version )
    {

    }

    @Override
    public void uploadPackage( MetaDataPackage metaDataPackage )
    {

    }

    @Override
    public void removePackage( MetaDataPackage metaDataPackage )
    {
        repository.delete( metaDataPackage );
    }

    @Override
    public void getAll()
    {
        repository.findAll();
    }

    @Override
    public void save(MetaDataPackage metaDataPackage)
    {
        repository.save( metaDataPackage );
    }
}
