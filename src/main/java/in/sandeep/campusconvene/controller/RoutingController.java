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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private static final String LOGIN_VALIDATOR_PATH = "/validateLogin";
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
     * Authenticate the User.
     *
     * @param userInfo the user info
     * @return the model and view
     */
    @RequestMapping(value = LOGIN_VALIDATOR_PATH, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView validateLogin(@ModelAttribute Users userInfo) {
        modelAndView.addObject ("userInfo", userInfo);
        try {
            userDetails = (UserDetails) userService.loadUserByUsername (userInfo.getUsername ());
            modelAndView.setViewName ("super_admin_home"); // REDIRECT TO USER HOME ON SUCCESSFUL AUTHENTICATION.
        } catch (UsernameNotFoundException usernameNotFoundException) {
            modelAndView.setViewName ("login");
        }
        return modelAndView;
    }
}
