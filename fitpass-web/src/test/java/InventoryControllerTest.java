import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.web.controller.InventoryController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private HttpSession session;
    @Mock
    private Model model;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testViewInventorySuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        List<OrderDetails> itemList = new ArrayList<>();
        List<OrderDetails> itemListNotActive = new ArrayList<>();
        List<OrderDetails> itemListActive = new ArrayList<>();
        List<OrderDetails> itemListExpired = new ArrayList<>();

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(orderDetailService.getAllOrderItemByUserId(user.getUserId())).thenReturn(itemList);
        when(orderDetailService.getOrderDetailByStatusAndUserId(0, user.getUserId())).thenReturn(itemListNotActive);
        when(orderDetailService.getOrderDetailByStatusAndUserId(1, user.getUserId())).thenReturn(itemListActive);
        when(orderDetailService.getOrderDetailByStatusAndUserId(2, user.getUserId())).thenReturn(itemListExpired);

        // Act
        String result = inventoryController.viewInventory(model, session);

        // Assert
        verify(model, times(1)).addAttribute("itemList", itemList);
        verify(model, times(1)).addAttribute("itemListNotActive", itemListNotActive);
        verify(model, times(1)).addAttribute("itemListActive", itemListActive);
        verify(model, times(1)).addAttribute("itemListExpired", itemListExpired);
        assertEquals("user/inventory", result);
    }

    @Test
    void testViewInventoryWithDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(orderDetailService.getAllOrderItemByUserId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = inventoryController.viewInventory(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testViewInventoryWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(orderDetailService.getAllOrderItemByUserId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = inventoryController.viewInventory(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testViewInventoryWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(orderDetailService.getAllOrderItemByUserId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = inventoryController.viewInventory(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testViewInventoryWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(orderDetailService.getAllOrderItemByUserId(anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = inventoryController.viewInventory(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }


    @Test
    void testActiveItemSuccess() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenReturn(1);
        when(orderDetailService.updateOrderDetailItemStatus(any(), anyInt(), any(), anyInt())).thenReturn(1);

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        verify(session).setAttribute(eq("activeItemMSG"), anyString());
        assertEquals("redirect:/inventory/view", result);
    }

    @Test
    void testActiveItemFailure() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenReturn(1);
        when(orderDetailService.updateOrderDetailItemStatus(any(), anyInt(), any(), anyInt())).thenReturn(0);

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        verify(session).setAttribute(eq("activeItemMSG"), anyString());
        assertEquals("redirect:/inventory/view", result);
    }

    @Test
    void testActiveItemWithDuplicateKeyException() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenThrow(DuplicateKeyException.class);

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testActiveItemWithEmptyResultDataAccessException() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testActiveItemWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testActiveItemWithDataAccessException() {
        // Arrange
        when(orderDetailService.updateOrderDetailsUseStatus(anyInt(), eq("Chưa tập"))).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = inventoryController.activeItem(1, 7, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }


}
