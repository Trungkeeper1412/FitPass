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
                number: true,
                min: 0.01,
            },
            planBeforeActive: {
                required: true,
                number: true,
                min: 0.01,
            },
            planAfterActive: {
                required: true,
                number: true,
                min: 0.01,
            },
            description: {
                required: true,
                minlength: 2,
                maxlength: 200,
                pattern: /^[a-zA-Z0-9\u00C0-\u1EF9 ]*$/,
            },
            packageDuration: {
                required: true,
                number: true,
                min: 1,
            },
            pricePlan: {
                required: true,
                number: true,
                min: 0.01,
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
                required: "Vui lòng nhập số credits",
                number: "Vui lòng nhập số hợp lệ",
                min: "Số credits/giờ phải lớn hơn 0",
            },
            planBeforeActive: {
                required: "Vui lòng nhập nhập số ngày trước khi kích hoạt",
                number: "Vui lòng nhập số hợp lệ",
                min: "Số ngày phải lớn hơn 0"
            },
            planAfterActive: {
                required: "Vui lòng nhập nhập số ngày sau khi kích hoạt",
                number: "Vui lòng nhập số hợp lệ",
                min: "Số ngày phải lớn hơn 0"
            },
            description: {
                required: "Vui lòng nhập nhập mô tả gói tập",
                minlength: "Mô tả gói tập phải có ít nhất 2 kí tự",
                maxlength: "Mô tả gói tập không được vượt quá 200 kí tự",
                pattern: "Mô tả gói tập không được chứa kí tự đặc biệt",
            },
            packageDuration: {
                required: "Vui lòng nhập nhập số ngày sử dụng gói tập",
                number: "Vui lòng nhập số hợp lệ",
                min: "Số ngày ít nhất phải bằng 1"
            },
            pricePlan: {
                required: "Vui lòng nhập số credits",
                number: "Vui lòng nhập số hợp lệ",
                min: "Số credits phải lớn hơn 0",
            },
        },
    });

    $("#createNewDepartment").click(function () {
        if (!$("#formSubmit").valid()) {
            return false;
        }
    });
});