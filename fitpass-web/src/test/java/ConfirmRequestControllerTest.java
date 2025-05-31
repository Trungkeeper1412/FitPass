
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;

import com.ks.fitpass.employee.dto.UpdateCheckInHistory;

import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.ConfirmRequestController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

class ConfirmRequestControllerTest {

    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private CheckInHistoryService checkInHistoryService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private WebSocketService webSocketService;

    @Mock
    private WalletService walletService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private ConfirmRequestController confirmRequestController;

    @Test
    void testPerformFlexibleCheckIn_CancelCheckIn() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        int orderDetailId = 1;
        int userIdSend = 123;
        int userIdReceive = 456;
        int departmentId = 789;
        String cancel = "yes";
        when(orderDetailService.getUserNameByOrderDetailId(orderDetailId)).thenReturn("John Doe");

        // Act
        ResponseEntity<Integer> responseEntity = confirmRequestController.performFlexibleCheckIn(
                orderDetailId, userIdSend, userIdReceive, departmentId, cancel);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody());

    }


    @Test
    void testPerformFlexibleCheckOut_CancelCheckOut() throws Exception {
        // Arrange
        MockitoAnnotations.initMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{ \"cancel\": \"Yes\", \"notification\": {\"userIdSend\": 123, \"userIdReceive\": 456, \"messageType\": \"\", \"message\": \"\", \"departmentId\": 789, \"orderDetailId\": 1, \"timeSend\": \"\" }, \"creditAfterPay\": 50.0, \"totalCredit\": 20.0, \"orderDetailId\": 1, \"checkInHistoryId\": 2, \"checkOutTime\": \"2023-01-01T00:00:00.000Z\" }";
        UpdateCheckInHistory updateCheckInHistory = objectMapper.readValue(jsonString, UpdateCheckInHistory.class);
        HttpSession session = mock(HttpSession.class);
        when(orderDetailService.getUserNameByOrderDetailId(updateCheckInHistory.getNotification().getOrderDetailId())).thenReturn("John Doe");

        // Act
        ResponseEntity<Integer> responseEntity = confirmRequestController.performFlexibleCheckOut(updateCheckInHistory, session);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody());
  }

}
