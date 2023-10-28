package com.ks.fitpass.employee.service;

import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;

import java.sql.Timestamp;
import java.util.List;

public interface EmployeeService {

    List<CheckInFixedDTO> getListNeedCheckInFixedByDepartmentId(int departmentId);

    List<CheckedInFixedDTO> getListCheckedInFixedByDepartmentId(int departmentId);
    int insertToCheckInHistory(int orderDetailId, int statusKey, Timestamp checkInTime, Timestamp checkOutTime, double totalCredit, int empCheckinId);

    UserReceiveMessageDTO getUserReceiveMessage(int orderDetailId);

    List<CheckInFixedDTO> searchListCheckInFixedByUsername(String username, int departmentId);

    List<CheckInFixedDTO> searchListCheckInFixedByPhoneNumber(String phoneNumber, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByUsername(String username, int departmentId);

    List<CheckedInFixedDTO> searchListCheckedInFixedByPhoneNumber(String phoneNumber, int departmentId);
}
