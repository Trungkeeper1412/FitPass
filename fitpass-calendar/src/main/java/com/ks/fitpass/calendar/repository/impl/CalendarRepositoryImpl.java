package com.ks.fitpass.calendar.repository.impl;

import com.ks.fitpass.calendar.dto.CalendarFeedbackDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDTO;
import com.ks.fitpass.calendar.dto.CheckInDataCalendarDetailDTO;
import com.ks.fitpass.calendar.repository.CalendarRepository;
import com.ks.fitpass.calendar.repository.IQueryRepository;
import com.ks.fitpass.department.entity.UserFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository,IQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CalendarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CheckInDataCalendarDTO> getListCheckInCalendarByUserId(int userId) {
        return jdbcTemplate.query(IQueryRepository.getListCheckInCalendarByUserId, (rs, rowNum) -> {
            CheckInDataCalendarDTO checkInDataCalendarDTO = new CheckInDataCalendarDTO();
            checkInDataCalendarDTO.setCheckInHistoryId(rs.getInt("check_in_history_id"));
            checkInDataCalendarDTO.setGymDepartmentName(rs.getString("gym_department_name"));
            checkInDataCalendarDTO.setCheckInTime(rs.getTimestamp("check_in_time"));
            return checkInDataCalendarDTO;
        }, userId);
    }


    @Override
    public CheckInDataCalendarDetailDTO getDetailByUserIdAndHistoryId(int userId, int checkInHistoryId) {
        return jdbcTemplate.queryForObject(IQueryRepository.getDetailByUserIdAndHistoryId, (rs, rowNum) -> {
            CheckInDataCalendarDetailDTO checkInDataCalendarDetailDTO = new CheckInDataCalendarDetailDTO();
            checkInDataCalendarDetailDTO.setCheckInHistoryId(rs.getInt("check_in_history_id"));
            checkInDataCalendarDetailDTO.setGymDepartmentId(rs.getInt("gym_department_id"));
            checkInDataCalendarDetailDTO.setGymDepartmentName(rs.getString("gym_department_name"));
            checkInDataCalendarDetailDTO.setAddress(rs.getString("address"));
            checkInDataCalendarDetailDTO.setGymPlanName(rs.getString("gym_plan_name"));
            checkInDataCalendarDetailDTO.setCheckInTime(rs.getTimestamp("check_in_time"));
            checkInDataCalendarDetailDTO.setFeedBackId(rs.getInt("feedback_id"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Timestamp datetime = rs.getTimestamp("check_in_time");

            String date = dateFormat.format(datetime);
            String time = timeFormat.format(datetime);

            checkInDataCalendarDetailDTO.setDate(date);
            checkInDataCalendarDetailDTO.setTime(time);
            return checkInDataCalendarDetailDTO;
        }, userId, checkInHistoryId);
    }

    @Override
    public CalendarFeedbackDTO getFeedbackByCheckInHistoryId(int checkInHistoryId) {
        return jdbcTemplate.queryForObject(IQueryRepository.getFeedbackByCheckInHistoryId, (rs, rowNum) -> {
            CalendarFeedbackDTO calendarFeedbackDTO = new CalendarFeedbackDTO();
            calendarFeedbackDTO.setFeedbackId(rs.getInt("feedback_id"));
            calendarFeedbackDTO.setUserId(rs.getInt("user_id"));
            calendarFeedbackDTO.setRating(rs.getInt("rating"));
            calendarFeedbackDTO.setDepartmentId(rs.getInt("department_id"));
            calendarFeedbackDTO.setComments(rs.getString("comments"));
            calendarFeedbackDTO.setFeedbackTime(rs.getTimestamp("feedback_time"));
            calendarFeedbackDTO.setFeedbackStatus(rs.getInt("feedback_status"));
            return calendarFeedbackDTO;
        }, checkInHistoryId);
    }

    @Override
    public int insertCalendarFeedback(UserFeedback userFeedback) {
        return jdbcTemplate.update(IQueryRepository.insertFeedback, userFeedback.getUserId(), userFeedback.getDepartmentId(), userFeedback.getRating(), userFeedback.getComments(), userFeedback.getFeedbackTime());
    }

    @Override
    public int updateCalendarFeedbackRating(int feedBackId, UserFeedback userFeedback) {
        return jdbcTemplate.update(IQueryRepository.updateFeedBackRating, userFeedback.getRating(), userFeedback.getComments(), userFeedback.getFeedbackTime(), userFeedback.getFeedbackId());
    }

    @Override
    public Integer getLastInsertFeedbackId() {
        return jdbcTemplate.queryForObject(IQueryRepository.getLastInsertFeedbackId, Integer.class);
    }

    @Override
    public int insertCheckInHistoryFeedback(int feedbackId, int checkInHistoryId) {
        return jdbcTemplate.update(IQueryRepository.INSERT_CHECK_IN_HISTORY_FEEDBACK, feedbackId, checkInHistoryId);
    }
}
