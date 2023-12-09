var selectedCardItem = null;

$(document).ready(function () {
    // Define validation rules
    $("#bankCardForm").validate({
        rules: {
            cardHolder: {
                required: true,
                maxlength: 50,
                pattern: /^[a-zA-Z\u00C0-\u1EF9 ]*$/,
            },
            bankType: {
                required: true,
            },
            accountNumber: {
                required: true,
                digits: true,
                minlength: 8,
                maxlength: 15,
            },
        },
        messages: {
            cardHolder: {
                required: "Vui lòng nhập họ và tên chủ thẻ",
                maxlength: "Họ và tên không được vượt quá 50 kí tự !",
                pattern: "Họ và tên không được chứa kí tự đặc biệt !",
            },
            bankType: {
                required: "Vui lòng chọn Loại Ngân Hàng",
            },
            accountNumber: {
                required: "Vui lòng nhập số tài khoản",
                digits: "Số tài khoản chỉ có thể chứa các chữ số",
                minlength: "Số Tài Khoản phải có ít nhất 8 số",
                maxlength: "Số Tài Khoản không được vượt quá 15 số",
            },
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
    });

    $("#addCard").click(function () {
        if (!$("#bankCardForm").valid()) {
            return false;
        }
    });
});

$(document).ready(function () {
    $("#cardInfoForm").validate({
        rules: {
            cardHolderName: {
                required: true,
                maxlength: 50,
                pattern: /^[a-zA-Z\u00C0-\u1EF9 ]*$/,
            },
            bankTypeUpdate: {
                required: true,
            },
            receiverAccount: {
                required: true,
                digits: true,
                minlength: 8,
                maxlength: 15,
            },
        },
        messages: {
            cardHolderName: {
                required: "Vui lòng nhập Tên chủ thẻ",
                maxlength: "Họ và tên không được vượt quá 50 kí tự !",
                pattern: "Họ và tên không được chứa kí tự đặc biệt !",
            },
            bankTypeUpdate: {
                required: "Vui lòng chọn Loại Ngân Hàng",
            },
            receiverAccount: {
                required: "Vui lòng nhập Số Tài Khoản",
                digits: "Số Tài Khoản chỉ có thể chứa các chữ số",
                minlength: "Số Tài Khoản phải có ít nhất 8 số",
                maxlength: "Số Tài Khoản không được vượt quá 15 số",
            },
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
    });

    $("#updateCard").click(function () {
        if (!$("#cardInfoForm").valid()) {
            return false;
        }

    });
});

function addBankCard() {
    if (!$("#bankCardForm").valid()) {
        return;
    }

    var cardHolder = document.getElementById("cardHolder").value;
    var bankType = document.getElementById("bankType").value;
    var accountNumber = document.getElementById("accountNumber").value;

    var maskedAccountNumber = "**** **** **** " + accountNumber.slice(-4);

    let creditCardData = {
        creditCardId: 0,
        userId: 0,
        cardOwnerName: cardHolder,
        cardNumber: accountNumber,
        status: "",
        bankName: bankType,
    }

    $.ajax({
        type: "POST",
        url: "/brand-owner/withdrawal/card/add-bank-card",
        data: JSON.stringify(creditCardData),
        contentType: "application/json",
        success: function (response) {
            let creditCardId = response;
            if (creditCardId === -1) {
                Swal.fire("Thất bại!", "Thẻ đã tồn tại.", "error");
                return;
            }
            if (response.errors) {
                handleValidationErrors(response.errors);
                return;
            }
            Swal.fire({
                title: 'Thành công!',
                text: 'Thẻ đã được thêm thành công.',
                icon: 'success',
                confirmButtonText: 'Đóng'
            }).then((result) => {
                if (result.isConfirmed) {
                    var cardItem = document.createElement("li");
                    cardItem.className = "list-bank-card list-group-item d-flex flex-column justify-content-between align-items-start mb-3";
                    cardItem.setAttribute("data-creditCardId", creditCardId);
                    cardItem.setAttribute("data-cardHolder", cardHolder);
                    cardItem.setAttribute("data-bankType", bankType);
                    cardItem.setAttribute("data-accountNumber", accountNumber);
                    cardItem.innerHTML =
                        `<div class="d-flex align-items-center">
                            <i class="bi bi-credit-card-2-front fs-1 pr-3"></i>
                            <span class="fs-4 text-uppercase">${bankType}</span>
                        </div>
                        <span class="fs-5">${maskedAccountNumber}</span>`;
                    cardItem.onclick = function () {
                        selectedCardItem = cardItem;
                        showCardInfo(this);
                        //showCardInfo(creditCardId ,cardHolder, bankType, accountNumber);
                    };
                    document.getElementById("cardList").appendChild(cardItem);
                    document.getElementById("cardHolder").value = "";
                    document.getElementById("bankType").value = "";
                    document.getElementById("accountNumber").value = "";
                }
            });

        },
        error: function (e) {
            Swal.fire({
                title: 'Thêm thẻ thất bại !',
                text: 'Kiểm tra lại Họ tên chủ thẻ và số tài khoản !',
                icon: 'error',
                confirmButtonText: 'Đóng'
            });
        }
    });
}

function showCardInfo(item) {
    let creditCardId = item.getAttribute("data-creditCardId");
    let cardHolder = item.getAttribute("data-cardHolder");
    let bankType = item.getAttribute("data-bankType");
    let accountNumber = item.getAttribute("data-accountNumber");

    document.getElementById("modalCardInfo").innerHTML =
        `<div class="mb-4">
            <label for="cardHolderName" class="form-label">Tên chủ thẻ :</label>
            <input type="hidden" class="form-control" id="creditCardId" value="${creditCardId}">
            <input type="text" class="form-control" id="cardHolderName" value="${cardHolder}">
         </div>
         <div class="mb-4">
            <label for="bankTypeUpdate" class="form-label">Loại ngân hàng :</label>
            <select class="form-select" id="bankTypeUpdate" name="bankType" required>
                <option value="acb" ${bankType === 'acb' ? 'selected' : ''}>ACB</option>
                <option value="agribank" ${bankType === 'agribank' ? 'selected' : ''}>Agribank</option>
                <option value="bacabank" ${bankType === 'bacabank' ? 'selected' : ''}>Bac A Bank</option>
                <option value="bidv" ${bankType === 'bidv' ? 'selected' : ''}>BIDV</option>
                <option value="hdbank" ${bankType === 'hdbank' ? 'selected' : ''}>HDBank</option>
                <option value="mbbank" ${bankType === 'mbbank' ? 'selected' : ''}>MB Bank</option>
                <option value="ncb" ${bankType === 'ncb' ? 'selected' : ''}>NCB</option>
                <option value="oceanbank" ${bankType === 'oceanbank' ? 'selected' : ''}>OceanBank</option>
                <option value="pgbank" ${bankType === 'pgbank' ? 'selected' : ''}>PG Bank</option>
                <option value="saigonbank" ${bankType === 'saigonbank' ? 'selected' : ''}>Saigonbank</option>
                <option value="scb" ${bankType === 'scb' ? 'selected' : ''}>SCB</option>
                <option value="shb" ${bankType === 'shb' ? 'selected' : ''}>SHB</option>
                <option value="techcombank" ${bankType === 'techcombank' ? 'selected' : ''}>Techcombank</option>
                <option value="tpbank" ${bankType === 'tpbank' ? 'selected' : ''}>TPBank</option>
                <option value="vib" ${bankType === 'vib' ? 'selected' : ''}>VIB</option>
                <option value="vietcombank" ${bankType === 'vietcombank' ? 'selected' : ''}>Vietcombank</option>
                <option value="vietinbank" ${bankType === 'vietinbank' ? 'selected' : ''}>Vietinbank</option>
                <option value="vpbank" ${bankType === 'vpbank' ? 'selected' : ''}>VPBank</option>
            </select>
         </div>
         <div class="mb-4">
            <label for="receiverAccount" class="form-label">Số tài khoản :</label>
            <input type="text" class="form-control" id="receiverAccount" value="${accountNumber}">
         </div>`;

    var cardInfoModal = new bootstrap.Modal(document.getElementById('cardInfoModal'));
    cardInfoModal.show();
}

function updateCard() {

    if (!$("#cardInfoForm").valid()) {
        return;
    }

    let creditCardId = document.getElementById("creditCardId").value;
    let cardHolder = document.getElementById("cardHolderName").value;
    let bankType = document.getElementById("bankTypeUpdate").value;
    let accountNumber = document.getElementById("receiverAccount").value;

    let creditCardData = {
        creditCardId: creditCardId,
        userId: 0,
        cardOwnerName: cardHolder,
        cardNumber: accountNumber,
        status: "Đang hoạt động",
        bankName: bankType,
    }

    $.ajax({
        type: "POST",
        url: "/brand-owner/withdrawal/card/update-bank-card",
        data: JSON.stringify(creditCardData),
        contentType: "application/json",
        success: function (response) {
            if (creditCardId === -1) {
                Swal.fire("Thất bại!", "Thẻ đã tồn tại.", "error");
                return;
            }

            if (response.errors) {
                handleValidationErrors(response.errors);
                return;
            }

            Swal.fire({
                title: 'Thành công!',
                text: 'Thẻ đã được cập nhật thành công.',
                icon: 'success',
                confirmButtonText: 'Đóng'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });
        },
        error: function (e) {
            Swal.fire({
                title: 'Cập nhật thẻ thất bại !',
                text: 'Kiểm tra lại Họ tên chủ thẻ và số tài khoản !',
                icon: 'error',
                confirmButtonText: 'Đóng'
            });
        }
    });
}

// Function to delete the selected card
function deleteCard() {

    // Use SweetAlert for confirmation
    Swal.fire({
        title: 'Bạn chắc chắn muốn xóa thẻ?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.isConfirmed) {
            // Delete card from DB
            let creditCardId = document.getElementById("creditCardId").value;
            deleteCardFromDB(creditCardId);
            // selectedCardItem.remove();
            selectedCardItem = null;
            Swal.fire('Đã xóa!', 'Thẻ đã được xóa thành công.', 'success');
        }
    });
}

function deleteCardFromDB(creditCardId) {
    // Delete card from DB
    $.ajax({
        type: "POST",
        url: "/brand-owner/withdrawal/card/delete-bank-card",
        data: JSON.stringify(creditCardId),
        contentType: "application/json",
        success: function (response) {
            Swal.fire({
                title: 'Thành công!',
                text: 'Thẻ đã được cập nhật thành công.',
                icon: 'success',
                confirmButtonText: 'Đóng'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });
        },
        error: function (e) {
            Swal.fire({
                title: 'Thất bại!',
                text: 'Cập nhật thẻ thất bại.',
                icon: 'error',
                confirmButtonText: 'Đóng'
            });
        }
    });
}

// Function to handle validation errors
function handleValidationErrors(errors) {
    Object.keys(errors).forEach((field) => {
        const error = errors[field];
        const inputElement = document.getElementById(field);
        if (inputElement) {
            // Display error message near the corresponding input field
            const errorContainer = document.createElement("div");
            errorContainer.className = "text-danger";
            errorContainer.innerText = error;
            inputElement.parentNode.appendChild(errorContainer);
        }
    });
}

document.getElementById("deleteCardBtn").addEventListener("click", deleteCard);
