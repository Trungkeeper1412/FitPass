package com.ks.fitpass.department.dto;

import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class UpdateGymOwnerDepartmentInfo {
    private int departmentId;

    @NotEmpty(message = "Vui lòng nhập tên cơ sở !")
    @Size(max = 150, message = "Tên cơ sở không được vượt quá 150 kí tự !")
    private String departmentName;

    @NotEmpty(message = "Vui lòng nhập địa chỉ !")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 kí tự !")
    private String departmentAddress;

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @Pattern(regexp = "^(0|84)(9|3|7|8|5)\\d{8,9}$", message = "Số điện thoại không đúng định dạng !")
    private String departmentContactNumber;

    @NotEmpty(message = "Vui lòng nhập mô tả thông tin cơ sở !")
    @Size(min = 2, max = 250, message = "Mô tả thông tin cơ sở phải nằm trong khoảng 2 đến 250 kí tự !")
    private String departmentDescription;

    @NotEmpty(message = "Vui lòng nhập sức chứa của cơ sở !")
    @Min(value = 1, message = "Sức chứa phải lớn hơn 0")
    private int capacity;

    @NotEmpty(message = "Vui lòng nhập diện tích của cơ sở !")
    @Min(value = 1, message = "Diện tích phải lớn hơn 0")
    private Double area;

    @NotEmpty(message = "Vui lòng chọn thành phố !")
    private String city;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa thứ 2")
    private String mondayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa thứ 2")
    private String mondayCloseTime;
    @NotEmpty(message = "Tuesday open time is required")
    private String tuesdayOpenTime;
    @NotEmpty(message = "Tuesday close time is required")
    private String tuesdayCloseTime;
    @NotEmpty(message = "Wednesday open time is required")
    private String wednesdayOpenTime;
    @NotEmpty(message = "Wednesday close time is required")
    private String wednesdayCloseTime;
    @NotEmpty(message = "Thursday open time is required")
    private String thursdayOpenTime;
    @NotEmpty(message = "Thursday close time is required")
    private String thursdayCloseTime;
    @NotEmpty(message = "Friday open time is required")
    private String fridayOpenTime;
    @NotEmpty(message = "Friday close time is required")
    private String fridayCloseTime;
    @NotEmpty(message = "Saturday open time is required")
    private String saturdayOpenTime;
    @NotEmpty(message = "Saturday close time is required")
    private String saturdayCloseTime;
    @NotEmpty(message = "Sunday open time is required")
    private String sundayOpenTime;
    @NotEmpty(message = "Sunday close time is required")
    private String sundayCloseTime;

    @AssertTrue(message = "Open time must be before close time")
    public boolean isValidSchedule() {
        // Thực hiện kiểm tra cho từng ngày ở đây
        if (isInvalidDaySchedule(mondayOpenTime, mondayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(tuesdayOpenTime, tuesdayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(wednesdayOpenTime, wednesdayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(thursdayOpenTime, thursdayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(fridayOpenTime, fridayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(saturdayOpenTime, saturdayCloseTime)) {
            return false;
        }
        if (isInvalidDaySchedule(sundayOpenTime, sundayCloseTime)) {
            return false;
        }
        return true;
    }

    private boolean isInvalidDaySchedule(String openTime, String closeTime) {
        // Thực hiện kiểm tra cho một cặp opentime và closetime của một ngày
        if (openTime == null || closeTime == null) {
            return false;
        }

        // Chuyển đổi chuỗi kiểu "time" thành LocalTime
        LocalTime openLocalTime = LocalTime.parse(openTime);
        LocalTime closeLocalTime = LocalTime.parse(closeTime);

        // Kiểm tra nếu openLocalTime lớn hơn hoặc bằng closeLocalTime
        return openLocalTime.isAfter(closeLocalTime);
    }
}
