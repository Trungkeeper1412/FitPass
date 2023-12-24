package com.ks.fitpass.employee.service.impl;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckInFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckOutFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.employee.repository.EmployeeRepository;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return employeeRepository.getListNeedCheckInFlexibleByDepartmentId(departmentId, offset, size);
    }

    @Override
    public List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return employeeRepository.getListNeedCheckOutFlexibleByDepartmentId(departmentId, offset, size);
    }

    @Override
    public int getTotalListNeedCheckInFlexibleByDepartmentId(int departmentId) {
        Integer count = employeeRepository.getTotalListNeedCheckInFlexibleByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int getTotalListNeedCheckOutFlexibleByDepartmentId(int departmentId) {
        Integer count = employeeRepository.getTotalListNeedCheckOutFlexibleByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        List<CheckInFlexibleDTO> results = employeeRepository.searchListCheckInByUsername(username, departmentId, offset, size);
        return results != null ? results : Collections.emptyList();
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        List<CheckInFlexibleDTO> results = employeeRepository.searchListCheckInByPhoneNumber(phoneNumber, departmentId, offset, size);
        return results != null ? results : Collections.emptyList();
    }

    @Override
    public List<CheckOutFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        List<CheckOutFlexibleDTO> results = employeeRepository.searchListCheckOutByUsername(username, departmentId, offset, size);
        return results != null ? results : Collections.emptyList();
    }

    @Override
    public List<CheckOutFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        List<CheckOutFlexibleDTO> results = employeeRepository.searchListCheckOutByPhoneNumber(phoneNumber, departmentId, offset, size);
        return results != null ? results : Collections.emptyList();
    }

    @Override
    public int countSearchListCheckInByUsername(String searchText, int departmentId) {
        Integer count = employeeRepository.countSearchListCheckInByUsername(searchText,departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int countSearchListCheckInByPhoneNumber(String searchText, int departmentId) {
        Integer count = employeeRepository.countSearchListCheckInByPhoneNumber(searchText, departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int countSearchListCheckOutByUsername(String searchText, int departmentId) {
        Integer count = employeeRepository.countSearchListCheckOutByUsername(searchText, departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int countSearchListCheckOutByPhoneNumber(String searchText, int departmentId) {
        Integer count = employeeRepository.countSearchListCheckOutByPhoneNumber(searchText, departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId) {
        return employeeRepository.searchListCheckInFixedByUsername(username, departmentId);
    }

    @Override
    public List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId) {
        return employeeRepository.searchListCheckInFixedByPhoneNumber(phoneNumber, departmentId);
    }

    @Override
    public List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId) {
        return employeeRepository.searchListCheckedInFixedByUsername(username, departmentId);
    }

    @Override
    public List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId) {
        return employeeRepository.searchListCheckedInFixedByPhoneNumber(phoneNumber, departmentId);
    }

    @Override
    public List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return employeeRepository.getListNeedCheckInFixedByDepartmentId(departmentId, offset, size);
    }

    @Override
    public List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId, int page, int size) {
        int offset = (page - 1) * size;
        return employeeRepository.getListCheckedInFixedByDepartmentId(departmentId, offset, size);
    }

    @Override
    public int getTotalListNeedCheckInFixedByDepartmentId(int departmentId) {
        Integer count = employeeRepository.getTotalListNeedCheckInFixedByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int getTotalListCheckedInFixedByDepartmentId(int departmentId) {
        Integer count = employeeRepository.getTotalListCheckedInFixedByDepartmentId(departmentId);
        return (count != null) ? count : 0;
    }

    @Override
    public int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId) {
        return employeeRepository.insertToCheckInHistory(orderDetailId, statusKey, checkInTime, checkOutTime, totalCredit, empCheckinId);
    }

    @Override
    public UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId) {
        return employeeRepository.getUserReceiveMessage(orderDetailId);
    }
}
