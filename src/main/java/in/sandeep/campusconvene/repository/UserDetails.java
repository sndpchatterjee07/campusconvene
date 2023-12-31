/*
 *
 *  *
 *  *  *     Copyright 2023-2024 Sandeep Chatterjee @ https://sandeepc.in/
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package in.sandeep.campusconvene.repository;


import in.sandeep.campusconvene.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


/**
 * The type UserDetails.
 *
 * @author sandeep
 * @version 1.0
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final Users users;

    public UserDetails(Users users) {
        super ();
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton (new SimpleGrantedAuthority (users.getRole ()));
    }


    @Override
    public String getPassword() {
        return users.getPassword ();
    }


    @Override
    public String getUsername() {
        return users.getUsername ();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
