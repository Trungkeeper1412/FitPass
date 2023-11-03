package com.ks.fitpass.employee.service;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckInFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckOutFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeService {
    List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId);
    List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId);

    List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId);

    List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId);

    List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId);

    List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId);

    List<CheckInFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId);

    List<CheckInFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId);

    int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId);

    UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId);

    List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId);
}
