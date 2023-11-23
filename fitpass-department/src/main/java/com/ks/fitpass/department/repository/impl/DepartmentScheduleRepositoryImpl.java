package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.mapper.DepartmentScheduleMapper;
import com.ks.fitpass.department.repository.DepartmentScheduleRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentScheduleRepositoryImpl implements DepartmentScheduleRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<DepartmentSchedule> getAllByDepartmentID(int departmentID) throws DataAccessException {
        return jdbcTemplate.query(GET_DEPARTMENT_SCHEDULE_BY_ID_DEPARTMENT, new DepartmentScheduleMapper(), departmentID);
    }

    @Override
    public int[] addDepartmentSchedule(Map<String, String> dayToTimeMap, int departmentID) {
        List<String> dayOfWeekList = new ArrayList<>();
        List<String> openTimeList = new ArrayList<>();
        List<String> closeTimeList = new ArrayList<>();

        for (Map.Entry<String, String> entry : dayToTimeMap.entrySet()) {
            String dayOfWeek = entry.getKey();
            String timeRange = entry.getValue();
            String[] times = timeRange.split("-");
            String openTime = times[0];
            String closeTime = times[1];

            dayOfWeekList.add(dayOfWeek);
            openTimeList.add(openTime);
            closeTimeList.add(closeTime);
        }

        String sql = ADD_DEPARTMENT_SCHEDULE;

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, departmentID);
                ps.setString(2, dayOfWeekList.get(i));
                ps.setString(3, openTimeList.get(i));
                ps.setString(4, closeTimeList.get(i));
            }

            @Override
            public int getBatchSize() {
                return dayOfWeekList.size();
            }
        });
    }

    @Override
    public int deleteAllDepartmentSchedule(int departmentId) {
        return jdbcTemplate.update(DELETE_ALL_DEPARTMENT_SCHEDULE, departmentId);
    }
}
