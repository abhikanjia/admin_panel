package com.learn.adminpanel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve user by username

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_username,user_password,user_is_active from login_master where user_username=?"
        );

        //define query to retrieve roles by username

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT login_master.user_username , role_master.role_rolename FROM role_master INNER JOIN login_master ON role_master.profile_id = login_master.profile_id WHERE login_master.user_username=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasAnyAuthority("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers("/allusers").hasAuthority("ADMIN")
                        .requestMatchers("/deleteuser").hasAuthority("ADMIN")
                        .requestMatchers("/adduser").hasAuthority("ADMIN")
                        .requestMatchers("/save").hasAuthority("ADMIN")
                        .requestMatchers("/updateuser").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                ).exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
        );
        return http.build();
    }
}
