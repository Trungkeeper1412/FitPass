import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.entity.ItemInventory;
import com.ks.fitpass.department.service.ItemInventoryService;
import com.ks.fitpass.web.controller.ItemInventoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.*;

import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ItemInventoryControllerTest {

    @Mock
    private ItemInventoryService itemInventoryService;
    @Mock
    private HttpSession session;

    @InjectMocks
    private ItemInventoryController itemInventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToInventorySuccess() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(itemInventoryService.checkDuplicateItemInventory(anyInt(), anyInt())).thenReturn(false);

        // Act
        String result = itemInventoryController.addToInventory(1, session);

        // Assert
        assertEquals("redirect:/cart/view", result);
    }

    @Test
    void testAddToInventoryDuplicateItem() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(itemInventoryService.checkDuplicateItemInventory(anyInt(), anyInt())).thenReturn(true);

        // Act
        String result = itemInventoryController.addToInventory(1, session);

        // Assert
        assertEquals("redirect:/cart/view", result);
    }

    @Test
    void testAddToInventoryWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(null);

        // Act
        String result = itemInventoryController.addToInventory(1, session);

        // Assert
        assertEquals("redirect:/error", result);
    }

    @Test
    void testAddToInventoryWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(itemInventoryService.checkDuplicateItemInventory(anyInt(), anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = itemInventoryController.addToInventory(1, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testAddToInventoryWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(itemInventoryService.checkDuplicateItemInventory(anyInt(), anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = itemInventoryController.addToInventory(1, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

}
