package com.ks.fitpass.department.mapper;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentScheduleMapper implements RowMapper<DepartmentSchedule>  {
    @Override
    public DepartmentSchedule mapRow(ResultSet resultSet, int i) throws SQLException {
        return DepartmentSchedule.builder()
                .departmentScheduleId(resultSet.getInt("id"))
                .departmentId(resultSet.getInt("gym_department_id"))
                .departmentScheduleDay(resultSet.getString("day"))
                .departmentScheduleOpenTime(resultSet.getString("open_time"))
                .departmentScheduleCloseTime(resultSet.getString("close_time"))
                .build();
    }
}
