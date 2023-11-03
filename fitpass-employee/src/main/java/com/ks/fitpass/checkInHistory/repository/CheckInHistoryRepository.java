package com.ks.fitpass.checkInHistory.repository;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;

import java.sql.Timestamp;
import java.util.List;

public interface CheckInHistoryRepository {
    Timestamp getCheckInTimeByOrderDetailId(int orderDetailId);

    Integer getCheckInHistoryIdByOrderDetailIdAndCheckInTime(int orderDetailId, Timestamp checkInTime);

    int updateCheckOutTimeAndCredit(int checkInHistoryId, Timestamp checkOutTime, double totalCredit);

    List<Integer> getListCheckInHistoryIdNeedCheckOut();

    List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId);
    List<CheckInHistoryFixed> getListCheckInHistoryFixedByDepartmentId(int departmentId);

    List<CheckInHistoryFlexible> searchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter);
    List<CheckInHistoryFixed> searchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter);
}
