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
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class RoutingController implements ErrorController, WebMvcConfigurer {

    private static final String MAIN_APPLICATION_PATH = "/";
    private static final String LOGIN_VALIDATOR_PATH = "/validateLogin";
    private static final String ERROR_PATH = "/error";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(MAIN_APPLICATION_PATH)
                .setViewName("forward:/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>" +
                        "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }

    @RequestMapping(value = LOGIN_VALIDATOR_PATH, method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.OK)
    public String validateLogin(HttpServletRequest request){
        String username   = request.getParameter ("username");
        String password = request.getParameter ("password");
        return username + password;
    }
}
