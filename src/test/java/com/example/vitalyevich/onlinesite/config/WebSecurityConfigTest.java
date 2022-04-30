package com.example.vitalyevich.onlinesite.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

class WebSecurityConfigTest {
    /**
     * Method under test: {@link WebSecurityConfig#configure(AuthenticationManagerBuilder)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfigure() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder.jdbcAuthentication()" because "auth" is null
        //       at com.example.vitalyevich.onlinesite.config.WebSecurityConfig.configure(WebSecurityConfig.java:63)
        //   In order to prevent configure(AuthenticationManagerBuilder)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   configure(AuthenticationManagerBuilder).
        //   See https://diff.blue/R013 to resolve this issue.

        (new WebSecurityConfig()).configure((AuthenticationManagerBuilder) null);
    }

    /**
     * Method under test: {@link WebSecurityConfig#configure(HttpSecurity)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfigure2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.config.annotation.web.builders.HttpSecurity.authorizeRequests()" because "http" is null
        //       at com.example.vitalyevich.onlinesite.config.WebSecurityConfig.configure(WebSecurityConfig.java:32)
        //   In order to prevent configure(HttpSecurity)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   configure(HttpSecurity).
        //   See https://diff.blue/R013 to resolve this issue.

        (new WebSecurityConfig()).configure((HttpSecurity) null);
    }

    /**
     * Method under test: {@link WebSecurityConfig#configure(WebSecurity)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfigure3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.config.annotation.web.builders.WebSecurity.ignoring()" because "web" is null
        //       at com.example.vitalyevich.onlinesite.config.WebSecurityConfig.configure(WebSecurityConfig.java:55)
        //   In order to prevent configure(WebSecurity)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   configure(WebSecurity).
        //   See https://diff.blue/R013 to resolve this issue.

        (new WebSecurityConfig()).configure((WebSecurity) null);
    }
}

