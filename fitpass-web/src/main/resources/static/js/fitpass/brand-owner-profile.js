$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

// --------------------------------------------------------------------------------------------------------------------------------
document.addEventListener("DOMContentLoaded", function () {
    var type = "";
    var idImageAlbumEdit = "";
    const MAX_IMAGES = 9;
    const rowElement = document.querySelector(".row-add");
    const imageInput = document.getElementById("imageInput");
    const addButton = document.getElementById("addImageButton");
    const editImageInput = createEditImageInput();
    let editingImageContainer = null;
    const currentUrl = window.location.pathname;

    // Gán sự kiện 'change' cho imageInput khi tài liệu được tải
    imageInput.addEventListener("change", handleImageChange);

    // Sự kiện thêm hình ảnh
    addButton.addEventListener("click", addImage);

    // Lựa chọn tất cả các nút "Chỉnh sửa" có lớp 'img-edit-button'
    const editButtons = document.querySelectorAll(".img-edit-button");

    // Lựa chọn tất cả các nút "Xóa" có lớp 'img-delete-button'
    const deleteButtons = document.querySelectorAll(".img-delete-button");

    // Gán sự kiện click cho tất cả các nút "Chỉnh sửa"
    editButtons.forEach(function (editButton) {
        editButton.addEventListener("click", handleEditButton);
    });

    // Gán sự kiện click cho tất cả các nút "Xóa"
    deleteButtons.forEach(function (deleteButton) {
        deleteButton.addEventListener("click", handleDeleteImage);
    });

    // Hàm tạo input chỉnh sửa hình ảnh
    function createEditImageInput() {
        const editInput = document.createElement("input");
        editInput.type = "file";
        editInput.accept = "image/*";
        editInput.style.display = "none";
        document.body.appendChild(editInput);

        // Gán sự kiện 'change' cho editInput
        editInput.addEventListener("change", handleEditImage);

        return editInput;
    }

    // Hàm xử lý sự kiện thay đổi hình ảnh
    function handleImageChange(event) {
        const file = event.target.files[0];

        var formData = new FormData();
        formData.append("file", file);

        if (!currentUrl.includes("gym-owner/department/update-details")) {
            if (currentUrl.includes("gym-owner/department/image")) {
                let num = rowElement.querySelectorAll(".col-md-4").length + 1;
                $.ajax({
                    type: "POST",
                    url: `/upload/department/${type}/${$("#brandId").val()}`,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        console.log(response)
                        if (type == "logo") {
                            $("#imageLogo").val(response);
                        } else if (type == "thumbnail") {
                            $("#imageThumbnail").val(response)
                        } else if (type == "wallpaper") {
                            $("#imageWallpaper").val(response)
                        } else if (type == "album") {
                            $(`#album-${num}`).val(response);
                        }
                    },
                    error: function () {
                        $("#message").text("Failed to upload file");
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: `/upload/${type}/${$("#brandId").val()}`,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        console.log(response)
                        if (type == "logo") {
                            $("#imageLogo").val(response);
                        } else if (type == "thumbnail") {
                            $("#imageThumbnail").val(response)
                        } else if (type == "wallpaper") {
                            $("#imageWallpaper").val(response)
                        }
                    },
                    error: function () {
                        $("#message").text("Failed to upload file");
                    }
                });
            }
        }

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const newImageContainer = createImageContainer(e.target.result);
                rowElement.insertBefore(newImageContainer, rowElement.firstChild);
            };
            reader.readAsDataURL(file);
            // Đặt lại giá trị của input để cho phép chọn cùng 1 hình nhiều lần
            imageInput.value = null;
        }
    }

    // Hàm tạo phần tử hình ảnh
    function createImageContainer(imageSrc) {
        const newImageContainer = document.createElement("div");
        newImageContainer.classList.add("col-md-4");
        let num = rowElement.querySelectorAll(".col-md-4").length + 1;
        if(type === "album") {
            newImageContainer.innerHTML = `
              <div class="image-container">
                <img src="${imageSrc}" alt="Image">
                <div class="image-actions">
                  <button class="img-edit-button" type="button"
                    data-toggle="tooltip" data-placement="top" data-image-type="${type}"
                    data-album-id="${num}"
                    title="Chỉnh sửa hình ảnh"><i
                    class="fas fa-edit"></i></button>
                  <button class="img-delete-button" type="button"
                    data-toggle="tooltip" data-placement="top"
                    title="Xóa hình ảnh"><i
                    class="fas fa-trash"></i></button>
                </div>
                <input type="hidden" class="hidden-img-album-src" id="album-${rowElement.querySelectorAll(".col-md-4").length + 1}">
              </div>
            `;
        } else {
            newImageContainer.innerHTML = `
      <div class="image-container">
        <img src="${imageSrc}" alt="Image">
        <div class="image-actions">
          <button class="img-edit-button" type="button"
            data-toggle="tooltip" data-placement="top" data-image-type="${type}"
            title="Chỉnh sửa hình ảnh"><i
            class="fas fa-edit"></i></button>
          <button class="img-delete-button" type="button"
            data-toggle="tooltip" data-placement="top"
            title="Xóa hình ảnh"><i
            class="fas fa-trash"></i></button>
        </div>
      </div>
    `;
        }

        // Kích hoạt tooltips
        $(newImageContainer).find('[data-toggle="tooltip"]').tooltip();

        // Thêm sự kiện click cho nút chỉnh sửa
        const editButton = newImageContainer.querySelector(".img-edit-button");
        editButton.addEventListener("click", handleEditButton);

        // Thêm sự kiện click cho nút xóa
        const deleteButton = newImageContainer.querySelector(".img-delete-button");
        deleteButton.addEventListener("click", handleDeleteImage);

        return newImageContainer;
    }

    // Hàm thêm hình ảnh
    function addImage() {
        type = "album";
        if (rowElement.querySelectorAll(".col-md-4").length >= MAX_IMAGES) {
            Swal.fire({
                icon: 'warning', title: 'Chỉ có thể thêm 9 ảnh vào album',
            });
            return;
        }
        imageInput.click();
    }

    // Hàm xử lý sự kiện xóa hình ảnh
    function handleDeleteImage(event) {
        const imageContainer = event.target.closest(".col-md-4");
        const allImageContainers = rowElement.querySelectorAll(".col-md-4");

        if (allImageContainers.length === 1) {
            Swal.fire({
                icon: 'warning', title: 'Không thể xóa ảnh cuối cùng của album',
            });
            return;
        }
        console.log(imageContainer);
        Swal.fire({
            title: 'Xác nhận xóa',
            text: 'Bạn có chắc muốn xóa hình ảnh này?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Hủy',
        }).then((result) => {
            if (result.isConfirmed) {
                let num = rowElement.querySelectorAll(".col-md-4").length;
                // Gửi yêu cầu delete ảnh cũ
                let oldImageSrc = $(`#album-${num}`).val();
                deleteImage(oldImageSrc);
                removeTooltip(imageContainer);
                imageContainer.remove();
            }
        });
    }

    // Hàm xử lý sự kiện chỉnh sửa hình ảnh
    function handleEditButton(event) {
        editingImageContainer = event.target.closest(".col-md-4");
        let button = editingImageContainer.querySelector(".img-edit-button");
        type = button.getAttribute("data-image-type");
        idImageAlbumEdit = button.getAttribute("data-album-id");
        editImageInput.value = null;
        editImageInput.click();
    }

    // Hàm xử lý sự kiện khi chọn ảnh để chỉnh sửa
    function handleEditImage(event) {
        console.log(event)
        if (editingImageContainer) {
            const file = event.target.files[0];

            var formData = new FormData();
            formData.append("file", file);

            if (!currentUrl.includes("gym-owner/department/update-details")) {
                if (currentUrl.includes("gym-owner/department/image")) {
                    $.ajax({
                        type: "POST",
                        url: `/upload/department/${type}/${$("#brandId").val()}`,
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (response) {
                            console.log(response)
                            if (type == "logo") {
                                $("#imageLogo").val(response);
                            } else if (type == "thumbnail") {
                                $("#imageThumbnail").val(response)
                            } else if (type == "wallpaper") {
                                $("#imageWallpaper").val(response)
                            } else if (type == "album") {
                                let num = idImageAlbumEdit;
                                // Gửi yêu cầu delete ảnh cũ
                                let oldImageSrc = $(`#album-${num}`).val();
                                if(oldImageSrc != "") {
                                    deleteImage(oldImageSrc);
                                }
                                $(`#album-${num}`).val(response);
                            }
                        },
                        error: function () {
                            $("#message").text("Failed to upload file");
                        }
                    });
                } else {
                    $.ajax({
                        type: "POST",
                        url: `/upload/${type}/${$("#brandId").val()}`,
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (response) {
                            console.log(response)
                            if (type == "logo") {
                                $("#imageLogo").val(response);
                            } else if (type == "thumbnail") {
                                $("#imageThumbnail").val(response)
                            } else if (type == "wallpaper") {
                                $("#imageWallpaper").val(response)
                            }
                        },
                        error: function () {
                            $("#message").text("Failed to upload file");
                        }
                    });
                }
            }

            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    const imageElement = editingImageContainer.querySelector("img");
                    imageElement.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        }
    }

    function removeTooltip(element) {
        const tooltips = $(element).find('[data-toggle="tooltip"]');
        tooltips.tooltip('dispose');
    }

});
function deleteImage(imagePath) {
    $.ajax({
        type: "POST",
        url: `/upload/deleteImage`,
        contentType: "application/json",
        data:JSON.stringify( {
            "imagePath" : imagePath
        }),
        success: function (response) {
            console.log(response)
        },
        error: function (error) {
            console.log(error)
        }
    });


}

