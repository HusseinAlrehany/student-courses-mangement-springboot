package com.luv2code.studentcoursesinstructormanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class StudentSecurityConfig {

    //adding support for jdbc authentication
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);
    }

    //Restricting the access to URL based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/students")
                        .hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET,
                                "/api/students/**",
                                "/api/courses/**",
                                "/api/instructors/**",
                                "/api/instructorDetail/**",
                                "/api/reviews/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST,"/api/students/**",
                                "/api/courses/**",
                                "/api/instructors/**","/api/instructorsCourse/**",
                                "/api/instructorDetail/**","/api/reviews","/api/coursesreviews/**",
                                "/api/coursesandreviews/**").hasRole("INSTRUCTOR")
                        .requestMatchers(HttpMethod.PUT,"/api/students/**",
                                "/api/courses","/api/instructors/**",
                                "/api/instructorDetail","/api/reviews").hasRole("INSTRUCTOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/students/**",
                                "/api/courses/**","/api/instructors/**",
                                "/api/instructorDetail/**","/api/reviews/**").hasRole("ADMIN"));

        http.httpBasic();

        http.csrf().disable();
       return http.build();



    }

    //adding support for in memory authentication
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails deema= User.builder()
                .username("deema")
                .password("{noop}abc123")
                .roles("STUDENT")
                .build();

        UserDetails lian=User.builder()
                .username("lian")
                .password("{noop}abc123")
                .roles("INSTRUCTOR,STUDENT")
                .build();
        UserDetails hussein=User.builder()
                .username("hussein")
                .password("{noop}abc123")
                .roles("ADMIN,INSTRUCTOR,STUDENT")
                .build();

        return new InMemoryUserDetailsManager(deema,lian,hussein);



    }*/




}
