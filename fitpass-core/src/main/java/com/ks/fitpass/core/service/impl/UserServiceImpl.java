package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.*;
import com.ks.fitpass.core.repository.RoleRepository;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
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
public class UserServiceImpl implements UserDetailsService, UserService {

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

    @Override
    public List<UserDTO> getAllAccountUser() {
        return userRepository.getAllAccountUser();
    }

    @Override
    public int insertIntoUserDetail(UserDetail userDetail) {
        return userRepository.insertIntoUserDetail(userDetail);
    }

    @Override
    public int getLastInsertUserDetailId(UserDetail userDetail) {
        return userRepository.getLastInsertUserDetailId(userDetail);
    }

    @Override
    public String getUserEmailByUserId(int userId) {
        return userRepository.getUserEmailByUserId(userId);
    }


    @Override
    public int insertIntoUser(User user) {
        return userRepository.insertIntoUser(user);
    }

    @Override
    public int getLastUserInsertId(User user) {
        return userRepository.getLastUserInsertId(user);
    }

    @Override
    public int insertIntoUserRole(int userId, int roleId) {
        return userRepository.insertIntoUserRole(userId, roleId);
    }

    @Override
    public int getNumberOfAccountCreatedByBrandId(int brandId) {
        return userRepository.getNumberOfAccountCreatedByBrandId(brandId);
    }

    @Override
    public List<GymOwnerListDTO> getAllAccountByBrandId(int brandId) {
        return userRepository.getAllAccountByBrandId(brandId);
    }

    @Override
    public int updateUserDetail(UserDetail userDetail) {
        return userRepository.updateUserDetail(userDetail);
    }

    @Override
    public int updateUserStatusByUserId(int userId, int status) {
        return userRepository.updateUserStatusByUserId(userId, status);
    }

    @Override
    public UserDetail getUserDetailByUserDetailId(int userId) {
        return userRepository.getUserDetailByUserDetailId(userId);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return userRepository.checkEmailExist(email);
    }

    @Override
    public boolean checkUsernameExist(String username) {
        return userRepository.checkUsernameExist(username);
    }

    @Override
    public boolean checkAccountFirstTimeLogin(int userId) {
        return userRepository.checkAccountFirstTimeLogin(userId);
    }

    @Override
    public int updateFirstTimeLoginStatus(int userId, int status) {
        return userRepository.updateFirstTimeLoginStatus(userId, status);
    }

    @Override
    public List<GymOwnerListDTO> getAllAccountByDepartmentId(int departmentId) {
        return userRepository.getAllAccountByDepartmentId(departmentId);
    }

    @Override
    public int getNumberOfAccountCreatedByDepartmentId(int departmentId) {
        return userRepository.getNumberOfAccountCreatedByDepartmentId(departmentId);
    }

    @Override
    public User getUserByAccount(String username) {
        return userRepository.findByAccount(username);
    }
}
