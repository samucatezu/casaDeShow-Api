package com.example.casadeshowapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());


        return provider;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/css/**", "/cadastro/**","/images/**", "/*").permitAll()
                .antMatchers("/adicionar/**").hasRole("GERENTE")
                .antMatchers("/adicionarcasa/**").hasRole("GERENTE")
                .antMatchers("/casa/**").hasRole("GERENTE")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/",true).permitAll()
                .and().logout()
                .and()
                .exceptionHandling().accessDeniedPage("/acessonegado");
    }
}