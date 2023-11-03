package com.ks.fitpass.employee.entity;

import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.order.repository.OrderDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class UpdateNewDayConfig {
    private static final Logger logger = LoggerFactory.getLogger(UpdateNewDayConfig.class);
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CheckInHistoryService checkInHistoryService;

    @Scheduled(cron = "0 0 0 * * ?") // Mỗi ngày vào lúc 00:00:00
    public void updateOrderDetailStatus() {
        logger.info("Update row: " + orderDetailRepository.updateAllFixedToCheckIn());

        // Lấy ra tất cả những id check in history cần được check out
        List<Integer> listId = checkInHistoryService.getListCheckInHistoryIdNeedCheckOut();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for(Integer id : listId) {
            checkInHistoryService.updateCheckOutTimeAndCredit(id, now, 0);
        }
//        checkInHistoryService.updateCheckOutTimeAndCredit()
    }
}
