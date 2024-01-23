package com.codegym.cms.configuration;

import com.codegym.cms.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Configuration
@EnableWebSecurity
@Transactional
public class    SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //The pages does not require login
        http.authorizeRequests()
                .antMatchers("/login", "/logout")
                    .permitAll();

        // For both of user, admin
        http.authorizeRequests()
                .antMatchers("/", "/home", "/user/**")
                    .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // For admin only
        http.authorizeRequests()
                .antMatchers("/role/**")
                    .access("hasAnyRole('ROLE_ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests()
                .and()
                    .exceptionHandling()
                        .accessDeniedPage("/access-denied");

        // Config for Login Form
        http.authorizeRequests()
                .and().formLogin()// Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true");
    }
}
