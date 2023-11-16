package com.ks.fitpass.web.controller;

import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.transaction.dto.TransactionDTO;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;


@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final WalletService walletService;
    private final TransactionService transactionService;

    @PostMapping("/create-session")
    public ResponseEntity<String> createCheckoutSession(@RequestParam("amount") String selectedAmount) {
        Stripe.apiKey = "sk_test_51O8PFkCEyctaDSq7R5Pd9BhZcu45QPgcNGhA0gpgbJYFnBwNCGEXcNc7XyCF2DbwkVOaPs8YLknSQbVzcZJWNWgF00rHGcHPGL";
        Long amount = Long.parseLong(selectedAmount);
        String YOUR_DOMAIN = "http://localhost:8080";
        try {
            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("vnd")
                    .setUnitAmount(amount) // Số tiền cần thanh toán (đơn vị là cents)
                    .setProductData(
                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("FITPASS")
                                    .setDescription("Nạp credit vào ví fitpass")
                                    .build()
                    )
                    .build();
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(YOUR_DOMAIN + "/payment/success?amount=" + amount)
                            .setCancelUrl(YOUR_DOMAIN + "/payment/cancel?amount=" + amount)
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(
                                                    priceData
                                            )
                                            .build())
                            .build();
            Session session = Session.create(params);

            String test = session.getUrl();

            return ResponseEntity.ok(test);
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/success")
    public String processSuccessPayment(@RequestParam("amount") long amount, HttpSession session, Model model) {
        User user = (User) session.getAttribute("userInfo");
        double balance = walletService.getBalanceByUserId(user.getUserId());
        double creditAfterPayment = balance + amount / 1000;
        walletService.updateBalanceByUderId(user.getUserId(), creditAfterPayment);

        // Insert vao bang transaction
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount((int) amount);
        transactionDTO.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transactionDTO.setStatus("Thành công");
        transactionDTO.setWalletId(walletService.getWalletIdByUserId(user.getUserId()));
        transactionService.insertTransaction(transactionDTO);

        session.setAttribute("userCredit", creditAfterPayment);
        model.addAttribute("redirectCountdown", 5); // Set the redirect countdown value (e.g., 5 seconds)
        return "user/paymentSuccess";
    }

    @GetMapping("/cancel")
    public String processCancelPayment(@RequestParam("amount") long amount, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        // Insert vao bang transaction
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount((int) amount);
        transactionDTO.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transactionDTO.setStatus("Đã hủy");
        transactionDTO.setWalletId(walletService.getWalletIdByUserId(user.getUserId()));
        transactionService.insertTransaction(transactionDTO);
        return "user/paymentCancel";
    }

}
