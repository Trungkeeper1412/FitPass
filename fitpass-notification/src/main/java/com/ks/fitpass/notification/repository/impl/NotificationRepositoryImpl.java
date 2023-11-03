package com.ks.fitpass.notification.repository.impl;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.repository.IRepositoryQuery;
import com.ks.fitpass.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertNotification(Notification notification) {
        return jdbcTemplate.update(IRepositoryQuery.INSERT_NOTIFICATION, notification.getUserIdSend(), notification.getUserIdReceive(), notification.getMessage(), notification.getTimeSend(), notification.getDepartmentId(), notification.getMessageType(), 0);
    }

    @Override
    public Notification getConfirmCheckInByUserIdReceive(int userIdReceive) {
        try {
            return jdbcTemplate.queryForObject(IRepositoryQuery.GET_CONFIRM_CHECK_IN_BY_USER_RECEIVE_ID, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, userIdReceive, userIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }

    @Override
    public int updateStatusNotificationById(int notificationId, int status) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_STATUS_NOTIFICATION_BY_ID, status, notificationId);
    }

    @Override
    public Notification getConfirmCheckOutByUserIdReceive(int userIdReceive) {
        try {
            return jdbcTemplate.queryForObject(IRepositoryQuery.GET_CONFIRM_CHECK_OUT_BY_USER_RECEIVE_ID, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, userIdReceive, userIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }

    @Override
    public List<Notification> getAllConfirmCheckOutSuccessByEmpIdReceive(int empIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_CONFIRM_CHECK_OUT_BY_EMP_RECEIVE_ID_AND_TYPE, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, empIdReceive, "Thông báo checkout thành công tới employee");
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }

    @Override
    public List<Notification> getAllConfirmCheckInSuccessByEmpIdReceive(int empIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_CONFIRM_CHECK_IN_BY_EMP_RECEIVE_ID_AND_TYPE, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, empIdReceive, "Thông báo checkin thành công tới employee");
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }

    @Override
    public List<Notification> getAllConfirmCheckInCancelByEmpIdReceive(int empIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_CONFIRM_CHECK_IN_BY_EMP_RECEIVE_ID_AND_TYPE, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, empIdReceive, "Thông báo hủy checkin tới employee");
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }

    @Override
    public List<Notification> getAllConfirmCheckOutCancelByEmpIdReceive(int empIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_CONFIRM_CHECK_OUT_BY_EMP_RECEIVE_ID_AND_TYPE, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                return notification;
            }, empIdReceive, "Thông báo hủy checkout tới employee");
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null khi không có kết quả
        }
    }
}
