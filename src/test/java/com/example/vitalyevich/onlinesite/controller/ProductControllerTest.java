package com.example.vitalyevich.onlinesite.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.vitalyevich.onlinesite.model.Category;
import com.example.vitalyevich.onlinesite.model.Photo;
import com.example.vitalyevich.onlinesite.model.Price;
import com.example.vitalyevich.onlinesite.model.Product;
import com.example.vitalyevich.onlinesite.repository.AccessRepository;
import com.example.vitalyevich.onlinesite.repository.BasketRepository;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private AccessRepository accessRepository;

    @MockBean
    private BasketRepository basketRepository;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link ProductController#menuDrinks(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuDrinks() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/drinks/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/drinks/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/drinks/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuDrinks(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuDrinks2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/drinks/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/drinks/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/drinks/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuRolls(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuRolls() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/rolls/page/{offset}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ProductController#menuSauces(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSauces() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/sauces/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sauces/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sauces/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSauces(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSauces2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/sauces/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sauces/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sauces/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSelection(int, org.springframework.ui.Model)}
     */
    @Test
    void testMenuSelection() throws Exception {
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Photo photo = new Photo();
        photo.setFormat("Format");
        photo.setId(1);
        photo.setName("Name");
        photo.setPath("Path");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Photo photo1 = new Photo();
        photo1.setFormat("Format");
        photo1.setId(1);
        photo1.setName("Name");
        photo1.setPath("Path");

        Price price = new Price();
        price.setDiscount(3);
        price.setId(1);
        price.setPrice(10.0d);
        price.setProducts(new Product());

        Product product = new Product();
        product.setAmount(10);
        product.setCategory(category1);
        product.setId(1);
        product.setIngredients(new HashSet<>());
        product.setPhoto(photo1);
        product.setPrice(price);
        product.setProductName("Product Name");
        product.setWeight(3);

        Price price1 = new Price();
        price1.setDiscount(3);
        price1.setId(1);
        price1.setPrice(10.0d);
        price1.setProducts(product);

        Product product1 = new Product();
        product1.setAmount(10);
        product1.setCategory(category);
        product1.setId(1);
        product1.setIngredients(new HashSet<>());
        product1.setPhoto(photo);
        product1.setPrice(price1);
        product1.setProductName("Product Name");
        product1.setWeight(3);
        Optional<Product> ofResult = Optional.of(product1);
        when(this.productRepository.findByCategory((Category) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(this.productRepository.findById((Integer) any())).thenReturn(ofResult);
        when(this.productRepository.existsById((Integer) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/selection/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product", "products"))
                .andExpect(MockMvcResultMatchers.view().name("menu-selection"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("menu-selection"));
    }

    /**
     * Method under test: {@link ProductController#menuSelection(int, org.springframework.ui.Model)}
     */
    @Test
    void testMenuSelection2() throws Exception {
        Category category = new Category();
        category.setCategoryName("Category Name");
        category.setId(1);

        Photo photo = new Photo();
        photo.setFormat("Format");
        photo.setId(1);
        photo.setName("Name");
        photo.setPath("Path");

        Category category1 = new Category();
        category1.setCategoryName("Category Name");
        category1.setId(1);

        Photo photo1 = new Photo();
        photo1.setFormat("Format");
        photo1.setId(1);
        photo1.setName("Name");
        photo1.setPath("Path");

        Price price = new Price();
        price.setDiscount(3);
        price.setId(1);
        price.setPrice(10.0d);
        price.setProducts(new Product());

        Product product = new Product();
        product.setAmount(10);
        product.setCategory(category1);
        product.setId(1);
        product.setIngredients(new HashSet<>());
        product.setPhoto(photo1);
        product.setPrice(price);
        product.setProductName("Product Name");
        product.setWeight(3);

        Price price1 = new Price();
        price1.setDiscount(3);
        price1.setId(1);
        price1.setPrice(10.0d);
        price1.setProducts(product);

        Product product1 = new Product();
        product1.setAmount(10);
        product1.setCategory(category);
        product1.setId(1);
        product1.setIngredients(new HashSet<>());
        product1.setPhoto(photo);
        product1.setPrice(price1);
        product1.setProductName("Product Name");
        product1.setWeight(3);
        Optional<Product> ofResult = Optional.of(product1);
        when(this.productRepository.findByCategory((Category) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(this.productRepository.findById((Integer) any())).thenReturn(ofResult);
        when(this.productRepository.existsById((Integer) any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/selection/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu"));
    }

    /**
     * Method under test: {@link ProductController#menuSets(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSets() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/sets/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sets/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sets/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSets(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSets2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/sets/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sets/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sets/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSoups(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSoups() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/soups/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/soups/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/soups/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSoups(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSoups2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/soups/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/soups/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/soups/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSushi(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSushi() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/sushi/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sushi/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sushi/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuSushi(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuSushi2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/sushi/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/sushi/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/sushi/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuWoks(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuWoks() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/menu/woks/page/{offset}", 2);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/woks/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/woks/page/0"));
    }

    /**
     * Method under test: {@link ProductController#menuWoks(org.springframework.ui.Model, Integer)}
     */
    @Test
    void testMenuWoks2() throws Exception {
        when(this.productRepository.findByCategory((com.example.vitalyevich.onlinesite.model.Category) any(),
                (org.springframework.data.domain.Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/menu/woks/page/{offset}", 2);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/menu/woks/page/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/woks/page/0"));
    }
}