// jQuery time
var current_fs, next_fs, previous_fs; // fieldsets
var left, opacity, scale; // fieldset properties which we will animate
var animating; // flag to prevent quick multi-click glitches

$(".previous").click(function () {
    if (animating) return false;
    animating = true;

    current_fs = $(this).parent();
    previous_fs = $(this).parent().prev();

    // de-activate current step on the progress bar
    $("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

    // show the previous fieldset
    previous_fs.show();
    // hide the current fieldset with style
    current_fs.animate({opacity: 0}, {
        step: function (now, mx) {
            // as the opacity of current_fs reduces to 0 - stored in "now"
            // 1. scale previous_fs from 80% to 100%
            scale = 0.8 + (1 - now) * 0.2;
            // 2. take current_fs to the right (50%) - from 0%
            left = ((1 - now) * 50) + "%";
            // 3. increase opacity of previous_fs to 1 as it moves in
            opacity = 1 - now;
            current_fs.css({left: left});
            previous_fs.css({transform: "scale(" + scale + ")", position: "relative", opacity: opacity});
        }, duration: 800, complete: function () {
            current_fs.hide();
            animating = false;
        }, // this comes from the custom easing plugin
        easing: "easeInOutBack",
    });
});

// $(".submit").click(function () {
//     return false;
// })

function previewImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            var img = document.querySelector('.show-service-img, .show-department-img');
            img.src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}

