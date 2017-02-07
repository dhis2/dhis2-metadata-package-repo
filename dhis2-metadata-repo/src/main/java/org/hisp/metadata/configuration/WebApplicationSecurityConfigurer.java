package org.hisp.metadata.configuration;

import com.auth0.spring.security.mvc.Auth0Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by zubair on 03.02.17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class WebApplicationSecurityConfigurer extends Auth0Config
{
}
