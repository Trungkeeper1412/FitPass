var selectedCardItem = null;

function addBankCard() {
    var cardHolder = document.getElementById("cardHolder").value;
    var bankType = document.getElementById("bankType").value;
    var accountNumber = document.getElementById("accountNumber").value;

    var maskedAccountNumber = "**** **** **** " + accountNumber.slice(-4);

    var cardItem = document.createElement("li");
    cardItem.className = "list-group-item d-flex flex-column justify-content-between align-items-start mb-3";
    cardItem.innerHTML =
        `<div class="d-flex align-items-center">
            <i class="bi bi-credit-card-2-front fs-1 pr-3"></i>
            <span class="fs-4 text-uppercase">${bankType}</span>
        </div>
        <span class="fs-5">${maskedAccountNumber}</span>`;

    cardItem.onclick = function () {
        selectedCardItem = cardItem;
        showCardInfo(cardHolder, bankType, accountNumber);
    };
    document.getElementById("cardList").appendChild(cardItem);
    document.getElementById("cardHolder").value = "";
    document.getElementById("bankType").value = "";
    document.getElementById("accountNumber").value = "";
}

function showCardInfo(cardHolder, bankType, accountNumber) {
    document.getElementById("modalCardInfo").innerHTML =
        `<div class="mb-3">
            <label for="cardHolderName" class="form-label">Tên chủ thẻ :</label>
            <input type="text" class="form-control" id="cardHolderName" value="${cardHolder}">
         </div>
         <div class="mb-3">
            <label for="bankType" class="form-label">Loại ngân hàng :</label>
            <select class="form-select" id="bankType" name="bankType" required>
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

// Function to delete the selected card
function deleteCard() {
    if (selectedCardItem) {
        // Use SweetAlert for confirmation
        Swal.fire({
            title: 'Bạn chắc chắn muốn xóa thẻ?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Hủy bỏ'
        }).then((result) => {
            if (result.isConfirmed) {
                selectedCardItem.remove();
                selectedCardItem = null;
                Swal.fire('Đã xóa!', 'Thẻ đã được xóa thành công.', 'success');
            }
        });
    }
}

document.getElementById("deleteCardBtn").addEventListener("click", deleteCard);