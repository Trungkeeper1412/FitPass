package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.CustomUser;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class CustomUserServiceImpl implements UserDetailsService {
//
//    private final UserService userService;
//    @Override
//    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUserByAccount(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomUser(user.getUserAccount(), user.getUserPassword(), user.getRoles(), user.getUserId(), user.getUserPassword(), user.getUserCreateTime(), user.isUserDeleted());
//    }
//}
