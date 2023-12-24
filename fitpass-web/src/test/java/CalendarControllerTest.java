import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.service.CalendarService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.entity.UserFeedback;
import com.ks.fitpass.web.controller.CalendarController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalendarControllerTest {

    @Mock
    private CalendarService calendarService;

    @Mock
    private Logger logger;  // Ensure this is a mock
    @InjectMocks
    private CalendarController calendarController;

    @Mock
    private HttpSession session;



    @Test
    void testGetListCheckInSuccess() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        when(session.getAttribute("userInfo")).thenReturn(user);

        List<CheckInDataCalendarDTO> expectedList = Arrays.asList(new CheckInDataCalendarDTO(), new CheckInDataCalendarDTO());
        when(calendarService.getListCheckInCalendarByUserId(user.getUserId())).thenReturn(expectedList);

        ResponseEntity<List<CheckInDataCalendarDTO>> responseEntity = calendarController.getListCheckIn(session);

        assertEquals(expectedList, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetListCheckInDuplicateKeyException() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        when(session.getAttribute("userInfo")).thenReturn(user);

        doThrow(DuplicateKeyException.class).when(calendarService).getListCheckInCalendarByUserId(user.getUserId());

        ResponseEntity<List<CheckInDataCalendarDTO>> responseEntity = calendarController.getListCheckIn(session);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }



    @Test
    void testGetListCheckInIncorrectResultSizeDataAccessException() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        when(session.getAttribute("userInfo")).thenReturn(user);

        doThrow(IncorrectResultSizeDataAccessException.class).when(calendarService).getListCheckInCalendarByUserId(user.getUserId());

        ResponseEntity<List<CheckInDataCalendarDTO>> responseEntity = calendarController.getListCheckIn(session);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void testUpdateReview() {
        // Arrange
        MockitoAnnotations.initMocks(this);

        int checkInHistoryId = 1;
        int feedbackId = 2;
        int departmentId = 3;
        int rating = 4;
        String comments = "Test comments";

        // Act
        String result = calendarController.updateReview(
                checkInHistoryId,
                feedbackId,
                departmentId,
                rating,
                comments
        );

        // Assert
        assertEquals("redirect:/calendar/view?updateSuccess=true", result);

    }

    @Test
    void testUpdateReviewDuplicateKeyException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        doThrow(DuplicateKeyException.class).when(calendarService).updateCalendarFeedbackRating(anyInt(), any(UserFeedback.class));

        // Act
        String result = calendarController.updateReview(1, 2, 3, 4, "Test comments");

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testUpdateReviewEmptyResultDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        doThrow(EmptyResultDataAccessException.class).when(calendarService).updateCalendarFeedbackRating(anyInt(), any(UserFeedback.class));

        // Act
        String result = calendarController.updateReview(1, 2, 3, 4, "Test comments");

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testUpdateReviewIncorrectResultSizeDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        doThrow(IncorrectResultSizeDataAccessException.class).when(calendarService).updateCalendarFeedbackRating(anyInt(), any(UserFeedback.class));

        // Act
        String result = calendarController.updateReview(1, 2, 3, 4, "Test comments");

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testUpdateReviewDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        doThrow(new CustomDataAccessException("Simulated DataAccessException")).when(calendarService).updateCalendarFeedbackRating(anyInt(), any(UserFeedback.class));

        // Act
        String result = calendarController.updateReview(1, 2, 3, 4, "Test comments");

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testGetFeedback() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(calendarService.getFeedbackByCheckInHistoryId(anyInt())).thenReturn(new CalendarFeedbackDTO());

        // Act
        ResponseEntity<CalendarFeedbackDTO> responseEntity = calendarController.getFeedback(1);

        // Assert

        assertEquals(200, responseEntity.getStatusCodeValue());


    }

    @Test
    void testGetFeedbackDuplicateKeyException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(calendarService.getFeedbackByCheckInHistoryId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<CalendarFeedbackDTO> result = calendarController.getFeedback(1);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testGetFeedbackEmptyResultDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(calendarService.getFeedbackByCheckInHistoryId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<CalendarFeedbackDTO> result = calendarController.getFeedback(1);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testGetFeedbackIncorrectResultSizeDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(calendarService.getFeedbackByCheckInHistoryId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<CalendarFeedbackDTO> result = calendarController.getFeedback(1);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testGetFeedbackDataAccessException() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(calendarService.getFeedbackByCheckInHistoryId(anyInt())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<CalendarFeedbackDTO> result = calendarController.getFeedback(1);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }


}
