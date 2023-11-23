package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.dto.DepartmentAmenitie;
import com.ks.fitpass.department.entity.DepartmentAmenities;
import com.ks.fitpass.department.mapper.DepartmentAmenitiesMapper;
import com.ks.fitpass.department.repository.DepartmentAmenitieRepository;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class DepartmentAmenitieRepositoryImpl implements DepartmentAmenitieRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DepartmentAmenities> getAllDepartmentAmenitiesActivate(int gymDepartmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_DEPARTMENT_AMENITIES_DEPARTMENT_ID,
                new DepartmentAmenitiesMapper(), gymDepartmentId);
    }

    @Override
    public List<DepartmentAmenitie> getAllAmenitieOfDepartment(int departmentId) {
        return jdbcTemplate.query(IRepositoryQuery.GET_ALL_AMENITIE_OF_DEPARTMENT, (rs, rowNum) -> {
            DepartmentAmenitie a = new DepartmentAmenitie();
            a.setAmenitieId(rs.getInt("amenitie_id"));
            a.setBrandId(rs.getInt("brand_id"));
            a.setPhotoUrl(rs.getString("photo_url"));
            a.setAmenitieName(rs.getString("amenitie_name"));
            a.setDescription(rs.getString("description"));
            return a;
        }, departmentId);
    }

    @Override
    public int[] insertDepartmentAmenitie(int gymDepartmentId, List<Integer> amenitieId) {
        return jdbcTemplate.batchUpdate(IRepositoryQuery.INSERT_DEPARTMENT_AMENITIE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                ps.setInt(1,gymDepartmentId);
                ps.setInt(2,amenitieId.get(i));
            }

            @Override
            public int getBatchSize() {
                return amenitieId.size();
            }
        });
    }

    @Override
    public int deleteAllDepartmentAmenitie(int gymDepartmentId) {
        return jdbcTemplate.update(IRepositoryQuery.DELETE_ALL_DEPARTMENT_AMENITIE, gymDepartmentId);
    }
}
