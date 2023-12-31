package com.ks.fitpass.checkInHistory.service.impl;

import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFixed;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.repository.CheckInHistoryRepository;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CheckInHistoryServiceImpl implements CheckInHistoryService {
    private final CheckInHistoryRepository checkInHistoryRepository;

    public CheckInHistoryServiceImpl(CheckInHistoryRepository checkInHistoryRepository) {
        this.checkInHistoryRepository = checkInHistoryRepository;
    }

    @Override
    public Timestamp getCheckInTimeByOrderDetailId(int orderDetailId) {
        return checkInHistoryRepository.getCheckInTimeByOrderDetailId(orderDetailId);
    }

    @Override
    public Integer getCheckInHistoryIdByOrderDetailIdAndCheckInTime(int orderDetailId, Timestamp checkInTime) {
        return checkInHistoryRepository.getCheckInHistoryIdByOrderDetailIdAndCheckInTime(orderDetailId, checkInTime);
    }

    @Override
    public int updateCheckOutTimeAndCredit(int checkInHistoryId, Timestamp checkOutTime, double totalCredit) {
        return checkInHistoryRepository.updateCheckOutTimeAndCredit(checkInHistoryId, checkOutTime, totalCredit);
    }

    @Override
    public List<Integer> getListCheckInHistoryIdNeedCheckOut() {
        return checkInHistoryRepository.getListCheckInHistoryIdNeedCheckOut();
    }

    @Override
    public List<CheckInHistoryFlexible> getListCheckInHistoryFlexibleByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return checkInHistoryRepository.getListCheckInHistoryFlexibleByDepartmentId(departmentId, offset, size);
    }

    @Override
    public List<CheckInHistoryFixed> getListCheckInHistoryFixedByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return checkInHistoryRepository.getListCheckInHistoryFixedByDepartmentId(departmentId, offset, size);
    }

    @Override
    public List<CheckInHistoryFlexible> searchListHistoryFlexible(int departmentId, String username, String phoneNumber, String dateFilter) {
        return checkInHistoryRepository.searchListHistoryFlexible(departmentId, username, phoneNumber, dateFilter);
    }

    @Override
    public List<CheckInHistoryFixed> searchListHistoryFixed(int departmentId, String username, String phoneNumber, String dateFilter) {
        return checkInHistoryRepository.searchListHistoryFixed(departmentId, username, phoneNumber, dateFilter);
    }

    @Override
    public int getTotalListCheckInHistoryFlexibleByDepartmentId(int departmentId) {
        return checkInHistoryRepository.getTotalListCheckInHistoryFlexibleByDepartmentId(departmentId);
    }

    @Override
    public int getTotalListCheckInHistoryFixedByDepartmentId(int departmentId) {
        return checkInHistoryRepository.getTotalListCheckInHistoryFixedByDepartmentId(departmentId);
    }
}
