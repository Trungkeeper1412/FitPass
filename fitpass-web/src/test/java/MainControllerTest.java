import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

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
    void testCheckFirstTimeLoginWithUserSession() {
        // Arrange
        HttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("userInfo", user);

        // Act
        String result = mainController.checkFirstTimeLogin(session);

        // Assert
        assertEquals("redirect:/homepage", result);
    }

    @Test
    void testRegisteredWithValidData() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("test@example.com");
        userUpdateDTO.setUserPassword("password");
        userUpdateDTO.setReUserPassword("password");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        Model model = mock(Model.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        UserDetail userDetail = new UserDetail();
        userDetail.setEmail(userUpdateDTO.getEmail());

        User newUser = new User();
        newUser.setUserAccount(userUpdateDTO.getUserAccount());

        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);
        when(userService.getLastInsertUserDetailIdRegister(userDetail)).thenReturn(1);
        when(userService.getLastUserInsertId(newUser)).thenReturn(1);

        // Act
        String result = mainController.registered(userUpdateDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
      }

    @Test
    void testRegisteredWithExistingEmail() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("existing@example.com");
        userUpdateDTO.setUserPassword("password");  // Add this line
        userUpdateDTO.setReUserPassword("password");  // Add this line

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        Model model = mock(Model.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(true);

        // Act
        String result = mainController.registered(userUpdateDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/register", result);
    }

    @Test
    void testRegisteredWithPasswordMismatch() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setUserPassword("password");
        userUpdateDTO.setReUserPassword("mismatched");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        Model model = mock(Model.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Act
        String result = mainController.registered(userUpdateDTO, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("register", result);
        verify(bindingResult, times(1)).rejectValue("reUserPassword", "error.reUserPassword", "Mật khẩu và xác nhận mật khẩu không khớp !");
        verify(model, times(1)).addAttribute("userUpdateDTO", userUpdateDTO);
    }

    @Test
    void testRegister() {
        // Arrange

        // Act
        String result = mainController.register(model);

        // Assert
        assertEquals("register", result);

        // Verify that the model.addAttribute method was called with the expected argument
        verify(model, times(1)).addAttribute(eq("userUpdateDTO"), any(UserUpdateDTO.class));
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
