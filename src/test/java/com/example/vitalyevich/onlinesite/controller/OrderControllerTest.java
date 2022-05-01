package com.example.vitalyevich.onlinesite.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.vitalyevich.onlinesite.model.Access;
import com.example.vitalyevich.onlinesite.model.Address;
import com.example.vitalyevich.onlinesite.model.City;
import com.example.vitalyevich.onlinesite.model.Order;
import com.example.vitalyevich.onlinesite.model.Payment;
import com.example.vitalyevich.onlinesite.model.User;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.AddressRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import com.example.vitalyevich.onlinesite.repository.OrderProductRepository;
import com.example.vitalyevich.onlinesite.repository.OrderRepository;
import com.example.vitalyevich.onlinesite.repository.UserPointRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private BasketRepository basketRepository;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderProductRepository orderProductRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserPointRepository userPointRepository;

    /**
     * Method under test: {@link OrderController#add(org.springframework.ui.Model, int, int, int, int, int, int, String)}
     */
    @Test
    void testAdd() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/order")
                .param("address", "https://example.org/example")
                .param("comment", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("contact", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult1.param("d", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("h", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult4 = paramResult3.param("m", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult4.param("point", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link OrderController#cancelOrder(org.springframework.ui.Model, int)}
     */
    @Test
    void testCancelOrder() throws Exception {
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

        City city = new City();
        city.setCityName("Oxford");
        city.setId(1);

        Address address = new Address();
        address.setCity(city);
        address.setEntrance(1);
        address.setFloor(1);
        address.setHome(1);
        address.setId(1);
        address.setStreet("Street");
        address.setUsers(new HashSet<>());

        Payment payment = new Payment();
        payment.setId(1);
        payment.setPaymentName("Payment Name");

        Order order = new Order();
        order.setAccess(access);
        order.setAddress(address);
        order.setComment("Comment");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setEndDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        order.setId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setOrderDate(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        order.setOrderProducts(new HashSet<>());
        order.setPayment(payment);
        order.setPrice(10.0d);
        order.setStatus("Status");
        order.setType("Type");
        when(this.orderRepository.findOrderById(anyInt())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/profile/orders/cancel/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/profile/orders"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile/orders"));
    }

    /**
     * Method under test: {@link OrderController#order(org.springframework.ui.Model)}
     */
    @Test
    void testOrder() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

