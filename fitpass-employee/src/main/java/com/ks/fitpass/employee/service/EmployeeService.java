package com.ks.fitpass.employee.service;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckInFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckOutFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeService {
    //////////////////////////////////////////////// Flexible Plan //////////////////////////
    List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId, int page, int size);
    List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId, int page, int size);
    int getTotalListNeedCheckInFlexibleByDepartmentId(int departmentId);
    int getTotalListNeedCheckOutFlexibleByDepartmentId(int departmentId);

    List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId, int page, int size);

    List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId, int page, int size);

    List<CheckOutFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId, int page, int size);

    List<CheckOutFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId, int page, int size);

    ////////////////////////////////////////////////// Fixed Plan //////////////////////////
    List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId, int page, int size);

    List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId, int page, int size);

    int getTotalListNeedCheckInFixedByDepartmentId(int departmentId);

    int getTotalListCheckedInFixedByDepartmentId(int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId);

    ///////////////////////////////////////// Other method ///////////////////////////////////////////

    int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId);

    UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId);
}
