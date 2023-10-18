package com.ks.fitpass.department.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@RequiredArgsConstructor
public class ItemInventory {
    private int inventoryId;
    private int userId;
    private int planId;
    private Timestamp planActiveTime;
    private int item_status_key;
    private Timestamp planExpiredTime;
}
