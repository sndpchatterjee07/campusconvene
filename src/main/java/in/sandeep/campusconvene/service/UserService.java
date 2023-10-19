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

package in.sandeep.campusconvene.service;

import in.sandeep.campusconvene.model.Users;
import in.sandeep.campusconvene.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    /**
     * Find by user name list.
     *
     * @param username the username
     * @return the list of Users.
     */
    public List<Users> findByUserName(String username) {

        List<Users> usersList = userRepository.findByUsername(username);
        if (CollectionUtils.isEmpty (usersList)) {
            System.out.println ("NO USER FOUND");
            return new ArrayList<Users> ();
        }
        return usersList;
    }
}
