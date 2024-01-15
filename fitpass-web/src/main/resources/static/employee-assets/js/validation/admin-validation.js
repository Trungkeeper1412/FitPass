$(document).ready(function () {
    $("#formSubmit").validate({
        rules: {
            featureName: {
                required: true,
                minlength: 2,
                maxlength: 30,
            },
            featureIcon: {
                required: true,
            },
            requestId: {
              required: true,
            },
            brandEmail: {
                required: true,
                email: true,
            },
            credit: {
                required: true,
                number: true,
                min: 1,
                max: 100000,
            },
            money: {
                required: true,
                number: true,
                min: 1000,
                max: 1000000000,
            }
        },
        messages: {
            featureName: {
                required: "Vui lòng nhập tên tiện ích",
                minlength: "Tên tiện ích phải có ít nhất 2 kí tự",
                maxlength: "Tên tiện ích không được quá 30 kí tự",
            },
            featureIcon: {
                required: "Vui lòng nhập chọn tiện ích",
            },
            requestId: {
                required: "Vui lòng chọn thương hiệu",
            },
            brandEmail: {
                required: "Vui lòng nhập email thương hiệu",
                email: "Vui lòng nhập đúng định dạng email",
            },
            credit: {
                required: "Vui lòng nhập mốc Credit",
                number: "Vui lòng nhập số",
                min: "Số Credit phải lớn hơn hoặc bằng 1",
                max: "Số Credit không được vượt quá 100.000",
            },
            money: {
                required: "Vui lòng nhập tiền của mốc Credit",
                number: "Vui lòng nhập số",
                min: "Số tiền phải lớn hơn hoặc bằng 1.000",
                max: "Số tiền không được vượt quá 100.000.000",
            }
        },
    });

    $("#submit").click(function () {
        if (!$("#formSubmit").valid()) {
            return false;
        }
    });
});

$(document).ready(function () {
    $("#formUpdate").validate({
        rules: {
            featureName: {
                required: true,
                minlength: 2,
                maxlength: 30,
            },
            featureIcon: {
                required: true,
            }
        },
        messages: {
            featureName: {
                required: "Vui lòng nhập tên tiện ích",
                minlength: "Tên tiện ích phải có ít nhất 2 kí tự",
                maxlength: "Tên tiện ích không được quá 30 kí tự",
            },
            featureIcon: {
                required: "Vui lòng nhập chọn tiện ích",
            }
        },
    });

    $("#update").click(function () {
        if (!$("#formUpdate").valid()) {
            return false;
        }
    });
});

$(document).ready(function () {
    $("#formPercentage").validate({
        rules: {
            numberPercentage: {
                required: true,
                min: 0,
                max: 100,
            }
        },
        messages: {
            numberPercentage: {
                required: "Vui lòng nhập phần trăm phí",
                min: "Phần trăm phí không được nhỏ hơn 0",
                max: "Phần trăm phí không được vượt quá 100",
            }
        },
    });

    $("#submit").click(function () {
        if (!$("#formPercentage").valid()) {
            return false;
        }
    });
});