import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ks.fitpass.department.dto.GymPlanDepartmentNameDto;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.order.dto.AddToCartRequestDTO;
import com.ks.fitpass.order.dto.CartUpdateRequestDto;
import com.ks.fitpass.order.entity.cart.Cart;
import com.ks.fitpass.order.entity.cart.CartItem;
import com.ks.fitpass.web.controller.CartController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class CartControllerTest {
    @Mock
    private HttpSession session;
    @Mock
    private Model model;

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
        assertEquals("Sản phẩm này đã có sẵn vào giỏ hàng", response.getBody());

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

    @Test
    void testViewCartWithEmptyCart() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(session.getAttribute("cart")).thenReturn(null);

        // Act
        String result = cartController.viewCart(model, session);

        // Assert
        assertEquals("shopping-cart", result);
        verify(model, times(1)).addAttribute("departmentList", new ArrayList<>());
        verify(model, times(1)).addAttribute("cartItems", new ArrayList<>());


    }

    @Test
    void testViewCartWithNonEmptyCart() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();

        when(session.getAttribute("cart")).thenReturn(cart);

        // Act
        String result = cartController.viewCart(model, session);

        // Assert
        assertEquals("shopping-cart", result);

    }

//    @Test
//    void testGetQuantityInCart_WhenCartExists() {
//        // Arrange
//        MockitoAnnotations.initMocks(this);
//        Cart cart = new Cart();
//        cart.addItem(new GymPlanDepartmentNameDto() , 2);
//        when(session.getAttribute("cart")).thenReturn(cart);
//
//        // Act
//        ResponseEntity<Integer> response = cartController.getQuantityInCart(session);
//
//        // Assert
//        assertEquals(ResponseEntity.ok(1), response);
//
//    }
//
//    @Test
//    void testGetQuantityInCart_WhenCartDoesNotExist() {
//        // Arrange
//        MockitoAnnotations.initMocks(this);
//        when(session.getAttribute("cart")).thenReturn(null);
//
//        // Act
//        ResponseEntity<Integer> response = cartController.getQuantityInCart(session);
//
//        // Assert
//        assertEquals(ResponseEntity.ok(0), response);
//
//    }

    @Test
    void testViewCart_WhenCartExistsWithItems() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        cart.addItem(new GymPlanDepartmentNameDto(), 2);
        when(session.getAttribute("cart")).thenReturn(cart);

        // Act
        String viewName = cartController.viewCart(model, session);

        // Assert
        assertEquals("shopping-cart", viewName);

    }

    @Test
    void testViewCart_WhenCartExistsWithoutItems() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        when(session.getAttribute("cart")).thenReturn(cart);

        // Act
        String viewName = cartController.viewCart(model, session);

        // Assert
        assertEquals("shopping-cart", viewName);
        verify(session, times(1)).getAttribute("cart");
        verify(model, times(1)).addAttribute(eq("departmentList"), anyList());
        verify(model, times(1)).addAttribute(eq("cartItems"), anyList());
        verify(session, never()).setAttribute(eq("cart"), any(Cart.class));
        verifyNoMoreInteractions(session, model);
    }

    @Test
    void testViewCart_WhenExceptionOccurs() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(session.getAttribute("cart")).thenThrow(new RuntimeException("Test Exception"));

        // Act
        String viewName = cartController.viewCart(model, session);

        // Assert
        assertEquals("error/error", viewName);
        verify(session, times(1)).getAttribute("cart");
        verify(model, never()).addAttribute(anyString(), any());
        verify(session, never()).setAttribute(anyString(), any());

    }
//    @Test
//    void testGetQuantityInCart_ExceptionHandling() {
//        // Arrange
//        MockitoAnnotations.initMocks(this);
//        when(session.getAttribute("cart")).thenThrow(RuntimeException.class);
//
//        // Act
//        ResponseEntity<Integer> response = cartController.getQuantityInCart(session);
//
//        // Assert
//        assertEquals(ResponseEntity.badRequest().build(), response);
//
//    }

    @Test
    void testUpdateCartQuantity_WhenCartExists() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        Cart cart = new Cart();
        cart.addItem(new GymPlanDepartmentNameDto(),2);

        when(session.getAttribute("cart")).thenReturn(cart);

        CartUpdateRequestDto request = new CartUpdateRequestDto(1, 5);

        // Act
        ResponseEntity<String> response = cartController.updateCartQuantity(request, session);

        // Assert
        assertEquals(ResponseEntity.ok("Cart updated successfully"), response);
        assertEquals(2, cart.getItems().get(0).getQuantity());
        verify(session, times(1)).getAttribute("cart");
        verify(session, times(1)).setAttribute("cart", cart);
        verifyNoMoreInteractions(session);
    }

    @Test
    void testUpdateCartQuantity_WhenCartDoesNotExist() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        when(session.getAttribute("cart")).thenReturn(null);

        CartUpdateRequestDto request = new CartUpdateRequestDto(1, 5);

        // Act
        ResponseEntity<String> response = cartController.updateCartQuantity(request, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Cart not found"), response);
        verify(session, times(1)).getAttribute("cart");
        verifyNoMoreInteractions(session);
    }


}
