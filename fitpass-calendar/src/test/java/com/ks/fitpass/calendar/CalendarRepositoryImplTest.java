package com.ks.fitpass.calendar;

import com.ks.fitpass.calendar.repository.IQueryRepository;
import com.ks.fitpass.calendar.repository.impl.CalendarRepositoryImpl;
import com.ks.fitpass.department.entity.UserFeedback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalendarRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private CalendarRepositoryImpl calendarRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        calendarRepositoryImpl = new CalendarRepositoryImpl(jdbcTemplate);
    }

    @Test
        // Normal test case
    void testInsertCalendarFeedback_WhenParametersAreValidAndInsertIsSuccessful() {
        // Arrange
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(1);
        userFeedback.setDepartmentId(1);
        userFeedback.setRating(5);
        userFeedback.setComments("Great service!");
        userFeedback.setFeedbackTime(Timestamp.valueOf("2022-01-01 10:00:00").toLocalDateTime());

        int expected = 1; // Number of rows affected by the insert query

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IQueryRepository.INSERT_USER_FEEDBACK),
                        Mockito.eq(userFeedback.getUserId()),
                        Mockito.eq(userFeedback.getDepartmentId()),
                        Mockito.eq(userFeedback.getRating()),
                        Mockito.eq(userFeedback.getComments()),
                        Mockito.eq(userFeedback.getFeedbackTime())))
                .thenReturn(1);

        // Act
        int actual = calendarRepositoryImpl.insertCalendarFeedback(userFeedback);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
        // Abnormal test case
    void testInsertCalendarFeedback_WhenInsertFails() {
        // Arrange
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(1);
        userFeedback.setDepartmentId(1);
        userFeedback.setRating(5);
        userFeedback.setComments("Great service!");
        userFeedback.setFeedbackTime(Timestamp.valueOf("2022-01-01 10:00:00").toLocalDateTime());

        int expected = 0; // No rows affected as the insert fails

        // Mock the behavior of the jdbcTemplate.update() method
        Mockito.when(jdbcTemplate.update(
                        Mockito.eq(IQueryRepository.INSERT_USER_FEEDBACK),
                        Mockito.eq(userFeedback.getUserId()),
                        Mockito.eq(userFeedback.getDepartmentId()),
                        Mockito.eq(userFeedback.getRating()),
                        Mockito.eq(userFeedback.getComments()),
                        Mockito.eq(userFeedback.getFeedbackTime())))
                .thenReturn(0);

        // Act
        int actual = calendarRepositoryImpl.insertCalendarFeedback(userFeedback);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testInsertCalendarFeedback_NullUserFeedback() {
        // Arrange
        UserFeedback userFeedback = null;

        // Act and Assert
        assertThrows(NullPointerException.class, () -> {
            calendarRepositoryImpl.insertCalendarFeedback(userFeedback);
        });
    }

    // Additional test cases can be added here...

}