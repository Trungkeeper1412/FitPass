package com.ks.fitpass.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        //http.csrf().disable();
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/css/**", "/images/**", "/js/**", "/webfonts/**").permitAll()
                .requestMatchers("/login", "/logout").permitAll()
                .requestMatchers("/vip/**").permitAll()
                .requestMatchers("/manage/**").hasRole("ADMIN")
                .requestMatchers("/statistics/chart", "/statistics/drink").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                //.loginProcessingUrl("/j_spring_security_check")   // submit URL login
                .loginPage("/login")
                .usernameParameter("account")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
