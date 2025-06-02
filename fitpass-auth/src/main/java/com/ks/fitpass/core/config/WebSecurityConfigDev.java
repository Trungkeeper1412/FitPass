package com.ks.fitpass.core.config;

import com.ks.fitpass.core.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigDev {
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public WebSecurityConfigDev (CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler){
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(new AuditInterceptor(), AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/user-homepage-assets/**", "/employee-assets/**", "/webfonts/**").permitAll()
                        .requestMatchers("/websocket/**").hasAnyAuthority("USER", "EMPLOYEE")
                        .requestMatchers("/login", "/logout").permitAll()
                        .requestMatchers("/forgot-password/**").anonymous()
                        .requestMatchers("/register").anonymous()
                        .requestMatchers("/").permitAll()

                        .requestMatchers("/landing-page").permitAll()
                        .requestMatchers("/homepage/**").hasAnyAuthority("ROLE_ANONYMOUS","USER")
                        .requestMatchers("/brand/**").permitAll()
                        .requestMatchers("/department/**").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/become-a-partner/**").permitAll()

                        .requestMatchers("/checkout/**").hasAuthority("USER")
                        .requestMatchers("/profile/**").hasAuthority("USER")
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/item/**").hasAuthority("USER")
                        .requestMatchers("/inventory/**").hasAuthority("USER")
                        .requestMatchers("/payment/**").hasAuthority("USER")
                        .requestMatchers("/confirm/**").hasAuthority("USER")

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
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/img/**", "/assets/**", "/ws/**", "/error/**", "/notification/**");
    }

    @Bean
    RememberMeServices rememberMeServices(UserService userService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("FitPass", userService, encodingAlgorithm);
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
