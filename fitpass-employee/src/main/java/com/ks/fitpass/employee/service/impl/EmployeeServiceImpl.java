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
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId) {
        return employeeRepository.getListNeedCheckInFlexibleByDepartmentId(departmentId);
    }

    @Override
    public List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId) {
        return employeeRepository.getListNeedCheckOutFlexibleByDepartmentId(departmentId);
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId) {
        return employeeRepository.searchListCheckInByUsername(username, departmentId);
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId) {
        return employeeRepository.searchListCheckInByPhoneNumber(phoneNumber, departmentId);
    }

    @Override
    public int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId) {
        return employeeRepository.insertToCheckInHistory(orderDetailId, statusKey, checkInTime, checkOutTime, totalCredit, empCheckinId);
    }

    @Override
    public UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId) {
        return employeeRepository.getUserReceiveMessage(orderDetailId);
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
    public List<CheckInFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId) {
        return employeeRepository.searchListCheckOutByUsername(username, departmentId);
    }

    @Override
    public List<CheckInFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId) {
        return employeeRepository.searchListCheckOutByPhoneNumber(phoneNumber, departmentId);
    }

    @Override
    public List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId) {
        return employeeRepository.getListNeedCheckInFixedByDepartmentId(departmentId);
    }

    @Override
    public List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId) {
        return employeeRepository.getListCheckedInFixedByDepartmentId(departmentId);
    }
}
