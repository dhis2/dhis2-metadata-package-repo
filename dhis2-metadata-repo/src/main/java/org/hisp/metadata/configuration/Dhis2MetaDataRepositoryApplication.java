package org.hisp.metadata.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Dhis2MetaDataRepositoryApplication
{
	public static void main( String[] args )
	{
		SpringApplication.run( Dhis2MetaDataRepositoryApplication.class, args );
	}
}
