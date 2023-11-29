var selectedCardItem = null;

function addBankCard() {
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

    // Add card to DB
    $.ajax({
        type: "POST",
        url: "/brand-owner/withdrawal/card/add-bank-card",
        data: JSON.stringify(creditCardData),
        contentType: "application/json",
        success: function (response) {
            let creditCardId = response;
            if(creditCardId === -1) {
                Swal.fire("Thất bại!", "Thẻ đã tồn tại.", "error");
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
                title: 'Thất bại!',
                text: 'Thêm thẻ thất bại.',
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
        `<div class="mb-3">
            <label for="cardHolderName" class="form-label">Tên chủ thẻ :</label>
            <input type="hidden" class="form-control" id="creditCardId" value="${creditCardId}">
            <input type="text" class="form-control" id="cardHolderName" value="${cardHolder}">
         </div>
         <div class="mb-3">
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
         <div class="mb-3">
            <label for="receiverAccount" class="form-label">Số tài khoản :</label>
            <input type="text" class="form-control" id="receiverAccount" value="${accountNumber}">
         </div>`;

    var cardInfoModal = new bootstrap.Modal(document.getElementById('cardInfoModal'));
    cardInfoModal.show();
}

function updateCard() {
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

    // Update card to DB
    $.ajax({
        type: "POST",
        url: "/brand-owner/withdrawal/card/update-bank-card",
        data: JSON.stringify(creditCardData),
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

document.getElementById("deleteCardBtn").addEventListener("click", deleteCard);
