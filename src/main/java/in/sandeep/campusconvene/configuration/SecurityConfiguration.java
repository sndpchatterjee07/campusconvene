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

package in.sandeep.campusconvene.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

/**
 * The type SecurityConfiguration.
 *
 * @author sandeep
 * @version 1.0
 * @see @link{https://spring.io/guides/gs/securing-web/}
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ClearSiteDataHeaderWriter.Directive[] SOURCE =
            {CACHE, COOKIES, STORAGE, EXECUTION_CONTEXTS};


    /**
     * Campusconvene SecurityFilterChain.
     *
     * @param httpSecurity the http security
     * @return the SecurityFilterChain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain campusConveneSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests ((requests) -> requests
                        .requestMatchers ("/", "/validateLogin","/assets/**").permitAll ()
                        .anyRequest ().authenticated ()
                )
                .formLogin ((form) -> form
                        .loginPage ("/")
                        .permitAll ()
                )
                .logout ((logout) -> logout
                                .logoutSuccessUrl ("/")
                                .addLogoutHandler (new HeaderWriterLogoutHandler (new ClearSiteDataHeaderWriter (SOURCE)))
                                .addLogoutHandler (new SecurityContextLogoutHandler ())
                                .invalidateHttpSession (true)
                        /*.permitAll ()*/);
        return httpSecurity.build ();
    }
}
