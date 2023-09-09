package com.howard7892.springsecurityoauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityServerConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager udm = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder().encode("1234"))
                .authorities("read")
                .build();
        udm.createUser(user);
        return udm;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }
}
