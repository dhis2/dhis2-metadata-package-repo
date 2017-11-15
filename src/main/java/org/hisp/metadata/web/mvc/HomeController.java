package org.hisp.metadata.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.metadata.api.domain.User;
import org.hisp.metadata.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zubair on 05.12.16.
 */

@Controller
public class HomeController
{
    private static final Log log = LogFactory.getLog( HomeController.class );

    @Autowired
    private UserService userService;

    @RequestMapping( value = "/*", method = RequestMethod.GET )
    public String loginPage( Model model )
    {
        return "home";
    }

    @PreAuthorize( "hasRole('ROLE_USER')" )
    @RequestMapping( value = "/user", method = RequestMethod.GET )
    public String userPage( Model model )
    {
        User user = userService.getCurrentUser();

        model.addAttribute( "user",user );

        return "user";
    }
}
