package org.hisp.metadata.services;

import com.auth0.Auth0User;
import com.auth0.SessionUtils;
import org.hisp.metadata.api.domain.User;
import org.hisp.metadata.api.services.UserService;
import org.hisp.metadata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zubair on 07.02.17.
 */
@Service
public class DefaultUserService implements UserService
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private UserRepository repository;

    @Override
    public User getCurrentUser()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Auth0User currentAuth0User = (Auth0User) SessionUtils.getAuth0User( request );

        return getUserByEmail( currentAuth0User.getEmail() );
    }

    @Override
    public User getUserByEmail( String email )
    {
        return repository.getUserByEmail( email );
    }

    @Override
    public void addUser( User user )
    {
        repository.save( user );
    }
}
