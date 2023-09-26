package com.ks.fitpass.core.repository.impl;

import com.ks.fitpass.core.entity.Kbn;
import com.ks.fitpass.core.mapper.KbnMapper;
import com.ks.fitpass.core.repository.IRepositoryQuery;
import com.ks.fitpass.core.repository.KbnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KbnRepositoryImpl implements KbnRepository, IRepositoryQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public KbnRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Kbn> getKbnByName(String kbnName) {
        return jdbcTemplate.query(GET_KBN_BY_NAME, new KbnMapper(), kbnName);
    }
}
