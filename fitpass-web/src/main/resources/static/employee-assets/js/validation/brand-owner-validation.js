$(document).ready(function () {
    $("#formSubmit").validate({
        rules: {
            brandName: {
                required: true,
                minlength: 3,
                maxlength: 32,
                pattern: /^[a-zA-Z0-9\u00C0-\u1EF9 ]*$/,
            },
            gymPlanName: {
                required: true,
                minlength: 2,
                maxlength: 26,
                pattern: /^[a-zA-Z0-9\u00C0-\u1EF9 ]*$/,
            },
            pricePerHours: {
                required: true,
                number: true
            },
            planBeforeActive: {
                required: true,
                number: true
            },
            planAfterActive: {
                required: true,
                number: true
            },
            description: {
                required: true
            },
        },
        messages: {
            brandName: {
                required: "Vui lòng nhập tên cơ sở",
                minlength: "Tên cơ sở phải có ít nhất 3 kí tự",
                maxlength: "Tên cơ sở không được vượt quá 32 kí tự",
                pattern: "Tên cơ sở không được chứa kí tự đặc biệt",
            },
            gymPlanName: {
                required: "Vui lòng nhập tên gói tập",
                minlength: "Tên gói tập phải có ít nhất 2 kí tự",
                maxlength: "Tên gói tập không được vượt quá 32 kí tự",
                pattern: "Tên gói tập không được chứa kí tự đặc biệt",
            },
            pricePerHours: {
                required: "Số credits/giờ là trường bắt buộc",
                number: "Vui lòng nhập số hợp lệ"
            },
            planBeforeActive: {
                required: "Thời hạn trước khi kích hoạt là trường bắt buộc",
                number: "Vui lòng nhập số hợp lệ"
            },
            planAfterActive: {
                required: "Thời hạn sau khi kích hoạt là trường bắt buộc",
                number: "Vui lòng nhập số hợp lệ"
            },
            description: {
                required: "Mô tả gói tập là trường bắt buộc"
            },
        },
    });

    $("#createNewDepartment").click(function () {
        if (!$("#formSubmit").valid()) {
            return false;
        }
    });
});