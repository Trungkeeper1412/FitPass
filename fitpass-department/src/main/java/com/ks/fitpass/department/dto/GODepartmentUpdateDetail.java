package com.ks.fitpass.department.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

@Data
public class GODepartmentUpdateDetail {
    private int departmentId;
    @NotEmpty(message = "Department Address is required")
    private String departmentAddress;
    @NotEmpty(message = "Department Contact Number is required")
    private String departmentContactNumber;
    @NotEmpty(message = "Department Description is required")
    private String departmentDescription;
    @Min(value = 1, message = "Capacity should be greater than 0")
    private int capacity;
    @Min(value = 1, message = "Area should be greater than 0")
    private Double area;
    @NotEmpty(message = "City is required")
    private String city;
    @NotEmpty(message = "Monday open time is required")
    private String mondayOpenTime;
    @NotEmpty(message = "Monday close time is required")
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

    private String listSelectedFlexGymPlanId;
    private String listSelectedFixedGymPlanId;
    private String listSelectedAmenitiesId;
    private String listSelectedFeaturesId;
    private double longitude;
    private double latitude;

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
