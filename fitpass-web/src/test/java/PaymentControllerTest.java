
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.PaymentController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {


    @Test
    void testProcessSuccessPayment() {
        // Arrange
        WalletService walletService = mock(WalletService.class);
        TransactionService transactionService = mock(TransactionService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        PaymentController paymentController = new PaymentController(walletService, transactionService);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        Long amount = 5000L; // Set amount as needed

        when(session.getAttribute("amount")).thenReturn(amount);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock walletService and transactionService behavior
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(100.0);
        when(transactionService.getTotalAmountOfTransactionByUserId(user.getUserId())).thenReturn(50.0);

        // Act
        String result = paymentController.processSuccessPayment(session, model);

        // Assert
        assertEquals("user/paymentSuccess", result);

        // Verify interactions
        verify(session, times(1)).removeAttribute("amount");
        verify(session, times(1)).setAttribute("userCredit", 105.0); // Adjust the expected credit value
        verify(session, times(1)).setAttribute("userTotalDeposit", 55.0); // Adjust the expected total deposit value
        verify(model, times(1)).addAttribute("redirectCountdown", 5);
    }

    @Test
    void testProcessSuccessPaymentWithEmptyResultDataAccessException() {
        // Arrange
        WalletService walletService = mock(WalletService.class);
        TransactionService transactionService = mock(TransactionService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        PaymentController paymentController = new PaymentController(walletService, transactionService);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        Long amount = 5000L; // Set amount as needed

        when(session.getAttribute("amount")).thenReturn(amount);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock walletService and transactionService behavior
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(100.0);
        when(transactionService.getTotalAmountOfTransactionByUserId(user.getUserId())).thenReturn(50.0);

        // Mock EmptyResultDataAccessException
        doThrow(new EmptyResultDataAccessException(1)).when(walletService).updateBalanceByUderId(anyInt(), anyDouble());

        // Act
        String result = paymentController.processSuccessPayment(session, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    void testProcessSuccessPaymentWithDataAccessException() {
        // Arrange
        WalletService walletService = mock(WalletService.class);
        TransactionService transactionService = mock(TransactionService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        PaymentController paymentController = new PaymentController(walletService, transactionService);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        Long amount = 5000L; // Set amount as needed

        when(session.getAttribute("amount")).thenReturn(amount);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock walletService and transactionService behavior
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(100.0);
        when(transactionService.getTotalAmountOfTransactionByUserId(user.getUserId())).thenReturn(50.0);

        // Mock DataAccessException
        doThrow(new DataAccessException("Data access error") {}).when(walletService).updateBalanceByUderId(anyInt(), anyDouble());

        // Act
        String result = paymentController.processSuccessPayment(session, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    void testProcessCancelPayment() {
        // Arrange
        WalletService walletService = mock(WalletService.class);
        TransactionService transactionService = mock(TransactionService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        PaymentController paymentController = new PaymentController(walletService, transactionService);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        Long amount = 5000L; // Set amount as needed

        when(session.getAttribute("amount")).thenReturn(amount);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock walletService method
        when(walletService.getWalletIdByUserId(user.getUserId())).thenReturn(123); // Set the expected wallet ID

        // Act
        String result = paymentController.processCancelPayment(session, model);

        // Assert
        assertEquals("user/paymentCancel", result);

       }


    @Test
    void testProcessCancelPaymentWithDataAccessException() {
        // Arrange
        WalletService walletService = mock(WalletService.class);
        TransactionService transactionService = mock(TransactionService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        PaymentController paymentController = new PaymentController(walletService, transactionService);

        User user = new User();
        user.setUserId(1); // Set user ID as needed

        Long amount = 5000L; // Set amount as needed

        when(session.getAttribute("amount")).thenReturn(amount);
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock DataAccessException
        doThrow(new DataAccessException("Data access error") {}).when(transactionService).insertTransaction(any(TransactionDTO.class));

        // Mock walletService method
        when(walletService.getWalletIdByUserId(user.getUserId())).thenReturn(123); // Set the expected wallet ID

        // Act
        String result = paymentController.processCancelPayment(session, model);

        // Assert
        assertEquals("error/data-access-error", result);
       }
}
