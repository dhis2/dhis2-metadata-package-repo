package org.hisp.metadata.web.mvc;

import com.auth0.Auth0User;
import com.auth0.SessionUtils;
import org.hisp.metadata.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;


/**
 * Created by zubair on 09.12.16.
 */
@Controller
@RequestMapping ( value = "/manager" )
public class ManagerController
{
    @Autowired
    private UserService userService;

    @PreAuthorize( "hasRole('ROLE_MANAGER')" )
    @RequestMapping( method = RequestMethod.GET)
    protected String home( final Map<String, Object> model, final HttpServletRequest request, final Principal principal )
    {
        final Auth0User user = SessionUtils.getAuth0User( request );
        model.put("user", user);

        return "manager";
    }
}
