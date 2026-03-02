package com.security.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
// Tells Spring that this class contains configuration definitions
@EnableWebSecurity
// Enables Spring Security for the application
public class SpringSecurity {

    // Define a SecurityFilterChain bean that configures HTTP security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable CSRF protection
        // (Common in stateless APIs, but NOT recommended for production form-based apps)
        http.csrf((csrf) -> csrf.disable())

                // Configure authorization rules for HTTP requests
                .authorizeHttpRequests(
                        auth -> auth
                                // Require authentication for ANY incoming request
                                .anyRequest().authenticated()
                )

                // Configure form-based login
                .formLogin(
                        form -> form
                                // Custom login page URL (GET request)
                                // You must create a controller + HTML page for this
                                .loginPage("/login")

                                // URL where login form submits username & password (POST request)
                                // Spring Security handles this automatically
                                .loginProcessingUrl("/login")

                                // Redirect to "/users" after successful login
                                // If not specified, it redirects to the originally requested page
                                .defaultSuccessUrl("/users")

                                // Allow everyone (even unauthenticated users)
                                // to access the login page
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutUrl("/logout")        // URL to trigger logout
                                .logoutSuccessUrl("/login")  // Redirect after logout
                                .permitAll()
                );

        // Build and return the configured SecurityFilterChain object
        return http.build();
    }
}