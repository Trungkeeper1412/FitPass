package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class UserFeedbackMapper implements RowMapper<UserFeedback> {

    @Override
    public UserFeedback mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserFeedback.builder()
                .feedbackId(resultSet.getInt("feedback_id"))
                .userId(resultSet.getInt("user_id"))
                .userName(resultSet.getString("first_name") + " " + resultSet.getString("last_name")) // Combine first name and last name
                .departmentId(resultSet.getInt("department_id"))
                .rating(resultSet.getInt("rating"))
                .comments(resultSet.getString("comments"))
                .feedbackTime(resultSet.getTimestamp("feedback_time").toLocalDateTime())
                .feedbackStatus(resultSet.getInt("feedback_status"))
                .build();
    }
}