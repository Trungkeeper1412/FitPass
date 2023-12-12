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

$.validator.addMethod("noSpace", function (value) {
    return /^\S*$/.test(value);
}, "Mật khẩu không được chứa khoảng trắng");

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
                number:true,
                minlength: 10,
                maxlength: 11,
                pattern: /^(0|84)(9|3|7|8|5)\d{8}$/
            },
            phoneNumber: {
                required: true,
                number:true,
                minlength: 10,
                maxlength: 11,
                pattern: /^(0|84)(9|3|7|8|5)\d{8}$/
            },
            userAccount: {
                required: true,
                minlength: 6,
                maxlength: 20,
                pattern: /^[a-zA-Z0-9 ]+$/,
                noSpace: true,
            },
            userPassword: {
                required: true,
                minlength: 6,
                maxlength: 50,
                noSpace: true,
            },
            reUserPassword: {
                required: true,
                equalTo: "#userPassword",
            }
        },
        messages: {
            // Validate employee
            'department-img': {
                required: "Vui lòng chọn ảnh !"
            },
            firstName: {
                required: "Vui lòng nhập họ của bạn !",
                maxlength: "Họ tên không vượt quá 25 kí tự !",
                pattern: "Họ tên không chứa số và kí tự đặc biệt !",
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
                maxlength: "Tên không vượt quá 25 kí tự !",
                pattern: "Tên không chứa số và kí tự đặc biệt !",
            },
            phone: {
                required: "Vui lòng nhập số điện thoại !",
                number: "Vui lòng nhập đúng định dạng số !",
                minlength: 'Số điện thoại phải có ít nhất 10 số !',
                maxlength: 'Số điện thoại có tối đa 11 số !',
                pattern: 'Số điện thoại không đúng định dạng !'
            },
            phoneNumber: {
                required: "Vui lòng nhập số điện thoại !",
                number: "Vui lòng nhập đúng định dạng số !",
                minlength: 'Số điện thoại phải có ít nhất 10 số !',
                maxlength: 'Số điện thoại có tối đa 11 số !',
                pattern: 'Số điện thoại không đúng định dạng !'
            },
            userAccount: {
                required: "Vui lòng nhập tên đăng nhập !",
                minlength: "Tên đăng nhập phải có ít nhất 6 kí tự !",
                maxlength: "Tên đăng nhập không được vượt quá 20 kí tự !",
                pattern: "Tên đăng nhập không bao gồm ký tự đặc biệt !",
                noSpace: "Tên đăng nhập không được chứa khoảng trắng !",
            },
            userPassword: {
                required: "Vui lòng nhập mật khẩu !",
                minlength: "Mật khẩu mới phải có ít nhất 6 ký tự",
                maxlength: "Mật khẩu mới không được quá 50 ký tự",
            },
            reUserPassword: {
                required: "Vui lòng xác nhận mật khẩu !",
                equalTo: "Xác nhận mật khẩu phải giống với mật khẩu",
            }
        },
        submitHandler: function (form) {
            // Lấy giá trị reCAPTCHA response
            const recaptchaResponse = grecaptcha.getResponse();

            // Kiểm tra xem reCAPTCHA response có giá trị hay không
            if (recaptchaResponse.length === 0) {
                alert('Vui lòng hoàn thành reCAPTCHA trước khi submit form.');
            } else {
                // Nếu reCAPTCHA đã được hoàn thành, tiếp tục submit form
                form.submit();
            }
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

$(document).ready(function () {

    $('#change-pw-form').validate({
        rules: {
            currentPassword: {
                required: true,
                minlength: 6,
                maxlength: 50,
                noSpace: true,
            },
            newPassword: {
                required: true,
                minlength: 6,
                maxlength: 50,
                noSpace: true,
            },
            confirmPassword: {
                required: true,
                equalTo: "#newPassword",
            }
        },
        messages: {
            currentPassword: {
                required: "Vui lòng nhập mật khẩu hiện tại!",
                minlength: "Mật khẩu hiện tại phải có ít nhất 6 ký tự",
                maxlength: "Mật khẩu hiện tại không được quá 50 ký tự",
            },
            newPassword: {
                required: "Vui lòng nhập mật khẩu mới!",
                minlength: "Mật khẩu mới phải có ít nhất 6 ký tự",
                maxlength: "Mật khẩu mới không được quá 50 ký tự",
            },
            confirmPassword: {
                required: "Vui lòng xác nhận mật khẩu mới!",
                equalTo: "Xác nhận mật khẩu phải giống với mật khẩu mới",
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        }
    });
    $("#submitPassword").click(function () {
        if (!$("#change-pw-form").valid()) {
            return false;
        }
    });
});