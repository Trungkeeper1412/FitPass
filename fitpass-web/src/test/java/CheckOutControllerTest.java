import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.CheckOutController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class CheckOutControllerTest {

    @Mock
    private HttpSession mockSession;

    @Mock
    private WalletService walletService;

    @InjectMocks
    private CheckOutController checkOutController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void viewCheckout_validCart_successfulView() {
        // Arrange
        Cart mockCart = new Cart();
        List<Integer> idList = List.of(1, 2);
        List<Integer> deptIdList = List.of(3, 4);
        User mockUser = new User();
        mockUser.setUserId(123);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);
        when(mockSession.getAttribute("userInfo")).thenReturn(mockUser);
        when(walletService.getBalanceByUserId(mockUser.getUserId())).thenReturn(100.0);

        // Act
        String viewName = checkOutController.viewCheckout(org.mockito.Mockito.mock(org.springframework.ui.Model.class), idList, deptIdList, mockSession);

        // Assert
        assertEquals("check-out", viewName);
        // Additional assertions based on your expectations
    }

    @Test
    public void viewCheckout_emptyCart_emptyView() {
        // Arrange
        Cart mockCart = new Cart();
        List<Integer> idList = List.of(1, 2);
        List<Integer> deptIdList = List.of(3, 4);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);

        // Act
        String viewName = checkOutController.viewCheckout(org.mockito.Mockito.mock(org.springframework.ui.Model.class), idList, deptIdList, mockSession);

        // Assert
        assertEquals("check-out", viewName); // or whatever the expected view name is for an empty cart
        // Additional assertions based on your expectations
    }

    @Test
    public void viewCheckout_userWithInsufficientBalance_insufficientBalanceView() {
        // Arrange
        Cart mockCart = new Cart();
        List<Integer> idList = List.of(1, 2);
        List<Integer> deptIdList = List.of(3, 4);
        User mockUser = new User();
        mockUser.setUserId(123);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);
        when(mockSession.getAttribute("userInfo")).thenReturn(mockUser);
        when(walletService.getBalanceByUserId(mockUser.getUserId())).thenReturn(10.0); // Assuming insufficient balance

        // Act
        String viewName = checkOutController.viewCheckout(org.mockito.Mockito.mock(org.springframework.ui.Model.class), idList, deptIdList, mockSession);

        // Assert
        assertEquals("insufficient-balance", viewName); // or whatever the expected view name is for insufficient balance
        // Additional assertions based on your expectations
    }


    // Add more test cases based on different scenarios, for example, when the departmentList is empty, etc.

}
