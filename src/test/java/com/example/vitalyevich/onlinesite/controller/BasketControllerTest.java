package com.example.vitalyevich.onlinesite.controller;

import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BasketController.class})
@ExtendWith(SpringExtension.class)
class BasketControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @Autowired
    private BasketController basketController;

    @MockBean
    private BasketRepository basketRepository;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link BasketController#add(int, String)}
     */
    @Test
    void testAdd() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/basket/add/{tag}/{id}", "Uri Vars",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link BasketController#addCount(int, int)}
     */
    @Test
    void testAddCount() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/menu/selection/{id}/add/{count}",
                "Uri Vars", "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link BasketController#basket(org.springframework.ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)}
     */
    @Test
    void testBasket() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/basket");
        MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-basket"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-basket"));
    }

    /**
     * Method under test: {@link BasketController#basket(org.springframework.ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)}
     */
    @Test
    void testBasket2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/basket");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/rolls#blackout-basket"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/rolls#blackout-basket"));
    }

    /**
     * Method under test: {@link BasketController#del(int, String)}
     */
    @Test
    void testDel() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/basket/del/{tag}/{id}", "Uri Vars",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link BasketController#sub(int, String)}
     */
    @Test
    void testSub() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/basket/sub/{tag}/{id}", "Uri Vars",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.basketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

