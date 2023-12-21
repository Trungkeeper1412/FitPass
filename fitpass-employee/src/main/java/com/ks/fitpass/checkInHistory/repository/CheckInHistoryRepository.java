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

    List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId, int offset, int size);
    List<CheckInHistoryFixed> getListCheckInHistoryFixedByDepartmentId(int departmentId, int offset, int size);

    List<CheckInHistoryFlexible> searchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter, int offset, int size);
    List<CheckInHistoryFixed> searchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter, int offset, int size);

    Integer getTotalListCheckInHistoryFlexibleByDepartmentId(int departmentId);

    Integer getTotalListCheckInHistoryFixedByDepartmentId(int departmentId);

    Integer countSearchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter);

    Integer countSearchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter);
}
