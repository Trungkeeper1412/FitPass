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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/css/**", "/images/**", "/js/**", "/webfonts/**").permitAll()
                .requestMatchers("/user-homepage-assets/**").permitAll()
                .requestMatchers("/employee-assets/**").permitAll()
                .requestMatchers("/login", "/logout").permitAll()
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/cart/**").permitAll()
                            .requestMatchers("/upload/**").permitAll()
                            .requestMatchers("/user/**").permitAll()
//                .requestMatchers("/gym-owner/**").hasRole("GYM_OWNER")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
//                .requestMatchers("/brand-owner/**").hasRole("BRAND_OWNER")
                //.requestMatchers("/employee").permitAll()
                .requestMatchers("/send-message").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                //.loginProcessingUrl("/j_spring_security_check")   // submit URL login
                .loginPage("/login")
                .usernameParameter("account")
                .passwordParameter("password")
                    //.successHandler(authenticationSuccessHandler())
                .defaultSuccessUrl("/user/homepage", true)
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
            );
        return http.build();
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("FitPass", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256);
        return rememberMe;
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        // Save remember me in memory (RAM)
//        return new InMemoryTokenRepositoryImpl();
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                // Chuyển hướng người dùng có quyền admin đến trang cụ thể
                response.sendRedirect("/admin/index");
            } else {
                // Chuyển hướng người dùng khác đến trang mặc định
                response.sendRedirect("/user/homepage");
            }
        };
    }
}
