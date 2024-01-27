import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.order.entity.Order;
import com.ks.fitpass.order.entity.OrderDetails;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.order.service.OrderService;
import com.ks.fitpass.transaction.service.TransactionService;
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
    private OrderService orderService;

    @Mock
    private OrderDetailService orderDetailService;

    @Mock
    private BrandService brandService;

    @Mock
    private TransactionService transactionService;


    @Mock
    private Model model;
    
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
        when(mockSession.getAttribute("userInfo")).thenReturn(mock(User.class)); // Mock the user

        // Act
        String viewName = checkOutController.viewCheckout(org.mockito.Mockito.mock(org.springframework.ui.Model.class), idList, deptIdList, mockSession);

        // Assert
        assertEquals("check-out", viewName); // or whatever the expected view name is for an empty cart
    }
    @Test
    public void viewCheckout_exceptionThrown_errorView() {
        // Arrange
        Cart mockCart = new Cart();
        List<Integer> idList = List.of(1);
        List<Integer> deptIdList = List.of(3);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);
        when(mockSession.getAttribute("userInfo")).thenReturn(mock(User.class)); // Mock the user
        when(walletService.getBalanceByUserId(anyInt())).thenThrow(new RuntimeException("Test exception"));

        // Act
        String viewName = checkOutController.viewCheckout(mock(Model.class), idList, deptIdList, mockSession);

        // Assert
        assertEquals("error/error", viewName);

    }

    @Test
    void testPayment_WhenCartIsNull() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(mockSession.getAttribute("cart")).thenReturn(null);

        // Act
        String viewName = checkOutController.payment(model, null, 0.0, mockSession);

        // Assert
        assertEquals("error/error", viewName);
        verify(mockSession, times(1)).getAttribute("cart");
        verifyNoInteractions(orderService, orderDetailService, walletService, transactionService, model);

    }

    @Test
    void testPayment_WhenPlanIdisNull() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        when(mockSession.getAttribute("cart")).thenReturn(cart);

        // Act
        String viewName = checkOutController.payment(model, null, 0.0, mockSession);

        // Assert
        assertEquals("error/error", viewName);

    }

    @Test
    void testPayment_WhenOrderInsertFails() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        cart.addItem(new GymPlanDepartmentNameDto(), 1);
        when(mockSession.getAttribute("cart")).thenReturn(cart);
        when(orderService.insertOrder(any(Order.class))).thenReturn(0);

        // Act
        String viewName = checkOutController.payment(model, List.of("1;1"), 100.0, mockSession);

        // Assert
        assertEquals("error/error", viewName);

    }

    @Test
    void testPayment_NegativeUserBalance() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart mockCart = new Cart();
        mockCart.addItem(new GymPlanDepartmentNameDto(), 1);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);
        when(mockSession.getAttribute("userInfo")).thenReturn(new User()); // Mock the user
        when(walletService.getBalanceByUserId(anyInt())).thenReturn(-50.0);

        // Act
        String viewName = checkOutController.payment(model, List.of("1;1"), 100.0, mockSession);

        // Assert
        assertEquals("redirect:/inventory/view", viewName);
     }



}
