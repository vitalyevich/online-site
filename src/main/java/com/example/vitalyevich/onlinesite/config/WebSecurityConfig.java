package com.example.vitalyevich.onlinesite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

/*    @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    /*.antMatchers("/account").hasRole("ADMIN")*/
                    .mvcMatchers("/profile").hasAnyRole("USER", "ADMIN")
                    /*.mvcMatchers("/account/{authentication.principal.username}").hasRole("ADMIN")*/
                    .antMatchers("/menu/**", "/delivery", "/authorization", "/registration").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/authorization")
                    .failureUrl("/authorization?error=true")
/*                    .successHandler(successHandler)*/
                    .defaultSuccessUrl("/profile",true)
                    .usernameParameter("phone")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/menu/rolls");
               /* .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied");*/
                    /*.permitAll();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
/*        web
                .ignoring()
                .antMatchers("/menu/**");*/
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**",
                        "/blocks/**"); // "/templates/**",
/*                .antMatchers("/publics/**");*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // .passwordEncoder(passwordEncoder);
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select phone, password, active from access where phone=?")
                .authoritiesByUsernameQuery("select A.phone, r.role_name from access A \n" +
                        "inner join user_roles ur on A.id = ur.user_id \n" +
                        "inner join roles r on ur.role_id = r.id where a.phone=?");
    }
}
