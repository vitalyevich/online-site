package com.example.vitalyevich.onlinesite.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.User;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.ActionRepository;
import com.example.vitalyevich.onlinesite.repository.OrderRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import com.example.vitalyevich.onlinesite.repository.RoleRepository;
import com.example.vitalyevich.onlinesite.repository.UserPointRepository;
import com.example.vitalyevich.onlinesite.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @MockBean
    private ActionRepository actionRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private UserController userController;

    @MockBean
    private UserPointRepository userPointRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserController#addUser(Access, User, org.springframework.ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)}
     */
    @Test
    void testAddUser() throws Exception {
        User user = new User();
        user.setDateOfBirth(LocalDate.ofEpochDay(1L));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setInviteCode("Invite Code");
        user.setUserCode("User Code");

        Access access = new Access();
        access.setActive(true);
        access.setConfirmPassword("iloveyou");
        access.setId(1);
        access.setPassword("iloveyou");
        access.setPhone("4105551212");
        access.setRoles(new HashSet<>());
        access.setUser(user);
        when(this.accessRepository.findByPhone((String) any())).thenReturn(access);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-registration"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-registration"));
    }

    /**
     * Method under test: {@link UserController#addUser(Access, User, org.springframework.ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)}
     */
    @Test
    void testAddUser2() throws Exception {
        User user = new User();
        user.setDateOfBirth(LocalDate.ofEpochDay(1L));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setInviteCode("Invite Code");
        user.setUserCode("User Code");

        Access access = new Access();
        access.setActive(true);
        access.setConfirmPassword("iloveyou");
        access.setId(1);
        access.setPassword("iloveyou");
        access.setPhone("4105551212");
        access.setRoles(new HashSet<>());
        access.setUser(user);
        when(this.accessRepository.findByPhone((String) any())).thenReturn(access);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/registration");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-registration"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-registration"));
    }

    /**
     * Method under test: {@link UserController#authorization()}
     */
    @Test
    void testAuthorization() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authorization");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-authorization"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-authorization"));
    }

    /**
     * Method under test: {@link UserController#authorization()}
     */
    @Test
    void testAuthorization2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/authorization");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-authorization"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-authorization"));
    }

    /**
     * Method under test: {@link UserController#delivery()}
     */
    @Test
    void testDelivery() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#modal()}
     */
    @Test
    void testModal() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/modal");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/profile#blackout-modal"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile#blackout-modal"));
    }

    /**
     * Method under test: {@link UserController#modal()}
     */
    @Test
    void testModal2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/modal");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/profile#blackout-modal"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile#blackout-modal"));
    }

    /**
     * Method under test: {@link UserController#orders(org.springframework.ui.Model)}
     */
    @Test
    void testOrders() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#profile(org.springframework.ui.Model)}
     */
    @Test
    void testProfile() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#registration()}
     */
    @Test
    void testRegistration() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/registration");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-registration"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-registration"));
    }

    /**
     * Method under test: {@link UserController#registration()}
     */
    @Test
    void testRegistration2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/registration");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-registration"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-registration"));
    }

    /**
     * Method under test: {@link UserController#saveSetting(com.example.vitalyevich.onlinesite.model.Access, com.example.vitalyevich.onlinesite.model.User, org.springframework.ui.Model)}
     */
    @Test
    void testSaveSetting() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#selection()}
     */
    @Test
    void testSelection() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/selection");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("menu-selection"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("menu-selection"));
    }

    /**
     * Method under test: {@link UserController#selection()}
     */
    @Test
    void testSelection2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/selection");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("menu-selection"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("menu-selection"));
    }

    /**
     * Method under test: {@link UserController#settings(org.springframework.ui.Model)}
     */
    @Test
    void testSettings() throws Exception {
        when(this.productRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}