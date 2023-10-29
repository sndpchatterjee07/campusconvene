/*
 *
 *  *     Copyright 2023-2024 Sandeep Chatterjee @ https://sandeepc.in/
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package in.sandeep.campusconvene.controller;

import in.sandeep.campusconvene.model.Users;
import in.sandeep.campusconvene.repository.UserDetails;
import in.sandeep.campusconvene.repository.UserRepository;
import in.sandeep.campusconvene.service.UserService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * The type RoutingController.
 *
 * @author sandeep
 * @version 1.0
 */
@RestController
public class RoutingController implements ErrorController {

    private static final String MAIN_APPLICATION_PATH = "/";
    private static final String AUTHENTICATE_USER = "/authenticateUser";
    private static final String ERROR_PATH = "/error";

    private final ModelAndView modelAndView = new ModelAndView ();

    private UserDetails userDetails;

    @Autowired
    private UserService userService;

    /**
     * Gets Login Page.
     *
     * @return the Login Page
     */
    @RequestMapping(value = MAIN_APPLICATION_PATH, method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        modelAndView.setViewName ("login");
        modelAndView.addObject ("userInfo", new Users ());
        return modelAndView;
    }

    /**
     * Handle Any Errors
     *
     * @return the Error Page
     */
    @RequestMapping(value = ERROR_PATH)
    public ModelAndView handleError() {
        modelAndView.setViewName ("error_page");
        return modelAndView;
    }


    /**
     * Authenticate User.
     *
     * @param userInfo the userInfo
     * @return the model and view
     */
    @RequestMapping(value = AUTHENTICATE_USER, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView authenticateUser(@ModelAttribute Users userInfo) {
        try {
            userDetails = (UserDetails) userService.loadUserByUsername (userInfo.getUsername ());
            if (userDetails != null) { // AUTHENTICATION SUCCESSFUL
                modelAndView.setViewName ("super_admin_home");
                modelAndView.addObject ("userInfo", userInfo);
            } else { // AUTHENTICATION FAILED
                modelAndView.setViewName ("login");
                modelAndView.addObject ("error", true);
                modelAndView.addObject ("errorMessage", "Login Failed! Invalid Username and/or Password");
            }
        } catch (UsernameNotFoundException usernameNotFoundException) { // AUTHENTICATION FAILED
            modelAndView.setViewName ("login");
            modelAndView.addObject ("error", true);
            modelAndView.addObject ("errorMessage", "Login Failed! Invalid Username and/or Password");
            return modelAndView;
        }
        return modelAndView;
    }
}
