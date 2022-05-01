package com.example.vitalyevich.onlinesite.controller;

import static org.mockito.Mockito.when;

import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.ActionRepository;
import com.example.vitalyevich.onlinesite.repository.OrderRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @MockBean
    private ActionRepository actionRepository;

    @Autowired
    private AdminController adminController;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link AdminController#adminClient(org.springframework.ui.Model)}
     */
    @Test
    void testAdminClient() throws Exception {
        when(this.accessRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/clients");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("accesses"))
                .andExpect(MockMvcResultMatchers.view().name("work-clients"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("work-clients"));
    }

    /**
     * Method under test: {@link AdminController#adminClient(org.springframework.ui.Model)}
     */
    @Test
    void testAdminClient2() throws Exception {
        when(this.accessRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/clients");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("accesses"))
                .andExpect(MockMvcResultMatchers.view().name("work-clients"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("work-clients"));
    }
}

