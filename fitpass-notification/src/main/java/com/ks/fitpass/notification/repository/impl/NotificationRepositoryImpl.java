package com.ks.fitpass.notification.repository.impl;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.repository.IRepositoryQuery;
import com.ks.fitpass.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertNotification(Notification notification) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(IRepositoryQuery.INSERT_NOTIFICATION, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, notification.getUserIdSend());
                ps.setInt(2, notification.getUserIdReceive());
                ps.setString(3, notification.getMessage());
                ps.setTimestamp(4, Timestamp.valueOf(notification.getTimeSend().toLocalDateTime()));
                ps.setInt(5, notification.getDepartmentId());
                ps.setInt(6, notification.getOrderDetailId());
                ps.setString(7, notification.getMessageType());
                ps.setInt(8, 0);

                return ps;
            }, keyHolder);

            // Retrieve the generated ID after insertion
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                int notificationId = Objects.requireNonNull(generatedId).intValue();
                notification.setNotificationId(notificationId);
            }

            return 1; // Assuming successful insertion, adjust as needed
        } catch (DataAccessException e) {
            // Handle insertion failure
            // Log or throw an exception, depending on your application's error handling strategy
            return 0; // Indicate failure
        }
    }

    @Override
    public int updateStatusNotificationById(int notificationId, int status) {
        return jdbcTemplate.update(IRepositoryQuery.UPDATE_STATUS_NOTIFICATION_BY_ID, status, notificationId);
    }

    @Override
    public List<Notification> getAllNotificationForUser(int userIdReceive, int offset, int size) {
        try {
            return jdbcTemplate.query(
                    IRepositoryQuery.GET_ALL_NOTIFICATION_FOR_USER,
                    (rs, rowNum) -> {
                        Notification notification = new Notification();
                        notification.setNotificationId(rs.getInt("notification_id"));
                        notification.setUserIdSend(rs.getInt("user_id_send"));
                        notification.setUserIdReceive(rs.getInt("user_id_receive"));
                        notification.setMessage(rs.getString("message"));
                        notification.setTimeSend(rs.getTimestamp("time_send"));
                        notification.setDepartmentId(rs.getInt("department_id"));
                        notification.setOrderDetailId(rs.getInt("order_detail_id"));
                        notification.setMessageType(rs.getString("message_type"));
                        notification.setStatus(rs.getInt("status"));
                        return notification;
                    },
                    userIdReceive, size, offset);
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
                notification.setOrderDetailId(rs.getInt("order_detail_id"));
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
                notification.setOrderDetailId(rs.getInt("order_detail_id"));
                notification.setMessageType(rs.getString("message_type"));
                notification.setStatus(rs.getInt("status"));
                return notification;
            }, empIdReceive);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getNumberOfUnseenNotification(int userIdReceive) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_NUM_OF_UNSEEN_NOTIFICATION_BY_USER_RECEIVE_ID, Integer.class, userIdReceive);
    }

    @Override
    public int getTotalNotificationsForUser(int userId) {
        return jdbcTemplate.queryForObject(IRepositoryQuery.GET_NUM_OF_TOTAL_NOTIFICATION_BY_USER_RECEIVE_ID, Integer.class, userId);
    }
}
