package org.hisp.metadata.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@ComponentScan ( basePackages = { "org.hisp.metadata","com.auth0.spring.security.mvc" } )
@PropertySource( "classpath:application.properties" )
@ImportResource( "classpath:/META-INF/beans.xml" )
public class Dhis2MetaDataRepositoryApplication
{
	public static void main( String[] args )
	{
		SpringApplication.run( Dhis2MetaDataRepositoryApplication.class, args );
	}
}
