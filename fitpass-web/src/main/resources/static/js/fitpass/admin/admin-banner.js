document.addEventListener("DOMContentLoaded", function () {
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
        newImageContainer.classList.add("col-md-6");
        newImageContainer.innerHTML = `
      <div class="image-container banner">
        <img src="${imageSrc}" alt="Image">
        <div class="image-actions">
          <button class="img-edit-button" type="button"
            data-toggle="tooltip" data-placement="top"
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
        if (rowElement.querySelectorAll(".col-md-6").length >= MAX_IMAGES) {
            Swal.fire({
                icon: 'warning',
                title: 'Chỉ có thể thêm 9 ảnh vào album',
            });
            return;
        }
        imageInput.click();
    }

    // Hàm xử lý sự kiện xóa hình ảnh
    function handleDeleteImage(event) {
        const imageContainer = event.target.closest(".col-md-6");
        const allImageContainers = rowElement.querySelectorAll(".col-md-6");

        if (allImageContainers.length === 1) {
            Swal.fire({
                icon: 'warning',
                title: 'Không thể xóa ảnh cuối cùng của album',
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
        editingImageContainer = event.target.closest(".col-md-6");
        editImageInput.value = null;
        editImageInput.click();
    }

    // Hàm xử lý sự kiện khi chọn ảnh để chỉnh sửa
    function handleEditImage(event) {
        if (editingImageContainer) {
            const file = event.target.files[0];
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