import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.notification.dto.NotificationPage;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.web.controller.NotificationController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Test
    void testPerformSeenNotificationSuccess() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        int notificationId = 1;
        int expectedStatus = 1;

        when(notificationService.updateStatusNotificationById(notificationId, 1)).thenReturn(expectedStatus);

        // Act
        ResponseEntity<Integer> responseEntity = notificationController.performSeenNotification(notificationId);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedStatus, responseEntity.getBody());
    }

    @Test
    void testPerformSeenNotificationNotFound() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        int notificationId = 1;

        when(notificationService.updateStatusNotificationById(notificationId, 1)).thenReturn(0);

        // Act
        ResponseEntity<Integer> responseEntity = notificationController.performSeenNotification(notificationId);

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetAllNotificationsForUser() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock data for notifications
        List<Notification> mockNotifications = Arrays.asList(
                Notification.builder().notificationId(1).message("Message 1").timeSend(new Timestamp(System.currentTimeMillis())).build(),
                Notification.builder().notificationId(2).message("Message 2").timeSend(new Timestamp(System.currentTimeMillis())).build()
        );

        when(notificationService.getAllNotificationForUser(user.getUserId(), 1, 4)).thenReturn(mockNotifications);
        when(notificationService.getTotalNotificationsForUser(user.getUserId())).thenReturn(2);

        // Act
        ResponseEntity<NotificationPage> responseEntity = notificationController.getAllNotificationsForUser(session, 1, 4);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        NotificationPage notificationPage = responseEntity.getBody();
        assertEquals(mockNotifications, notificationPage.getNotifications());
        assertEquals(1, notificationPage.getCurrentPage());
    }

    @Test
    void testGetTotalPagesForUser() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock data for total notifications
        int totalNotifications = 10;

        when(notificationService.getTotalNotificationsForUser(user.getUserId())).thenReturn(totalNotifications);

        // Act
        ResponseEntity<Integer> responseEntity = notificationController.getTotalPagesForUser(session, 4);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(3, responseEntity.getBody()); // Assuming pageSize is 4 and totalNotifications is 10
    }

    @Test
    void testGetTotalUnseenNumberForUser() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock data for total unseen notifications
        int totalUnseenNotifications = 5;

        when(notificationService.getNumberOfUnseenNotification(user.getUserId())).thenReturn(totalUnseenNotifications);

        // Act
        ResponseEntity<Integer> responseEntity = notificationController.getTotalUnseenNumberForUser(session);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(5, responseEntity.getBody()); // Assuming there are 5 unseen notifications
    }


    @Test
    void testGet3NewestUnseenNotificationsForUser() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock data for 3 newest unseen notifications
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());

        when(notificationService.get3NewestUnseenNotificationForUser(user.getUserId())).thenReturn(notifications);

        // Act
        ResponseEntity<Map<String, Object>> responseEntity = notificationController.get3NewestUnseenNotificationsForUser(session);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(3, ((List<?>) responseEntity.getBody().get("notifications")).size());
        assertEquals(3, responseEntity.getBody().get("size"));
    }

    @Test
    void testGetAllNotificationsForEmployee() {
        // Arrange
        NotificationService notificationService = mock(NotificationService.class);
        NotificationController notificationController = new NotificationController(notificationService);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock data for notifications
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());

        when(notificationService.getAllNotificationForEmployee(user.getUserId())).thenReturn(notifications);

        // Act
        ResponseEntity<List<Notification>> responseEntity = notificationController.getAllNotificationsForEmployee(session);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(3, responseEntity.getBody().size());
    }
}
