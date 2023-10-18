package com.ks.fitpass.department.service.impl;

import com.ks.fitpass.department.entity.ItemInventory;
import com.ks.fitpass.department.repository.ItemInventoryRepository;
import com.ks.fitpass.department.service.ItemInventoryService;
import org.springframework.stereotype.Service;

@Service
public class ItemInventoryServiceImpl implements ItemInventoryService {

    private ItemInventoryRepository itemInventoryRepository;

    public ItemInventoryServiceImpl(ItemInventoryRepository itemInventoryRepository) {
        this.itemInventoryRepository = itemInventoryRepository;
    }

    @Override
    public void insert(ItemInventory itemInventory) {
        itemInventoryRepository.insert(itemInventory);
    }

    @Override
    public boolean checkDuplicateItemInventory(int userid, int planId) {
        return itemInventoryRepository.checkDuplicateItemInventory(userid, planId);
    }
}
