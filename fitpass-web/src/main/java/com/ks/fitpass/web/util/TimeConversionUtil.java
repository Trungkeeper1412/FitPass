package com.ks.fitpass.web.util;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeConversionUtil {

    public String convertTimeStringToTimeValue(String timeString) {
        // Định dạng của chuỗi thời gian
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        try {
            // Chuyển đổi chuỗi thời gian thành đối tượng Date
            Date date = sdf.parse(timeString);

            // Lấy giờ và phút từ đối tượng Date
            int hours = date.getHours();
            int minutes = date.getMinutes();

            // Tạo đối tượng Time từ giờ và phút
            Time time = new Time(hours, minutes, 0);

            // Trả về giá trị biến kiểu Time dưới dạng chuỗi "HH:mm"
            return time.toString().substring(0, 5);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertTo12HourFormat(String inputTime) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");

            LocalTime time = LocalTime.parse(inputTime, inputFormatter);
            String outputTime = time.format(outputFormatter);

            return outputTime;
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            return null;
        }
    }
}
