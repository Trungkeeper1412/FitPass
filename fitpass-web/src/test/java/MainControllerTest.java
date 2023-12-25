import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserRegisterDTO;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.MainController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private HttpSession session;

    @Mock
    private Authentication authentication;
    @Mock
    private WalletService walletService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCheckFirstTimeLoginWithoutUserSession() {
        // Arrange
        HttpSession session = new MockHttpSession();

        // Act
        String result = mainController.checkFirstTimeLogin(session);

        // Assert
        assertEquals("landing", result);
    }

    @Test
    public void testRegisteredSuccess() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        userRegisterDTO.setUserPassword("password");  // Set a non-null password

        // Act
        String result = mainController.registered(userRegisterDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/login", result);
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("successRegister"), eq(true));
    }

    @Test
    public void testRegisteredEmailExists() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        userRegisterDTO.setUserPassword("password");  // Set a non-null password

        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("email", "Email đã tồn tại !")));

        // Act
        String result = mainController.registered(userRegisterDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("register", result);
   }

    @Test
    public void testRegisteredUsernameExists() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUserPassword("password");  // Set a non-null password

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("userAccount", "Tên đăng nhập đã tồn tại !")));

        // Act
        String result = mainController.registered(userRegisterDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("register", result);
   }

    @Test
    public void testRegisteredPasswordMismatch() {
        // Arrange

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUserPassword("password");  // Set a non-null password

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("reUserPassword", "Mật khẩu và xác nhận mật khẩu không khớp !")));

        // Act
        String result = mainController.registered(userRegisterDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("register", result);
    }


    @Test
    void testLoginWithUserInSession() {
        // Arrange
        User mockUser = new User(); // create a mock user object
        when(session.getAttribute("userInfo")).thenReturn(mockUser); // mock session to return the mock user

        // Act
        String result = mainController.login(session);

        // Assert
        assertEquals("redirect:/homepage", result);
        // Verify that the model.addAttribute method was not called
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void testLoginWithoutUserInSession() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(null); // simulate no user in session

        // Act
        String result = mainController.login(session);

        // Assert
        assertEquals("login-register", result);
        // Verify that the model.addAttribute method was not called
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void testGetLandingPage() {
        // Arrange & Act
        String result = mainController.getLandingPage();

        // Assert
        assertEquals("landing", result);
    }


    @Test
    void testShowInfo() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Authentication authentication = mock(Authentication.class);
        User mockUser = new User();
        when(session.getAttribute("userInfo")).thenReturn(mockUser);

        // Act
        String result = mainController.admin(model, session, authentication);

        // Assert
        assertEquals("show-info", result);
    }


}