//Code Js submit update Profile
$.validator.addMethod("correctNumber", function(value, element) {
    var correctNumber;
    if (value.startsWith("1900") || value.startsWith("1800")) {
        correctNumber = /^(1800|1900)\d{4}$/;
    } else {
        correctNumber = /^(0|84)(9|3|7|8|5)\d{8,10}$/;
    }
    return this.optional(element) || correctNumber.test(value);
}, "Đầu số không đúng định dạng !");

$(document).ready(function () {
    $("#msform").validate({
        rules: {
            brandName: {
                required: true,
                minlength: 3,
                maxlength: 32,
                pattern: /^(?!\s+$).+/,
            },
            phone: {
                required: true,
                minlength: 8,
                maxlength: 11,
                correctNumber: true
            },
            address: {
                required: true,
                maxlength: 150,
                pattern: /^(?!\s+$).+/,
            },
            email: {
                required: true,
                email: true
            },
            comment: {
                required: true,
                minlength: 2,
                maxlength: 700,
                pattern: /^(?!\s+$).+/,
            },
            capacity: {
                required: true,
                number: true,
                min: 0.01,
            },
            area: {
                required: true,
                number: true,
                min: 0.01,
            },
            province: {
                required: true,
            },
            latitude: {
                required: true,
                pattern: /^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?)$/,
            },
            longitude: {
                required: true,
                pattern: /^[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$/,
            },
        },
        messages: {
            brandName: {
                required: "Vui lòng nhập tên cơ sở !",
                minlength: "Tên thương hiệu phải có ít nhất 3 kí tự !",
                maxlength: "Tên thương hiệu không được vượt quá 32 kí tự !",
                pattern: "Tên thương hiệu đang bị trống !",
            },
            phone: {
                required: "Vui lòng nhập số điện thoại !",
                number: "Vui lòng nhập số điện thoại !",
                minlength: 'Đầu số phải có ít nhất 8 số !',
                maxlength: 'Số điện thoại có tối đa 11 số !',
                correctNumber: "Đầu số không đúng định dạng !",
            },
            address: {
                required: "Vui lòng nhập địa chỉ !",
                maxlength: "Địa chỉ không được vượt quá 150 kí tự !",
                pattern: "Địa chỉ đang bị trống !",
            },
            email: {
                required: "Vui lòng nhập email !",
                email: "Vui lòng nhập địa chỉ email hợp lệ !"
            },
            comment: {
                required: "Vui lòng nhập nhập mô tả gói tập !",
                minlength: "Mô tả gói tập phải có ít nhất 2 kí tự !",
                maxlength: "Mô tả gói tập không được vượt quá 700 kí tự !",
                pattern: "Mô tả gói tập đang bị trống !",
            },
            capacity: {
                required: "Vui lòng nhập sức chứa !",
                number: "Vui lòng nhập số !",
                min: "Sức chứa phải lớn hơn 0",
            },
            area: {
                required: "Vui lòng nhập diện tích !",
                number: "Vui lòng nhập số !",
                min: "Diện tích phải lớn hơn 0",
            },
            province: {
                required: "Vui lòng chọn tỉnh/thành phố !",
            },
            latitude: {
                required: "Vui lòng nhập kinh độ!",
                pattern: "Kinh độ của bạn không hợp lệ!",
            },
            longitude: {
                required: "Vui lòng nhập vĩ độ!",
                pattern: "Vĩ độ của bạn không hợp lệ!",
            },
        },
    });

    $(".next").click(function () {
        if (!$("#msform").valid()) {
            return false;
        } else {
            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            // activate next step on progress bar using the index of next_fs
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            // show the next fieldset
            next_fs.show();
            // hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function (now, mx) {
                    // as the opacity of current_fs reduces to 0 - stored in "now"
                    // 1. scale current_fs down to 80%
                    scale = 1 - (1 - now) * 0.2;
                    // 2. bring next_fs from the right (50%)
                    left = (now * 50) + "%";
                    // 3. increase opacity of next_fs to 1 as it moves in
                    opacity = 1 - now;
                    current_fs.css({
                        transform: "scale(" + scale + ")", position: "absolute",
                    });
                    next_fs.css({left: left, opacity: opacity});
                }, duration: 800, complete: function () {
                    current_fs.hide();
                    animating = false;
                }, // this comes from the custom easing plugin
                easing: "easeInOutBack",
            });
        }
    });

    // Handle "Submit" button click
    $("#submitButton").click(function () {
        // Validate the form
        if ($("#msform").valid()) {
            var profileData = {
                brandId: $("#brandId").val(),
                userId: 1,
                brandName: $("#name-brand").val(),
                brandLogoUrl: $("#imageLogo").val(),
                brandWallpaperUrl: $("#imageWallpaper").val(),
                brandThumbnailUrl: $("#imageThumbnail").val(),
                brandDescription: $("#description").val(),
                brandContactNumber: $("#phone").val(),
                brandEmail: $("#email").val(),
                brandStatus: {
                    brandStatusCd: $("#inlineradio1").prop("checked") ? 1 : 2, brandStatusName: "",
                }
            };

            $.ajax({
                type: "POST",
                url: "/brand-owner/updateProfile",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(profileData),
                success: function (response) {
                    console.log("shit")
                    Swal.fire({
                        title: 'Bạn có muốn cập nhật thông tin ?',
                        icon: 'question',
                        showCancelButton: true,
                        confirmButtonText: 'Có',
                        cancelButtonText: 'Không',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                title: 'Vui lòng đợi...',
                                icon: 'info',
                                showConfirmButton: false,
                                timerProgressBar: true,
                                didOpen: () => {
                                    Swal.showLoading();
                                    setTimeout(() => {
                                        $("#formSubmit").submit();
                                        Swal.close();
                                    }, 2000);
                                },
                                didClose: () => {
                                    Swal.fire({
                                        title: 'Thành công!',
                                        text: 'Cập nhật thông tin thành công.',
                                        icon: 'success',
                                        showConfirmButton: false,
                                        timer: 3000
                                    });
                                }
                            });
                        } else {
                            Swal.fire({
                                title: 'Thất bại!',
                                text: 'Cập nhật thông tin thất bại.',
                                icon: 'error',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    });
                },
                error: function (error) {
                    console.error("Error:", error);
                    Swal.fire({
                        title: 'Thất bại!',
                        text: 'Cập nhật thông tin thất bại.',
                        icon: 'error',
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            });
        } else {
            return false;
        }
    });
});
