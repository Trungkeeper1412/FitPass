package com.ks.fitpass.calendar.mapper;

import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInDataCalendarMapper implements RowMapper<CheckInDataCalendarDTO> {
    @Override
    public CheckInDataCalendarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CheckInDataCalendarDTO.builder()
                .checkInHistoryId(rs.getInt("check_in_history_id"))
                .gymDepartmentName(rs.getString("gym_department_name"))
                .checkInTime(rs.getTimestamp("check_in_time"))
                .build();
    }
}