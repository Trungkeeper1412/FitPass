package com.ks.fitpass.calendar.service.impl;

import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDetailDTO;
import com.ks.fitpass.calendar.repository.CalendarRepository;
import com.ks.fitpass.calendar.service.CalendarService;
import com.ks.fitpass.department.entity.UserFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;

    @Override
    public List<CheckInDataCalendarDTO> getListCheckInCalendarByUserId(int userId) {
        return calendarRepository.getListCheckInCalendarByUserId(userId);
    }

    @Override
    public CheckInDataCalendarDetailDTO getDetailByUserIdAndHistoryId(int userId, int checkInHistoryId) {
        return calendarRepository.getDetailByUserIdAndHistoryId(userId, checkInHistoryId);
    }

    @Override
    public int insertCalendarFeedback(UserFeedback userFeedback) {
        return calendarRepository.insertCalendarFeedback(userFeedback);
    }

    @Override
    public CalendarFeedbackDTO getFeedbackByCheckInHistoryId(int checkInHistoryId) {
        return calendarRepository.getFeedbackByCheckInHistoryId(checkInHistoryId);
    }

    @Override
    public int updateCalendarFeedbackRating(int feedBackId, UserFeedback userFeedback) {
        return calendarRepository.updateCalendarFeedbackRating(feedBackId, userFeedback);
    }

    @Override
    public Integer getLastInsertFeedbackId() {
        return calendarRepository.getLastInsertFeedbackId();
    }

    @Override
    public int insertCheckInHistoryFeedback(int feedbackId, int checkInHistoryId) {
        return calendarRepository.insertCheckInHistoryFeedback(feedbackId, checkInHistoryId);
    }
}
