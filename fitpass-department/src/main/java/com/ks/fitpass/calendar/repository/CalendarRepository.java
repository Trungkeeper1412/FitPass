package com.ks.fitpass.calendar.repository;

import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDetailDTO;
import com.ks.fitpass.department.entity.UserFeedback;

import java.util.List;

public interface CalendarRepository {
    List<CheckInDataCalendarDTO> getListCheckInCalendarByUserId(int userId);

    CheckInDataCalendarDetailDTO getDetailByUserIdAndHistoryId(int userId, int checkInHistoryId);

    int insertCalendarFeedback(UserFeedback userFeedback);

    CalendarFeedbackDTO getFeedbackByCheckInHistoryId(int checkInHistoryId);

    int updateCalendarFeedbackRating(int feedBackId, UserFeedback userFeedback);

    Integer getLastInsertFeedbackId();

    int insertCheckInHistoryFeedback(int feedbackId, int checkInHistoryId);
}
