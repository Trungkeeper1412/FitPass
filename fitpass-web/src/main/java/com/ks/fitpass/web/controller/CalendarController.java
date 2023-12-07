package com.ks.fitpass.web.controller;

import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDetailDTO;
import com.ks.fitpass.calendar.service.CalendarService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.entity.UserFeedback;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/view")
    public String viewCalendar() {
        try {
            return "user/calendar";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/getListCheckIn")
    public ResponseEntity<List<CheckInDataCalendarDTO>> getListCheckIn(HttpSession session) {
        try {
            User u = (User) session.getAttribute("userInfo");
            // lấy ra danh sách check_in_history theo getUserId
            List<CheckInDataCalendarDTO> list = calendarService.getListCheckInCalendarByUserId(u.getUserId());
            return ResponseEntity.ok(list);
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getDetail")
    public ResponseEntity<CheckInDataCalendarDetailDTO> getListCheckIn(@RequestParam("id") int checkInHistoryId, HttpSession session) {
        try {
            User u = (User) session.getAttribute("userInfo");
            CheckInDataCalendarDetailDTO checkInDataCalendarDetailDTO = calendarService.getDetailByUserIdAndHistoryId(u.getUserId(), checkInHistoryId);
            return ResponseEntity.ok(checkInDataCalendarDetailDTO);
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/submitReview")
    public String submitReview(
            @RequestParam("checkInHistoryId") int checkInHistoryId,
            @RequestParam("departmentId") int departmentId,
            @RequestParam(value = "rating", defaultValue = "5") int rating,
            @RequestParam("thoughts") String comments,
            HttpSession session) {
        try {
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
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @PostMapping("/updateReview")
    public String updateReview(@RequestParam("checkInHistoryId") int checkInHistoryId,
                               @RequestParam("feedbackId") int feedbackId,
                               @RequestParam("departmentId") int departmentId,
                               @RequestParam("rating") int rating,
                               @RequestParam("thoughts") String comments) {
        try {
            UserFeedback userFeedback = new UserFeedback();
            userFeedback.setFeedbackId(feedbackId);
            userFeedback.setRating(rating);
            userFeedback.setComments(comments);
            userFeedback.setFeedbackTime(LocalDateTime.now());
            calendarService.updateCalendarFeedbackRating(feedbackId, userFeedback);
            return "redirect:/calendar/view";
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return "error/duplicate-key-error";
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return "error/incorrect-result-size-error";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/getFeedback")
    public ResponseEntity<CalendarFeedbackDTO> getFeedback(@RequestParam("id") int checkInHistoryId) {
        try {
            CalendarFeedbackDTO calendarFeedbackDTO = calendarService.getFeedbackByCheckInHistoryId(checkInHistoryId);
            return ResponseEntity.ok(calendarFeedbackDTO);
        } catch (DuplicateKeyException ex) {
            // Handle duplicate key violation
            logger.error("DuplicateKeyException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle incorrect result size
            logger.error("IncorrectResultSizeDataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return ResponseEntity.badRequest().build();
        }
    }
}
