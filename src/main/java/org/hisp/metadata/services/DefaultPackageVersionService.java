package org.hisp.metadata.services;

import org.hisp.metadata.api.domain.PackageVersion;
import org.hisp.metadata.api.services.PackageVersionService;
import org.hisp.metadata.repositories.PackageVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zubair on 07.02.17.
 */
@Service
@Transactional
public class DefaultPackageVersionService implements PackageVersionService
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private PackageVersionRepository repository;

    // -------------------------------------------------------------------------
    // Implementation methods
    // -------------------------------------------------------------------------

    @Override
    public PackageVersion get( int id )
    {
        return repository.findOne( id );
    }

    @Override
    public PackageVersion get( String uid )
    {
        return repository.getVersionByUid( uid );
    }

    @Override
    public void update( PackageVersion version )
    {
        repository.save( version );
    }

    @Override
    public void delete( PackageVersion version )
    {
        repository.delete( version );
    }

    @Override
    public void save( PackageVersion version )
    {
        repository.save( version );
    }
}
