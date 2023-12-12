package com.ks.fitpass.credit_card.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreditCard {

    private int creditCardId;

    private int userId;


    @Size(max = 100, message = "Tên của bạn không được vượt quá 100 kí tự !")
    @NotEmpty(message = "Vui lòng nhập họ của bạn !")
    @Pattern(regexp = "^[a-zA-Z\\u00C0-\\u1EF9\\s]*$", message = "Họ của bạn không được chứa kí tự đặc biệt !")
    private String cardOwnerName;

    @NotEmpty(message = "Vui lòng nhập số tài khoản !")
    @Size(min = 8, max = 15, message = "Số tài khoản phải là 8-15 số !")
    private String cardNumber;

    private String status;

    @NotEmpty(message = "Vui lòng chọn loại tài khoản !")
    private String bankName;

}
