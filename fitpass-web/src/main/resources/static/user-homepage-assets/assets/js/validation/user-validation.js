$(document).ready(function () {
    $("#moneyForm").validate({
        rules: {
            moneyAmount: {
                required: true,
                number: true,
                min: 20000,
                max: 10000000,
                step: 1000
            }
        },
        messages: {
            moneyAmount: {
                required: "Vui lòng nhập số tiền",
                number: "Vui lòng nhập số",
                min: "Số tiền nạp ít nhất bằng 20,000",
                max: "Số tiền nạp không được vượt quá 10,000,000",
                step: "Số tiền nạp phải là bội số của 1,000"
            }
        },
        submitHandler: function (form) {
            if ($("#moneyForm").valid()) {
                alert("Nạp tiền thành công!");
                toggleModal();
            }
        }
    });

    $("#add-money-btn").click(function () {
        if (!$("#moneyForm").valid()) {
            return false;
        }
    });
});