package com.ks.fitpass.employee.repository;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckInFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckOutFlexibleDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeRepository {
    //////////////////////////////////////////////// Flexible Plan //////////////////////////
    List<CheckInFlexibleDTO> getListNeedCheckInFlexibleByDepartmentId(int departmentId, int offset, int size);

    List<CheckOutFlexibleDTO> getListNeedCheckOutFlexibleByDepartmentId(int departmentId, int offset, int size);

    Integer getTotalListNeedCheckInFlexibleByDepartmentId(int departmentId);

    Integer getTotalListNeedCheckOutFlexibleByDepartmentId(int departmentId);

    List<CheckInFlexibleDTO> searchListCheckInByUsername(String username, int departmentId, int offset, int size);

    List<CheckInFlexibleDTO> searchListCheckInByPhoneNumber(String phoneNumber, int departmentId, int offset, int size);

    List<CheckOutFlexibleDTO> searchListCheckOutByUsername(String username, int departmentId, int offset, int size);

    List<CheckOutFlexibleDTO> searchListCheckOutByPhoneNumber(String phoneNumber, int departmentId, int offset, int size);

    Integer countSearchListCheckInByUsername(String searchText, int departmentId);

    Integer countSearchListCheckInByPhoneNumber(String searchText, int departmentId);

    Integer countSearchListCheckOutByUsername(String searchText, int departmentId);

    Integer countSearchListCheckOutByPhoneNumber(String searchText, int departmentId);

    ////////////////////////////////////////////////// Fixed Plan ///////////////////////////////////////////////////////////////////////////
    List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId, int offset, int size);

    List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId, int offset, int size);

    Integer getTotalListCheckedInFixedByDepartmentId(int departmentId);

    Integer getTotalListNeedCheckInFixedByDepartmentId(int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId);

    ///////////////////////////////////////// Other method ///////////////////////////////////////////

    int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId);

    UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId);
}
