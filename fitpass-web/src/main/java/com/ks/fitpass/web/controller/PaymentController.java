package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.entity.CreateSessionRequest;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.constant.Constants;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;


@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final WalletService walletService;
    private final TransactionService transactionService;

    @Value("${stripe.api.key}")
    private String apiKey;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @PostMapping("/create-session")
    public ResponseEntity<String> createCheckoutSession(@RequestBody CreateSessionRequest request, HttpSession session) {
        Stripe.apiKey = apiKey;
        Long amount = request.getAmount();

        try {
            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("vnd")
                    .setUnitAmount(amount) // Số tiền cần thanh toán (đơn vị là cents)
                    .setProductData(
                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("FITPASS")
                                    .setDescription("Nạp credit vào ví FitPass")
                                    .build()
                    )
                    .build();
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(Constants.DOMAIN_NAME + "/payment/success")
                            .setCancelUrl(Constants.DOMAIN_NAME + "/payment/cancel")
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(priceData)
                                            .build())
                            .build();
            Session stripeSession = Session.create(params);

            String test = stripeSession.getUrl();

            session.setAttribute("amount", amount);

            return ResponseEntity.ok(test);
        } catch (StripeException e) {
            return null;
        }
    }

    @GetMapping("/success")
    public String processSuccessPayment(HttpSession session, Model model) {
        try {
            Long amount = (Long) session.getAttribute("amount");
            User user = (User) session.getAttribute("userInfo");
            double balance = walletService.getBalanceByUserId(user.getUserId());
            double userTotalDeposit = transactionService.getTotalAmountOfTransactionByUserId(user.getUserId());

            double depositAmount = (double) amount / 1000;

            double creditAfterPayment = balance + depositAmount;
            double totalDepositAfterPayment = userTotalDeposit + depositAmount;
            walletService.updateBalanceByUderId(user.getUserId(), creditAfterPayment);

            // Insert vao bang transaction
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setAmount(Math.toIntExact(amount));
            transactionDTO.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            transactionDTO.setStatus("Thành công");
            transactionDTO.setWalletId(walletService.getWalletIdByUserId(user.getUserId()));
            transactionService.insertTransaction(transactionDTO);

            session.removeAttribute("amount");
            session.setAttribute("userCredit", creditAfterPayment);
            session.setAttribute("userTotalDeposit", totalDepositAfterPayment);
            model.addAttribute("redirectCountdown", 5); // Set the redirect countdown value (e.g., 5 seconds)
            return "user/paymentSuccess";
        }catch (EmptyResultDataAccessException ex) {
            // Handle empty result set
            logger.error("EmptyResultDataAccessException occurred", ex);
            return "error/no-data";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }

    @GetMapping("/cancel")
    public String processCancelPayment(HttpSession session, Model model) {
        try {
            Long amount = (Long) session.getAttribute("amount");
            User user = (User) session.getAttribute("userInfo");
            // Insert vao bang transaction
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setAmount(Math.toIntExact(amount));
            transactionDTO.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            transactionDTO.setStatus("Đã hủy");
            transactionDTO.setWalletId(walletService.getWalletIdByUserId(user.getUserId()));
            transactionService.insertTransaction(transactionDTO);

            session.removeAttribute("amount");
            model.addAttribute("redirectCountdown", 5);
            return "user/paymentCancel";
        } catch (DataAccessException ex) {
            // Handle other data access issues
            logger.error("DataAccessException occurred", ex);
            return "error/data-access-error";
        }
    }



}
