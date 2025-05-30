package com.ks.fitpass.core.mapper;

import com.ks.fitpass.core.entity.Kbn;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KbnMapper implements RowMapper<Kbn> {

    @Override
    public Kbn mapRow(ResultSet resultSet, int i) throws SQLException {
        return Kbn.builder()
                .kbnId(resultSet.getInt("mst_kbn_id"))
                .kbnName(resultSet.getString("mst_kbn_name"))
                .kbnKey(resultSet.getInt("mst_kbn_key"))
                .kbnValue(resultSet.getString("mst_kbn_value"))
                .build();
    }
}
