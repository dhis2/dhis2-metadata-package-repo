package org.hisp.metadata.repositories;

import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageVersion;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zubair on 07.02.17.
 */
public interface PackageVersionRepository
        extends CrudRepository<PackageVersion, Integer>
{
    PackageVersion getVersionByUid( String uid );
}
