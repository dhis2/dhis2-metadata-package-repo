package org.hisp.metadata.configuration;

import org.hisp.metadata.repositories.MetaDataPackageRepository;
import org.hisp.metadata.repositories.PackageVersionRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by zubair on 03.02.17.
 */
@Configuration
@EnableJpaRepositories( basePackageClasses = { PackageVersionRepository.class, MetaDataPackageRepository.class })
public class WebApplicationBusinessLogicConfigurer
{
}
