import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.entity.UserUpdateDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.web.controller.ProfileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfileControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private RedirectAttributes attributes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowProfileSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setUserId(1); // Set user ID as needed
        UserDetail userDetail = new UserDetail();
        // Set other properties of userDetail as needed

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.getUserDetailByUserId(user.getUserId())).thenReturn(userDetail);

        // Act
        String result = profileController.showProfile(session, model);

        // Assert
        assertEquals("user/user-profile", result);

        }

    @Test
    void testShowProfileEmptyResult() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setUserId(1); // Set user ID as needed

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.getUserDetailByUserId(user.getUserId())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = profileController.showProfile(session, model);

        // Assert
        assertEquals("error/no-data", result);

          }

    @Test
    void testUpdateGymOwnerDetailsSuccess() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("new.email@example.com");
        userUpdateDTO.setOldEmail("old.email@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the service methods
        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult, attributes);

        // Assert
        assertEquals("redirect:/profile/my-profile", result);

        // Verify interactions
        verify(userService, times(1)).updateUserDetail(any());
    }

    @Test
    void testUpdateGymOwnerDetailsWithEmailConflict() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setUserDetailId(1); // Set user detail ID as needed
        userUpdateDTO.setEmail("new@example.com");
        userUpdateDTO.setOldEmail("old@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(true);

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult, attributes);

        // Assert
        assertEquals("redirect:/profile/my-profile", result);
    }

    @Test
    void testUpdateGymOwnerDetailsWithEmailNoConflict() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setUserDetailId(1); // Set user detail ID as needed
        userUpdateDTO.setEmail("new@example.com");
        userUpdateDTO.setOldEmail("old@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult,attributes);

        // Assert
        assertEquals("redirect:/profile/my-profile", result);

        // Verify interactions
        verify(bindingResult, never()).rejectValue(any(), any(), any());
        verify(userService, times(1)).updateUserDetail(any(UserDetail.class));
    }


    @Test
    void testUpdateGymOwnerDetailsBindingResultHasErrors() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("new.email@example.com");
        userUpdateDTO.setOldEmail("old.email@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the service methods
        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult,attributes);

        // Assert
        assertEquals("user/user-profile", result);

        // Verify interactions
        verify(userService, never()).updateUserDetail(any());
    }

    @Test
    void testUpdateGymOwnerDetailsEmptyResultDataAccessException() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("new.email@example.com");
        userUpdateDTO.setOldEmail("old.email@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the service methods
        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);
        doThrow(EmptyResultDataAccessException.class).when(userService).updateUserDetail(any());

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult,attributes);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testUpdateGymOwnerDetailsDataAccessException() {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("new.email@example.com");
        userUpdateDTO.setOldEmail("old.email@example.com");
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock the behavior of the service methods
        when(userService.checkEmailExist(userUpdateDTO.getEmail())).thenReturn(false);
        doThrow(new CustomDataAccessException("Custom Data Access Exception")).when(userService).updateUserDetail(any());

        // Act
        String result = profileController.updateProfileDetails(userUpdateDTO, bindingResult,attributes);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testShowNotificationPage() {
        // Arrange
        // Act
        String result = profileController.showNotificationPage();

        // Assert
        assertEquals("user/user-notification", result);
    }

//    @Test
//    void testShowDepositPage() {
//        // Arrange
//        // Act
//        String result = profileController.showDepositPage();
//
//        // Assert
//        assertEquals("user/user-deposit", result);
//    }

    @Test
    void testShowTransactionHistoryPageSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        List<TransactionDTO> transactionDTOList = new ArrayList<>(); // Set a sample list

        // Mock the behavior of the session and service methods
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(transactionService.getListTransactionByUserId(user.getUserId())).thenReturn(transactionDTOList);

        // Act
        String result = profileController.showTransactionHistoryPage(model, session);

        // Assert
        assertEquals("user/user-transaction-history", result);

        // Verify interactions
        verify(model, times(1)).addAttribute("transactionList", transactionDTOList);
    }

    @Test
    void testShowTransactionHistoryPageEmptyResultDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        // Mock the behavior of the session and service methods
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(transactionService.getListTransactionByUserId(user.getUserId())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = profileController.showTransactionHistoryPage(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testShowTransactionHistoryPageIncorrectResultSizeDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        // Mock the behavior of the session and service methods
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(transactionService.getListTransactionByUserId(user.getUserId())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = profileController.showTransactionHistoryPage(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testShowTransactionHistoryPageDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        // Mock the behavior of the session and service methods
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(transactionService.getListTransactionByUserId(user.getUserId())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String result = profileController.showTransactionHistoryPage(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testChangePasswordSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed
        user.setUserPassword("$2a$10$u15c2wGiOBICUZAcUJiRdeyK8x8IJm5n6PV68D7pAyJl3c0GfYvze"); // Set hashed password as needed

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.updatePassword(anyString(), anyInt())).thenReturn(true); // Assume successful update

        // Act
        String result = profileController.changePassword("currentPassword", "newPassword", "newPassword", model, session);

        // Assert
        assertEquals("user/user-change-password", result); // Updated expected result

    }

    @Test
    void testChangePasswordIncorrectCurrentPassword() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed
        user.setUserPassword("$2a$10$u15c2wGiOBICUZAcUJiRdeyK8x8IJm5n6PV68D7pAyJl3c0GfYvze"); // Set hashed password as needed

        when(session.getAttribute("userInfo")).thenReturn(user);

        // Act
        String result = profileController.changePassword("incorrectPassword", "newPassword", "newPassword", model, session);

        // Assert
        assertEquals("user/user-change-password", result);
        verify(model, times(1)).addAttribute("currentPasswordError", "Mật khẩu hiện tại không đúng, vui lòng thử lại");
    }

    @Test
    void testChangePasswordMismatchedNewPassword() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        User user = new User();
        user.setUserId(1); // Set user ID as needed
        user.setUserPassword("$2a$10$u15c2wGiOBICUZAcUJiRdeyK8x8IJm5n6PV68D7pAyJl3c0GfYvze"); // Set hashed password as needed

        when(session.getAttribute("userInfo")).thenReturn(user);

        // Act
        String result = profileController.changePassword("currentPassword", "newPassword", "confirmPassword", model, session);

        // Assert
        assertEquals("user/user-change-password", result);
    }


}
