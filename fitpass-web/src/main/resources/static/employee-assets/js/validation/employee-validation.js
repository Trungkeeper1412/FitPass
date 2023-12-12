$(document).ready(function () {

    $.validator.addMethod("noSpace", function (value) {
        return /^\S*$/.test(value);
    }, "Mật khẩu không được chứa khoảng trắng");

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
            if (element.hasClass("form-control")) {
                error.insertAfter(element.closest('.input-group'));
            } else {
                error.insertAfter(element);
            }
        }
    });

    $("#submitPassword").click(function () {
        if (!$("#change-pw-form").valid()) {
            return false;
        }
    });
});