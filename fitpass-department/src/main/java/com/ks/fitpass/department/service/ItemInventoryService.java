package com.ks.fitpass.department.service;

import com.ks.fitpass.department.entity.ItemInventory;

public interface ItemInventoryService {
    void insert(ItemInventory itemInventory);

    boolean checkDuplicateItemInventory(int userid, int planId);
}
