package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.CustomUser;
import com.ks.fitpass.core.entity.Role;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.repository.RoleRepository;
import com.ks.fitpass.core.repository.UserRepository;
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
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(account);

        if (user == null) {
            System.out.println("User not found! " + account);
            throw new UsernameNotFoundException("User " + account + " was not found in the database");
        }
        System.out.println("USER: " + user.getUserAccount());

        // Get roles of user
        List<Role> roles = roleRepository.getRolesByUserAccount(account);

        // Create GrantedAuthority of Spring for role
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                grantedAuthorities.add(authority);
            }
        }
        System.out.println("Authorities: " + grantedAuthorities);

        // return object UserDetails of Spring
        return new CustomUser(
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
