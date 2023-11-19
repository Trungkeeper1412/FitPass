$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

// --------------------------------------------------------------------------------------------------------------------------------
document.addEventListener("DOMContentLoaded", function () {
    var type = "";
    const MAX_IMAGES = 9;
    const rowElement = document.querySelector(".row-add");
    const imageInput = document.getElementById("imageInput");
    const addButton = document.getElementById("addImageButton");
    const editImageInput = createEditImageInput();
    let editingImageContainer = null;

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
                    $("imageWallpaper").val(response)
                }
            },
            error: function () {
                $("#message").text("Failed to upload file");
            }
        });

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

        Swal.fire({
            title: 'Xác nhận xóa',
            text: 'Bạn có chắc muốn xóa hình ảnh này?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Hủy',
        }).then((result) => {
            if (result.isConfirmed) {
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

// jQuery time
var current_fs, next_fs, previous_fs; // fieldsets
var left, opacity, scale; // fieldset properties which we will animate
var animating; // flag to prevent quick multi-click glitches

$(".next").click(function () {
    if (animating) return false;
    animating = true;

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
});

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

$(".submit").click(function () {
    return false;
})

$(document).ready(function () {
    function configureDataTable(tableId) {
        $(tableId).DataTable({
            "paging": true, "lengthMenu": [10, 25, 50, 100], "searching": true,
            "bDestroy": true
        });
    }

    // Bảng "Gói linh hoạt"
    configureDataTable('#dataTableFlexible');

    // Bảng "Gói cố định"
    configureDataTable('#dataTableFixed');

    // Bảng "Checked Gói linh hoạt"
    configureDataTable('#dataTableCheckedFlexible');

    // Bảng "Checked Gói cố định"
    configureDataTable('#dataTableCheckedFixed');

    configureDataTable('#dataTableAmenities');

    configureDataTable('#dataTableFeatures');
});

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

$("#submitButton").click(function () {
    var profileData = {
        brandId: $("#brandId").val(),
        userId: 1,
        brandName: $("#name-brand").val(),
        brandLogoUrl: $("#imageLogo").val(),
        brandWallpaperUrl: $("#imageWallpaper").val(),
        brandThumbnailUrl: $("#imageThumbnail").val(),
        brandDescription: $("#description").text(),
        brandContactNumber: $("#phone").val(),
        brandEmail: $("#email").val(),
        brandStatus: {
            brandStatusCd: $("#inlineradio1").prop("checked") ? 1 : 2, brandStatusName: "",
        }
    };

    console.log(JSON.stringify(profileData));

    $.ajax({
        type: "POST",
        url: "/brand-owner/updateProfile",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(profileData),
        success: function (response) {
            console.log("Success:", response);

            Swal.fire({
                title: 'Đang cập nhật...', timer: 5000,
                timerProgressBar: true, allowOutsideClick: false,
                onBeforeOpen: () => {
                    Swal.showLoading();
                }
            });


            setTimeout(function () {
                Swal.fire({
                    icon: 'success', title: 'Cập nhật thành công', showConfirmButton: false,
                    timer: 2000
                }).then(() => {
                    window.location.href = "/brand-owner/department/list"
                });
            }, 5000);
        },
        error: function (error) {
            console.error("Error:", error);
            // Xử lý lỗi (nếu có)
        }
    });
})
