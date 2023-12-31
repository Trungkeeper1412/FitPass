package com.ks.fitpass.core.repository.impl;

import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.mapper.UserMapper;
import com.ks.fitpass.core.repository.IRepositoryQuery;
import com.ks.fitpass.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserDTO> getAllAccountUser() {
        return jdbcTemplate.query(GET_ALL_USER_BY_ROLE_USER, (rs, rowNum) -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(rs.getInt("user_id"));
            userDTO.setUserDetailId(rs.getInt("user_detail_id"));
            userDTO.setFirstName(rs.getString("first_name"));
            userDTO.setLastName(rs.getString("last_name"));
            userDTO.setEmail(rs.getString("email"));
            userDTO.setPhoneNumber(rs.getString("phone_number"));
            userDTO.setImageUrl(rs.getString("image_url"));
            userDTO.setUserDeleted(rs.getBoolean("user_deleted"));
            return userDTO;
        });
    }

    @Override
    public User findByAccount(String account) {
        return jdbcTemplate.queryForObject(GET_USER_BY_USER_ACCOUNT, new UserMapper(), account);
    }

    @Override
    public boolean updatePassword(String newPassword, int userId) throws DataAccessException {
        return jdbcTemplate.update(
                UPDATE_USER_PASSWORD,
                newPassword,
                userId
        ) > 0;
    }

    @Override
    public int insertIntoUserDetail(UserDetail userDetail) {
        return jdbcTemplate.update(INSERT_INTO_USER_DETAIL,
                userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), userDetail.getPhoneNumber(),
                userDetail.getAddress(), userDetail.getDateOfBirth(),
                userDetail.getGender(), userDetail.getImageUrl(), userDetail.getSecurityId()
                );
    }

    @Override
    public int insertIntoUserDetailRegister(UserDetail userDetail) {
        return jdbcTemplate.update(INSERT_INTO_USER_DETAIL_REGISTER,
                userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), userDetail.getPhoneNumber(),
                userDetail.getDateOfBirth(),userDetail.getGender()
        );
    }

    @Override
    public Integer getLastInsertUserDetailId(UserDetail userDetail) {
        return jdbcTemplate.queryForObject(GET_LAST_INSERT_USER_DETAIL_ID, Integer.class,
                userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), userDetail.getPhoneNumber(),
                userDetail.getAddress(), userDetail.getDateOfBirth(),
                userDetail.getImageUrl(), userDetail.getSecurityId()
        );
    }

    @Override
    public Integer getLastInsertUserDetailIdRegister(UserDetail userDetail) {
        return jdbcTemplate.queryForObject(GET_LAST_INSERT_USER_REGISTER_DETAIL_ID, Integer.class,
                userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), userDetail.getPhoneNumber(), userDetail.getDateOfBirth()
        );
    }

    @Override
    public int insertIntoUser(User user) {
        return jdbcTemplate.update(INSERT_INTO_USER,
                user.getUserAccount(), user.getUserPassword(),
                user.getUserDetailId(), user.getUserCreateTime(),
                user.isUserDeleted(), user.getCreatedBy());
    }

    @Override
    public Integer getLastUserInsertId(User user) {
        if(user.getUserDetailId() == null) {
            return jdbcTemplate.queryForObject(GET_LAST_USER_INSERT_ID_NULL, Integer.class,
                    user.getUserAccount(),
                    user.getUserPassword(),
                    user.getUserCreateTime());
        }
        return jdbcTemplate.queryForObject(GET_LAST_USER_INSERT_ID,Integer.class,
                user.getUserAccount(),
                user.getUserPassword(),
                user.getUserDetailId(),
                user.getUserCreateTime());
    }

    @Override
    public int insertIntoUserRole(int userId, int roleId) {
        return jdbcTemplate.update(INSERT_INTO_USER_ROLE, userId, roleId);
    }

    @Override
    public Integer getNumberOfAccountCreatedByBrandId(int brandId) {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ACCOUNT_CREATED_BY_BRAND_ID, Integer.class, brandId);
    }

    @Override
    public List<GymOwnerListDTO> getAllAccountByBrandId(int brandId) {
        return jdbcTemplate.query(GET_ALL_ACCOUNT_CREATED_BY_BRAND_ID, (rs, rowNum) -> {
            GymOwnerListDTO gymOwnerListDTO = new GymOwnerListDTO();
            gymOwnerListDTO.setUserId(rs.getInt("user_id"));
            gymOwnerListDTO.setUserDetailId(rs.getInt("user_detail_id"));
            gymOwnerListDTO.setFirstName(rs.getString("first_name"));
            gymOwnerListDTO.setLastName(rs.getString("last_name"));
            gymOwnerListDTO.setEmail(rs.getString("email"));
            gymOwnerListDTO.setPhoneNumber(rs.getString("phone_number"));
            gymOwnerListDTO.setImageUrl(rs.getString("image_url"));
            gymOwnerListDTO.setSecurityId(rs.getString("securityId"));
            gymOwnerListDTO.setGymDepartmentName(rs.getString("name"));
            gymOwnerListDTO.setUserDeleted(rs.getBoolean("user_deleted"));
            return gymOwnerListDTO;
        }, brandId);
    }

    @Override
    public int updateUserDetail(UserDetail userDetail) {
        return jdbcTemplate.update(UPDATE_USER_DETAIL_BY_USER_DETAIL_ID, userDetail.getFirstName(),
                userDetail.getLastName(), userDetail.getEmail(), userDetail.getPhoneNumber(),
                userDetail.getAddress(), userDetail.getDateOfBirth(), userDetail.getGender(),
                userDetail.getImageUrl(),userDetail.getSecurityId(), userDetail.getUserDetailId());
    }

    @Override
    public int updateUserStatusByUserId(int userId, int status) {
        return jdbcTemplate.update(UPDATE_USER_STATUS_BY_USER_ID, status, userId);
    }

    @Override
    public UserDetail getUserDetailByUserDetailId(int userId) {
        return jdbcTemplate.queryForObject(GET_USER_DETAIL_BY_USER_DETAIL_ID, (rs, rowNum) ->
                        UserDetail.builder()
                                .userDetailId(rs.getInt("user_detail_id"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .email(rs.getString("email"))
                                .phoneNumber(rs.getString("phone_number"))
                                .address(rs.getString("address"))
                                .dateOfBirth(rs.getObject("date_of_birth", LocalDate.class))
                                .gender(rs.getString("gender"))
                                .imageUrl(rs.getString("image_url"))
                                .securityId(rs.getString("securityId"))
                                .userDeleted(rs.getBoolean("user_deleted"))
                                .gymDepartmentId(rs.getInt("gym_department_id"))
                                .build(), userId);
    }


    @Override
    public UserDetail getUserDetailByUserId(int userId) {
        return jdbcTemplate.queryForObject(GET_USER_DETAIL_BY_USER_ID, (rs, rowNum) -> {
            UserDetail userDetail = new UserDetail();
            userDetail.setUserDetailId(rs.getInt("user_detail_id"));
            userDetail.setFirstName(rs.getString("first_name"));
            userDetail.setLastName(rs.getString("last_name"));
            userDetail.setEmail(rs.getString("email"));
            userDetail.setPhoneNumber(rs.getString("phone_number"));
            userDetail.setAddress(rs.getString("address"));
            userDetail.setDateOfBirth(rs.getObject("date_of_birth", LocalDate.class));
            userDetail.setGender(rs.getString("gender"));
            userDetail.setImageUrl(rs.getString("image_url"));
            userDetail.setUserDeleted(rs.getBoolean("user_deleted"));
            return  userDetail;
        }, userId);
    }

    @Override
    public boolean checkEmailExist(String email) {
        Integer count = jdbcTemplate.queryForObject(CHECK_EMAIL_EXIST, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean checkUsernameExist(String username) {
        Integer count = jdbcTemplate.queryForObject(CHECK_USERNAME_EXIST, Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public boolean checkAccountFirstTimeLogin(int userId) {
        Integer count = jdbcTemplate.queryForObject(CHECK_ACCOUNT_FIRST_TIME_LOGIN, Integer.class, userId);
        return count != null && count > 0;
    }

    @Override
    public int updateFirstTimeLoginStatus(int userId, int status) {
        return jdbcTemplate.update(UPDATE_FIRST_TIME_LOGIN_STATUS, status, userId);
    }

    @Override
    public List<GymOwnerListDTO> getAllAccountByDepartmentId(int departmentId) {
        return jdbcTemplate.query(GET_ALL_ACCOUNT_EMPLOYEE_CREATED_BY_DEPARTMENT_ID, (rs, rowNum) -> {
            GymOwnerListDTO gymOwnerListDTO = new GymOwnerListDTO();
            gymOwnerListDTO.setUserId(rs.getInt("user_id"));
            gymOwnerListDTO.setUserDetailId(rs.getInt("user_detail_id"));
            gymOwnerListDTO.setFirstName(rs.getString("first_name"));
            gymOwnerListDTO.setLastName(rs.getString("last_name"));
            gymOwnerListDTO.setEmail(rs.getString("email"));
            gymOwnerListDTO.setPhoneNumber(rs.getString("phone_number"));
            gymOwnerListDTO.setImageUrl(rs.getString("image_url"));
            gymOwnerListDTO.setSecurityId(rs.getString("securityId"));
            gymOwnerListDTO.setUserDeleted(rs.getBoolean("user_deleted"));
            return gymOwnerListDTO;
        }, departmentId);
    }

    @Override
    public Integer getNumberOfAccountCreatedByDepartmentId(int departmentId) {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ACCOUNT_EMPLOYEE_CREATED_BY_DEPARTMENT_ID, Integer.class, departmentId);
    }

     @Override
    public Integer getDepartmentIdByEmployeeId(int employeeId) {
        return jdbcTemplate.queryForObject(GET_DEPARTMENT_ID_BY_EMPLOYEE_ID, Integer.class, employeeId);
    }

    @Override
    public int createEmployee(User user) {
        return jdbcTemplate.update(CREATE_EMPLOYEE, user.getUserAccount(), user.getUserPassword(),
                user.getUserDetailId(), user.getUserCreateTime(), user.isUserDeleted(),
                user.getCreatedBy(), user.getInDepartmentId());
    }


    @Override
    public Integer countAllUsersAccount() {
        return jdbcTemplate.queryForObject(COUNT_ALL_USERS_ACCOUNT, Integer.class);
    }

    @Override
    public int resetPassword(String email, String hashedPassword) {
        return jdbcTemplate.update(RESET_PASSWORD, hashedPassword, email);
    }
}
