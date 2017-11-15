package org.hisp.metadata.api.services;

import org.hisp.metadata.api.domain.PackageVersion;

/**
 * Created by zubair on 07.02.17.
 */
public interface PackageVersionService
{
    PackageVersion get( int id );

    PackageVersion get( String uid );

    void update( PackageVersion version );

    void delete( PackageVersion version );

    void save( PackageVersion version );
}
