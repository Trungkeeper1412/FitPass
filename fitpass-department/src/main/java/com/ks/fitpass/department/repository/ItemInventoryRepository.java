package com.ks.fitpass.department.repository;

import com.ks.fitpass.department.entity.ItemInventory;

public interface ItemInventoryRepository {
    void insert(ItemInventory itemInventory);

    boolean checkDuplicateItemInventory(int userid, int planId);
}
