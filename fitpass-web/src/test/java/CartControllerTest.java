import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.order.dto.AddToCartRequestDTO;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.web.controller.CartController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CartControllerTest {


    @Mock
    private GymPlanService gymPlanService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void addToCart_validRequest_successfulResponse() {
        // Arrange
        AddToCartRequestDTO requestDTO = new AddToCartRequestDTO(1, 2, 3); // Replace with actual values
        GymPlanDepartmentNameDto mockProduct = new GymPlanDepartmentNameDto(); // Replace with actual product
        HttpSession mockSession = mock(HttpSession.class);

        when(gymPlanService.getGymPlanByGymPlanId(anyInt(), anyInt())).thenReturn(mockProduct);

        // Act
        ResponseEntity<String> response = cartController.addToCart(requestDTO, mockSession);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Additional assertions based on your expectations
    }

    @Test
    public void addToCart_existingProductInCart_badRequest() {
        // Arrange
        int gymPlanId = 1;
        int quantity = 2;
        int departmentId = 3;

        // Replace with actual values
        AddToCartRequestDTO requestDTO = new AddToCartRequestDTO(gymPlanId, quantity, departmentId);
        GymPlanDepartmentNameDto mockProduct = new GymPlanDepartmentNameDto();
        mockProduct.setGymPlanId(1);
        mockProduct.setGymDepartmentId(3);
        HttpSession mockSession = mock(HttpSession.class);

        when(gymPlanService.getGymPlanByGymPlanId(anyInt(), anyInt())).thenReturn(mockProduct);

        // Mock existing product in the cart
        Cart mockCart = new Cart();
        mockCart.addItem(mockProduct, 1);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);

        // Act
        ResponseEntity<String> response = cartController.addToCart(requestDTO, mockSession);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Sản phẩm này đã add vào giỏ hàng", response.getBody());

        // Verify that the cart was not modified
        assertEquals(1, mockCart.getItems().size());
        // Additional assertions based on your expectations
    }


    @Test
    public void addToCart_invalidProductQuantity_badRequest() {
        // Arrange
        AddToCartRequestDTO requestDTO = new AddToCartRequestDTO(1, 0, 3); // Invalid quantity
        HttpSession mockSession = mock(HttpSession.class);

        // Act
        ResponseEntity<String> response = cartController.addToCart(requestDTO, mockSession);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Additional assertions based on your expectations
    }

    @Test
    public void addToCart_nullProduct_badRequest() {
        // Arrange
        AddToCartRequestDTO requestDTO = new AddToCartRequestDTO(1, 2, 3);
        HttpSession mockSession = mock(HttpSession.class);

        when(gymPlanService.getGymPlanByGymPlanId(anyInt(), anyInt())).thenReturn(null);

        // Act
        ResponseEntity<String> response = cartController.addToCart(requestDTO, mockSession);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Additional assertions based on your expectations
    }


    @Test
    public void addToCart_negativeQuantity_badRequest() {
        // Arrange
        AddToCartRequestDTO requestDTO = new AddToCartRequestDTO(1, -1, 3);
        HttpSession mockSession = mock(HttpSession.class);

        // Act
        ResponseEntity<String> response = cartController.addToCart(requestDTO, mockSession);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Additional assertions based on your expectations
    }

    @Test
    public void removeItem_validRequest_successfulRedirect() {
        // Arrange
        int gymPlanId = 1;
        int departmentId = 3;
        HttpSession mockSession = mock(HttpSession.class);

        Cart mockCart = new Cart();
        mockCart.addItem(new GymPlanDepartmentNameDto(), 1);
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);

        // Act
        String redirectResult = cartController.removeItem(gymPlanId, departmentId, mockSession);

        // Assert
        assertEquals("redirect:/cart/view", redirectResult);

    }
    @Test
    public void removeItem_exceptionThrown_redirectToErrorPage() {
        // Arrange
        int gymPlanId = 1;
        int departmentId = 3;
        HttpSession mockSession = mock(HttpSession.class);

        Cart mockCart = mock(Cart.class);
        doThrow(new RuntimeException("Test exception")).when(mockCart).removeItem(anyInt(), anyInt());
        when(mockSession.getAttribute("cart")).thenReturn(mockCart);

        // Act
        String redirectResult = cartController.removeItem(gymPlanId, departmentId, mockSession);

        // Assert
        assertEquals("redirect:/error/error", redirectResult);
    }


}
