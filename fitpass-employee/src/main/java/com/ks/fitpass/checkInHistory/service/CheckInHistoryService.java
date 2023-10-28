package com.ks.fitpass.checkInHistory.service;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;

import java.sql.Timestamp;
import java.util.List;

public interface CheckInHistoryService {
    Timestamp getCheckInTimeByOrderDetailId(int orderDetailId);

    Integer getCheckInHistoryIdByOrderDetailIdAndCheckInTime(int orderDetailId, Timestamp checkInTime);

    int updateCheckOutTimeAndCredit(int checkInHistoryId, Timestamp checkOutTime, double totalCredit);

    List<Integer> getListCheckInHistoryIdNeedCheckOut();

    List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId);

}
