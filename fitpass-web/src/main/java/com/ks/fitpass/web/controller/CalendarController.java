package com.ks.fitpass.web.controller;

import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDetailDTO;
import com.ks.fitpass.calendar.service.CalendarService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.entity.UserFeedback;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    @GetMapping("/view")
    public String viewCalendar() {
        return "user/calendar";
    }

    @GetMapping("/getListCheckIn")
    public ResponseEntity<List<CheckInDataCalendarDTO>> getListCheckIn(HttpSession session) {
        User u = (User) session.getAttribute("userInfo");
        List<CheckInDataCalendarDTO> list = calendarService.getListCheckInCalendarByUserId(u.getUserId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getDetail")
    public ResponseEntity<CheckInDataCalendarDetailDTO> getListCheckIn(@RequestParam("id") int checkInHistoryId, HttpSession session) {
        User u = (User) session.getAttribute("userInfo");
        CheckInDataCalendarDetailDTO checkInDataCalendarDetailDTO = calendarService.getDetailByUserIdAndHistoryId(u.getUserId(), checkInHistoryId);
        return ResponseEntity.ok(checkInDataCalendarDetailDTO);
    }

    @PostMapping("/submitReview")
    public String submitReview(@RequestParam("checkInHistoryId") int checkInHistoryId,
                               @RequestParam("departmentId") int departmentId,
                               @RequestParam("rating") int rating,
                               @RequestParam("thoughts") String comments,
                               HttpSession session){
        User u = (User) session.getAttribute("userInfo");

        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(u.getUserId());
        userFeedback.setDepartmentId(departmentId);
        userFeedback.setRating(rating);
        userFeedback.setComments(comments);
        userFeedback.setFeedbackTime(LocalDateTime.now());
        calendarService.insertCalendarFeedback(userFeedback);

        // lấy id thằng feedbackId Mà vừa insert vào bảng user user_feedback
        int feedbackId = calendarService.getLastInsertFeedbackId();

        // nhét nó lại vào bảng check_in_history
        calendarService.insertCheckInHistoryFeedback(feedbackId, checkInHistoryId);
        return "redirect:/calendar/view";
    }

    @PostMapping("/updateReview")
    public String updateReview(@RequestParam("checkInHistoryId") int checkInHistoryId,
                               @RequestParam("feedbackId") int feedbackId,
                               @RequestParam("departmentId") int departmentId,
                               @RequestParam("rating") int rating,
                               @RequestParam("thoughts") String comments,
                               HttpSession session){
        User user = (User) session.getAttribute("userInfo");

        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setFeedbackId(feedbackId);
        userFeedback.setRating(rating);
        userFeedback.setComments(comments);
        userFeedback.setFeedbackTime(LocalDateTime.now());
        calendarService.updateCalendarFeedbackRating(feedbackId, userFeedback);
        return "redirect:/calendar/view";
    }

    @GetMapping("/getFeedback")
    public ResponseEntity<CalendarFeedbackDTO> getFeedback(@RequestParam("id") int checkInHistoryId) {
        CalendarFeedbackDTO calendarFeedbackDTO = calendarService.getFeedbackByCheckInHistoryId(checkInHistoryId);
        return ResponseEntity.ok(calendarFeedbackDTO);
    }
}
