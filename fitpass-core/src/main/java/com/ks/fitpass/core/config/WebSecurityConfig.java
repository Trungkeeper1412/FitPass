package com.ks.fitpass.core.config;

import com.ks.fitpass.core.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/webfonts/**").permitAll()
                        .requestMatchers("/user-homepage-assets/**","/employee-assets/**", "/upload/**").permitAll()
                        .requestMatchers("/login", "/logout").permitAll()
                        .requestMatchers("/landing-page").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/become-a-partner/**").permitAll()
                        .requestMatchers("/profile/**").hasAuthority("USER")
                        .requestMatchers("/item/**").hasAuthority("USER")
                        .requestMatchers("/inventory/**").hasAuthority("USER")
                        .requestMatchers("/payment/**").hasAuthority("USER")

                        .requestMatchers("/employee/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/gym-owner/**").hasAuthority("GYM_OWNER")
                        .requestMatchers("/brand-owner/**").hasAuthority("BRAND_OWNER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        //.loginProcessingUrl("/j_spring_security_check")   // submit URL login
                        .loginPage("/login")
                        .usernameParameter("account")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                               // default url
                        .logoutSuccessUrl("/login?logout")                  // default url
                        .invalidateHttpSession(true)                        // default: true
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe((remember) -> remember
                        .rememberMeServices(rememberMeServices)
                        .rememberMeParameter("remember-me")
                );
        return http.build();
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("FitPass", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256);
        rememberMe.setTokenValiditySeconds(3000);
        return rememberMe;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
