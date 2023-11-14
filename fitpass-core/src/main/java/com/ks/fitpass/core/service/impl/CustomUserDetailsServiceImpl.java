package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.CustomUserDetails;
import com.ks.fitpass.core.entity.Role;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.RoleRepository;
import com.ks.fitpass.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username);

        if (user == null) {
            logger.error("User not found! {}", username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        logger.info("User: {}", user.getUserAccount());

        // Get roles of user
        List<Role> roles = roleRepository.getRolesByUserAccount(username);

        // Create GrantedAuthority of Spring for role
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                grantedAuthorities.add(authority);
            }
        }
        logger.info("Authorities: {}", user.getUserAccount());

        // return object UserDetails of Spring
        return new CustomUserDetails(
                user.getUserAccount(),
                user.getUserPassword(),
                grantedAuthorities,
                user.getUserId(),
                user.getUserPassword(),
                user.getUserCreateTime(),
                user.isUserDeleted()
        );
    }
}
