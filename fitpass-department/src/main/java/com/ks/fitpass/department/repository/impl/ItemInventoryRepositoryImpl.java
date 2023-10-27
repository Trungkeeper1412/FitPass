package com.ks.fitpass.department.repository.impl;

import com.ks.fitpass.department.entity.ItemInventory;
import com.ks.fitpass.department.repository.IRepositoryQuery;
import com.ks.fitpass.department.repository.ItemInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemInventoryRepositoryImpl implements ItemInventoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemInventoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(ItemInventory itemInventory) {
        jdbcTemplate.update(IRepositoryQuery.INSERT_ITEM_INVENTORY,
                itemInventory.getUserId(),
                itemInventory.getPlanId(),
                itemInventory.getPlanActiveTime(),
                itemInventory.getItem_status_key(),
                itemInventory.getPlanExpiredTime());
    }

    @Override
    public boolean checkDuplicateItemInventory(int userid, int planId) {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM item_inventory WHERE user_id = ? AND plan_id = ?", Integer.class, userid, planId);
        return count > 0;
    }
}
