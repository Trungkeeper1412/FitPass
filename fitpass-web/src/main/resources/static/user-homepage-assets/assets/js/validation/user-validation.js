$.validator.addMethod("minAge", function (value, element) {
    var dob = new Date(value);
    var today = new Date();
    var age = today.getFullYear() - dob.getFullYear();

    var monthDiff = today.getMonth() - dob.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < dob.getDate())) {
        age--;
    }
    return age >= 16;
}, "Bạn phải đủ 16 tuổi trở lên.");

$.validator.addMethod("notFutureDate", function (value, element) {
    var selectedDate = new Date(value);
    var today = new Date();
    return selectedDate <= today;
}, "Wow, bạn đang chọn ngày ở tương lai?");

$(document).ready(function () {
    $("#formSubmit").validate({
        rules: {
            // Validate employee
            'avatar-img': {
                required: true
            },
            firstName: {
                required: true,
                maxlength: 25,
                pattern: /^[a-zA-Z\u00C0-\u1EF9 ]*$/,
            },
            email: {
                required: true,
                email: true
            },
            address: {
                required: true,
                maxlength: 150,
                pattern: /^(?!\s+$).+/,
            },
            dateOfBirth: {
                required: true,
                date: true,
                notFutureDate: true,
                minAge: true,
            },
            gender: {
                required: true
            },
            lastName: {
                required: true,
                maxlength: 25,
                pattern: /^[a-zA-Z\u00C0-\u1EF9 ]*$/,
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
                pattern: /^(0|84)(9|3|7|8|5)\d{8}$/
            },
        },
        messages: {
            // Validate employee
            'department-img': {
                required: "Vui lòng chọn ảnh !"
            },
            firstName: {
                required: "Vui lòng nhập họ của bạn !",
                maxlength: "Họ của bạn không được vượt quá 25 kí tự !",
                pattern: "Họ của bạn không được chứa kí tự đặc biệt !",
            },
            email: {
                required: "Vui lòng nhập email !",
                email: "Vui lòng nhập địa chỉ email hợp lệ !"
            },
            address: {
                required: "Vui lòng nhập địa chỉ !",
                maxlength: "Địa chỉ không được vượt quá 150 kí tự !",
                pattern: "Địa chỉ đang bị trống !",
            },
            dateOfBirth: {
                required: "Vui lòng nhập ngày sinh !",
                date: "Vui lòng nhập ngày sinh hợp lệ !",
            },
            gender: {
                required: "Vui lòng chọn giới tính !"
            },
            lastName: {
                required: "Vui lòng nhập tên của bạn !",
                maxlength: "Tên của bạn không được vượt quá 25 kí tự !",
                pattern: "Tên của bạn không được chứa kí tự đặc biệt !",
            },
            phone: {
                required: "Vui lòng nhập số điện thoại !",
                number: "Vui lòng nhập số điện thoại !",
                minlength: 'Số điện thoại phải có ít nhất 10 số !',
                maxlength: 'Số điện thoại có tối đa 11 số !',
                pattern: 'Số điện thoại không đúng định dạng !'
            },
        },
    });

    $("#updateInfo").click(function () {
        if (!$("#formSubmit").valid()) {
            return false;
        }
    });
});

$(document).ready(function () {

    $.validator.addMethod("starRequired", function (value, element) {
        var rating = $("input[name='rating']:checked").val();
        return (rating !== undefined);
    }, "Vui lòng chọn đánh giá sao.");

    $("#formReview").validate({
        rules: {
            rating: {
                starRequired: true
            },
            thoughts: {
                maxlength: 200,
            },
        },
        messages: {
            rating: {
                starRequired: "Vui lòng chọn đánh giá sao."
            },
            thoughts: {
                maxlength: "Đánh giá không được vượt quá 200 kí tự !",
            },
        },
        errorPlacement: function (error, element) {
            if (element.attr("name") == "rating") {
                error.insertAfter(".star-rating");
            } else {
                error.insertAfter(element);
            }
        },
    });

    $("#submit-review").click(function () {
        if (!$("#formReview").valid()) {
            return false;
        }
    });
});

$(document).ready(function () {

    $("#formReviewed").validate({
        rules: {
            thoughts: {
                maxlength: 200,
            },
        },
        messages: {
            thoughts: {
                maxlength: "Đánh giá không được vượt quá 200 kí tự !",
            },
        },
    });

    $("#edit-review").click(function () {
        if (!$("#formReviewed").valid()) {
            return false;
        }
    });
});