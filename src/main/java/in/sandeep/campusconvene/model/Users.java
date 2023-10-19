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

package in.sandeep.campusconvene.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * The type Users.
 *
 * @author sandeep
 * @version 1.0
 */
@Document(collection = "Users")
public final class Users {


    private ObjectId id;

    private String username;

    private String email;

    private String password;

    private enum role {
        /**
         * Student role.
         */
        student,
        /**
         * Faculty role.
         */
        faculty,
        /**
         * Registrar role.
         */
        registrar,
        /**
         * Dean role.
         */
        dean,
        /**
         * Deputy director role.
         */
        deputy_director,
        /**
         * Director role.
         */
        director,
        /**
         * Chairperson role.
         */
        chairperson,
        /**
         * Super admin role.
         */
        super_admin
    }

    private String fullName;

    private List<String> contactInfo;


    // ACCESSORS & MUTATORS.


    /**
     * Gets id.
     *
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets contact info.
     *
     * @return the contact info
     */
    public List<String> getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets contact info.
     *
     * @param contactInfo the contact info
     */
    public void setContactInfo(List<String> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
