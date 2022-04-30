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
}/*
package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Role;
import com.example.vitalyevich.onlinesite.model.User;
import com.example.vitalyevich.onlinesite.model.UserPoint;
import com.example.vitalyevich.onlinesite.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {
        User user = new User("Максим");
        mockMvc.perform(
                        post("/persons")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Максим"));
    }

    @Test
    public void givenId_whenGetExistingPerson_thenStatus200andPersonReturned() throws Exception {
        long id = createTestPerson("Michail").getId();
        mockMvc.perform(
                        get("/persons/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Michail"));
    }

    public void givenId_whenGetNotExistingPerson_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                        get("/persons/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    @Test
    public void givePerson_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        long id = createTestPerson("Nick").getId();
        mockMvc.perform(
                        put("/persons/{id}", id)
                                .content(objectMapper.writeValueAsString(new Person("Michail")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Michail"));
    }

    @Test
    public void givenPerson_whenDeletePerson_thenStatus200() throws Exception {
        Person person = createTestPerson("Nick");
        mockMvc.perform(
                        delete("/persons/{id}", person.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(person)));
    }

    @Test
    public void givenPersons_whenGetPersons_thenStatus200() throws Exception {
        Person p1 = createTestPerson("Jane");
        Person p2 =createTestPerson( "Joe");
        mockMvc.perform(
                        get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }


    private User createTestPerson(String userCode, String firstName, LocalDate dateOfBirth, String email, String inviteCode) {
        User user = new User(userCode, firstName, dateOfBirth, email,inviteCode);
        return repository.save(user);
    }

    @Test
    void addUser() {
    }

    @Test
    void saveSetting() {
    }
}*/
