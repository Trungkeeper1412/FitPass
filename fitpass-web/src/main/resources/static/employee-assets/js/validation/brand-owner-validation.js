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

$.validator.addMethod("validateIdCard", function(value, element) {
    // Lấy mã tỉnh từ số căn cước
    var provinceCode = value.substring(0, 3);

    // Lấy số tiếp theo sau mã tỉnh
    var validCenturyGenderCodes = value.charAt(3);

    // - Kiểm tra mã tỉnh
    var validProvinceCodes = ["001", "002", "004", "006", "008", "010", "011", "012", "014",
        "015", "017", "019", "020", "022", "024", "025", "026", "027", "030", "031", "033", "034",
        "035", "036", "037", "038", "040", "042", "044", "045", "046", "048", "049", "051", "052",
        "054", "056", "058", "060", "062", "064", "066", "067", "068", "070", "072", "074", "075",
        "077", "079", "080", "082", "083", "084", "086", "087", "089", "091", "092", "093", "094",
        "095", "096"]; // Danh sách mã tỉnh hợp lệ
    if (validProvinceCodes.indexOf(provinceCode) === -1) {
        return false; // Mã tỉnh không hợp lệ
    }

    // Kiểm tra mã giới tính
    if (!/^[0-3]$/.test(validCenturyGenderCodes)) {
        /*
        "0": "nữ", "1": "nam", // Thế kỷ 20
        "2": "nữ", "3": "nam", // Thế kỷ 21
        "4": "nữ", "5": "nam", // Thế kỷ 22
        "6": "nữ", "7": "nam", // Thế kỷ 23
        "8": "nữ", "9": "nam" // Thế kỷ 24
         */
        return false;
    }

    return true; // Các mã tỉnh và số tiếp theo đều hợp lệ
}, "Số căn cước công dân không đúng định dạng !");

$(document).ready(function () {
    $("#formSubmit").validate({
        rules: {
            // Validate Brand Name
            brandName: {
                required: true,
                minlength: 3,
                maxlength: 32,
                pattern: /^[a-zA-Z0-9\u00C0-\u1EF9 ]*$/,
            },

            // Validate Plan
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
                maxlength: 250,
            },
            duration: {
                required: true,
                number: true,
                min: 1,
            },
            price: {
                required: true,
                number: true,
                min: 0.01,
            },
            statusActive: {
                required: true,
            },

            // Validate Owner
            'department-img': {
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
            username: {
                required: true,
                minlength: 6,
                maxlength: 50,
                pattern: /^[a-zA-Z0-9]+$/,
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
                pattern: /^(0|84)(9|3|7|8|5)\d{8}$/
            },
            idCard: {
                required: true,
                number: true,
                minlength: 12,
                maxlength: 12,
                pattern: /^[0-9]{12}$/,
                validateIdCard: true,
            },

            // Validate service
            amenitieName: {
                required: true,
                minlength: 2,
                maxlength: 50,
            }
        },
        messages: {
            // Validate Plan
            brandName: {
                required: "Vui lòng nhập tên cơ sở !",
                minlength: "Tên cơ sở phải có ít nhất 3 kí tự !",
                maxlength: "Tên cơ sở không được vượt quá 32 kí tự !",
                pattern: "Tên cơ sở không được chứa kí tự đặc biệt !",
            },
            gymPlanName: {
                required: "Vui lòng nhập tên gói tập !",
                minlength: "Tên gói tập phải có ít nhất 2 kí tự !",
                maxlength: "Tên gói tập không được vượt quá 32 kí tự !",
                pattern: "Tên gói tập không được chứa kí tự đặc biệt !",
            },
            pricePerHours: {
                required: "Vui lòng nhập số credits !",
                number: "Vui lòng nhập số hợp lệ !",
                min: "Số credits/giờ phải lớn hơn 0 !",
            },
            planBeforeActive: {
                required: "Vui lòng nhập nhập số ngày trước khi kích hoạt !",
                number: "Vui lòng nhập số hợp lệ !",
                min: "Số ngày phải lớn hơn 0 !"
            },
            planAfterActive: {
                required: "Vui lòng nhập nhập số ngày sau khi kích hoạt !",
                number: "Vui lòng nhập số hợp lệ !",
                min: "Số ngày phải lớn hơn 0 !"
            },
            description: {
                required: "Vui lòng nhập nhập mô tả gói tập !",
                minlength: "Mô tả gói tập phải có ít nhất 2 kí tự !",
                maxlength: "Mô tả gói tập không được vượt quá 250 kí tự !",
            },
            duration: {
                required: "Vui lòng nhập nhập số ngày sử dụng gói tập !",
                number: "Vui lòng nhập số hợp lệ !",
                min: "Số ngày ít nhất phải bằng 1 !"
            },
            price: {
                required: "Vui lòng nhập số credits !",
                number: "Vui lòng nhập số hợp lệ !",
                min: "Số credits phải lớn hơn 0 !",
            },
            statusActive: {
                required: "Vui lòng chọn trạng thái !"
            },

            // Validate Owner
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
            username: {
                required: "Vui lòng nhập tên đăng nhập !",
                minlength: "Tên đăng nhập phải có ít nhất 6 kí tự !",
                maxlength: "Tên đăng nhập không được vượt quá 50 kí tự !",
                pattern: "Tên đăng nhập không bao gồm khoảng trắng và ký tự đặc biệt !",
            },
            phone: {
                required: "Vui lòng nhập số điện thoại !",
                number: "Vui lòng nhập số điện thoại !",
                minlength: 'Số điện thoại phải có ít nhất 10 số !',
                maxlength: 'Số điện thoại có tối đa 11 số !',
                pattern: 'Số điện thoại không đúng định dạng !'
            },
            idCard: {
                required: "Vui lòng nhập số căn cước công dân !",
                number: "Đã bảo nhập số rồi còn nhập cái gì đấy ?",
                minlength: "Số căn cước công dân phải có ít nhất 12 chữ số !",
                maxlength: "Số căn cước công dân không được vượt quá 12 chữ số !",
                pattern: "Số căn cước công dân không hợp lệ !",
            },

            // Validate service
            amenitieName: {
                required: "Vui lòng nhập tên dịch vụ !",
                minlength: "Tên dịch vụ phải có ít nhất 2 kí tự !",
                maxlength: "Tên dịch vụ không được vượt quá 50 kí tự !",
            }
        },
        errorPlacement: function (error, element) {
            if (element.attr("type") === "radio") {
                error.insertAfter(element.closest('.display-status'));
            } else {
                error.insertAfter(element);
            }
        }
    });

    $("#createNewDepartment").click(function () {
        if (!$("#formSubmit").valid()) {
            return false;
        }
    });
});