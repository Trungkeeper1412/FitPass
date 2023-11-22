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
    public int updateStatusNotificationById(int notificationId, int status) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_STATUS_NOTIFICATION_BY_ID, status, notificationId);
    }

    @Override
    public List<Notification> getAllNotificationForUser(int userIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_NOTIFICATION_FOR_USER, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                notification.setStatus(rs.getInt("status"));
                return notification;
            }, userIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Notification> get3NewestUnseenNotificationForUser(int userIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_3_NEWEST_UNSEEN_NOTIFICATION_FOR_USER, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                notification.setStatus(rs.getInt("status"));
                return notification;
            }, userIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Notification> getAllNotificationForEmployee(int empIdReceive) {
        try {
            return jdbcTemplate.query(IRepositoryQuery.GET_ALL_NOTIFICATION_FOR_EMPLOYEE, (rs, rowNum) -> {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserIdSend(rs.getInt("user_id_send"));
                notification.setUserIdReceive(rs.getInt("user_id_receive"));
                notification.setMessage(rs.getString("message"));
                notification.setTimeSend(rs.getTimestamp("time_send"));
                notification.setDepartmentId(rs.getInt("department_id"));
                notification.setMessageType(rs.getString("message_type"));
                notification.setStatus(rs.getInt("status"));
                return notification;
            }, empIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
