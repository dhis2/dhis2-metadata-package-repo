package org.hisp.metadata.repositories;

import org.hisp.metadata.api.domain.MetaDataPackage;
import org.hisp.metadata.api.domain.PackageStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zubair on 06.02.17.
 */
public interface MetaDataPackageRepository
        extends CrudRepository<MetaDataPackage, Integer>
{
    MetaDataPackage getPackageByUid( String uid );

    List<MetaDataPackage> getPackagesByStatus( PackageStatus status );
}
