package com.ks.fitpass.checkInHistory.service;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;

import java.sql.Timestamp;
import java.util.List;

public interface CheckInHistoryService {
    Timestamp getCheckInTimeByOrderDetailId(int orderDetailId);

    Integer getCheckInHistoryIdByOrderDetailIdAndCheckInTime(int orderDetailId, Timestamp checkInTime);

    int updateCheckOutTimeAndCredit(int checkInHistoryId, Timestamp checkOutTime, double totalCredit);

    List<Integer> getListCheckInHistoryIdNeedCheckOut();

    List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId, int page, int size);

    List<CheckInHistoryFixed> getListCheckInHistoryFixedByDepartmentId(int departmentId, int page, int size);

    List<CheckInHistoryFlexible> searchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter);

    List<CheckInHistoryFixed> searchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter);

    int getTotalListCheckInHistoryFlexibleByDepartmentId(int departmentId);

    int getTotalListCheckInHistoryFixedByDepartmentId(int departmentId);
}
