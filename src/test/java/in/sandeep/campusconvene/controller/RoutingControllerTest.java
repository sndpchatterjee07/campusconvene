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

package in.sandeep.campusconvene.controller;

import in.sandeep.campusconvene.model.Users;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


@SpringBootTest
@AutoConfigureMockMvc
public class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // TEST CASES FOR getLoginPage METHOD.

    /**
     * 1. Test get login page returns HTTP Status 200 ok.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPageReturns200OK() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.get ("/"))
                .andExpect (MockMvcResultMatchers.status ().isOk ());
    }

    /**
     * 2. Test get login page returns correct view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPageReturnsCorrectView() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.get ("/"))
                .andExpect (MockMvcResultMatchers.view ().name ("login"));
    }

    /**
     * 3. Test get login page returns NotNull model and view.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPageReturnsNotNullModelAndView() throws Exception {
        ModelAndView modelAndView = mockMvc.perform (MockMvcRequestBuilders.get ("/"))
                .andReturn ()
                .getModelAndView ();

        assertNotNull (modelAndView);
        assertEquals ("login", modelAndView.getViewName ());
    }


    /**
     * 4. Test get login page returns userInfo model attribute.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPageReturnsUserInfoModelAttribute() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.get ("/"))
                .andExpect (model ().attributeExists ("userInfo"));
    }


    /**
     * 5. Test get login page returns userInfo model attribute of type Users.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPageReturnsUserInfoModelAttributeofTypeUsers() throws Exception {
        mockMvc.perform (MockMvcRequestBuilders.get ("/"))
                .andExpect (model ().attribute ("userInfo", instanceOf (Users.class)));
    }
}