package com.ks.fitpass.department.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalTime;

@Data
public class GODepartmentUpdateDetail {
    private int departmentId;


    @NotEmpty(message = "Vui lòng nhập địa chỉ !")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 kí tự !")
    private String departmentAddress;

    @NotEmpty(message = "Vui lòng nhập số điện thoại !")
    @ValidatePhone(message = "Số điện thoại không đúng định dạng !")
    private String departmentContactNumber;

    @NotEmpty(message = "Vui lòng nhập mô tả thông tin cơ sở !")
    @Size(min = 2, max = 700, message = "Mô tả thông tin cơ sở phải nằm trong khoảng 2 đến 700 kí tự !")
    private String departmentDescription;

    @NotNull(message = "Vui lòng nhập sức chứa của cơ sở !")
    @Min(value = 1, message = "Sức chứa phải lớn hơn 0")
    @Max(value = 10000, message = "Sức chứa không vượt quá 10000")
    private Integer capacity;

    @NotNull(message = "Vui lòng nhập diện tích của cơ sở !")
    @Min(value = 1, message = "Diện tích phải lớn hơn 0")
    @Min(value = 10000, message = "Diện tích không vượt quá 10000")
    private Double area;

    @NotEmpty(message = "Vui lòng chọn thành phố !")
    private String city;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Hai")
    private String mondayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Hai")
    private String mondayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Ba")
    private String tuesdayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Ba")
    private String tuesdayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Tư")
    private String wednesdayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Tư")
    private String wednesdayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Năm")
    private String thursdayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Năm")
    private String thursdayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Sáu")
    private String fridayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Sáu")
    private String fridayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Thứ Bảy")
    private String saturdayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Thứ Bảy")
    private String saturdayCloseTime;

    @NotEmpty(message = "Vui lòng chọn giờ mở cửa Chủ Nhật")
    private String sundayOpenTime;
    @NotEmpty(message = "Vui lòng chọn giờ đóng cửa Chủ Nhật")
    private String sundayCloseTime;

    private String listSelectedFlexGymPlanId;
    private String listSelectedFixedGymPlanId;
    private String listSelectedAmenitiesId;
    private String listSelectedFeaturesId;

    @NotEmpty(message = "Vui lòng nhập kinh độ !")
    @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$", message = "Kinh độ của bạn đang sai!")
    private double longitude;

    @NotEmpty(message = "Vui lòng nhập vĩ độ !")
    @Pattern(regexp = "^[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$", message = "Vĩ độ của bạn đang sai !")
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

    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = PhoneNumberUpdateDetailValidator.class)
    public @interface ValidatePhone {
        String message() default "Số điện thoại không đúng định dạng !";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
