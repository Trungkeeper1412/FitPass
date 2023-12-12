function showCancelConfirmation(type) {
    Swal.fire({
        title: 'Xác nhận hủy đơn',
        input: 'text',
        icon: 'warning',
        inputPlaceholder: 'Nhập lý do hủy...',
        showCancelButton: true,
        confirmButtonText: 'Xác nhận',
        cancelButtonText: 'Hủy bỏ',
        showLoaderOnConfirm: true,
        inputValidator: (value) => {
            return new Promise((resolve) => {
                if (value.trim() === '') {
                    resolve('Lý do hủy không được để trống');
                } else if (value.length > 270) {
                    resolve('Lý do hủy không được quá 200 kí tự');
                } else {
                    resolve();
                }
            });
        },
        preConfirm: (reason) => {
            return new Promise((resolve) => {
                setTimeout(() => {
                    let becomeAPartnerRequestId;
                    if(type === 'pending') {
                        becomeAPartnerRequestId = $("#becomeAPartnerRequestIdPending").val();
                    } else if(type === 'processing') {
                        becomeAPartnerRequestId = $("#becomeAPartnerRequestIdProcessing").val();
                    }
                    console.log(reason)
                    $.ajax({
                        url: '/admin/registration/update-status',
                        type: 'POST',
                        data: JSON.stringify({
                            becomeAPartnerRequestId: becomeAPartnerRequestId,
                            status: 'Từ chối đơn',
                            cancelReason: reason
                        }),
                        contentType: 'application/json',
                        success: function (result) {
                            Swal.fire('Xử lý hủy đơn thành công', '', 'success').then(
                                function () {
                                    resolve();
                                    location.reload();
                                }
                            );
                            console.log(result);

                        },
                        error: function (e) {
                            Swal.fire('Xử lý hủy đơn thất bại', '', 'error');
                            console.log(e);
                            resolve();
                        }
                    });
                    if(type === 'pending'){
                        $('#pendingModal').modal('hide');
                    } else if(type === 'processing') {
                        $('#processingModal').modal('hide');
                    }
                }, 300);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    });
}

function showProcessingConfirmation(type) {
    Swal.fire({
        title: 'Xác nhận xử lý đơn',
        text: 'Bạn chắc chắn muốn xử lý đơn này?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonText: 'Xác nhận',
        cancelButtonText: 'Hủy bỏ',
        showLoaderOnConfirm: true,
        preConfirm: () => {
            return new Promise((resolve) => {
                // Perform any processing logic here
                setTimeout(() => {
                    resolve();
                    if(type === 'pending') {
                        $.ajax({
                            url: '/admin/registration/update-status',
                            type: 'POST',
                            data: JSON.stringify({
                                becomeAPartnerRequestId: $("#becomeAPartnerRequestIdPending").val(),
                                status: 'Đang xử lý',
                                cancelReason: ''
                            }),
                            contentType: 'application/json',
                            success: function (result) {
                                Swal.fire('Xử lý đơn thành công', '', 'success').then(
                                    function () {
                                        location.reload();
                                    }
                                );
                                $('#pendingModal').modal('hide');
                                console.log(result);
                            },
                            error: function (e) {
                                Swal.fire('Xử lý đơn thất bại', '', 'error');
                                console.log(e);
                            }
                        })
                    } else if(type === 'processing') {
                        $.ajax({
                            url: '/admin/registration/update-status',
                            type: 'POST',
                            data: JSON.stringify({
                                becomeAPartnerRequestId: $("#becomeAPartnerRequestIdProcessing").val(),
                                status: 'Thành công',
                                cancelReason: '',
                            }),
                            contentType: 'application/json',
                            success: function (result) {
                                Swal.fire('Xử lý đơn thành công', '', 'success').then(
                                    function () {
                                        location.reload();
                                    }
                                );
                                $('#processingModal').modal('hide');
                                console.log(result);
                            },
                            error: function (e) {
                                Swal.fire('Xử lý đơn thất bại', '', 'error');
                                console.log(e);
                            }
                        })
                    }
                    // $('#pendingModal').modal('hide');
                }, 300);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    });
}

function showDetailPending(id) {
    $.ajax({
        url: '/admin/registration/detail/' + id,
        type: 'GET',
        success: function (result) {
            console.log(result);
            $("#becomeAPartnerRequestIdPending").val(result.becomeAPartnerRequestId);
            $("#brandNamePending").val(result.brandName);
            $("#brandOwnerPending").val(result.brandOwnerName);
            $("#emailPending").val(result.contactEmail);
            $("#phoneNumberPending").val(result.contactNumber);
            $("#requestTimePending").val(formatISODate(result.sendRequestTime));
            $("#addressPending").val(result.address);
            $("#websitePending").val(result.webUrl);
        }
    })

}

function showDetailProcessing(id) {
    $.ajax({
        url: '/admin/registration/detail/' + id,
        type: 'GET',
        success: function (result) {
            $("#becomeAPartnerRequestIdProcessing").val(result.becomeAPartnerRequestId);
            $("#brandNameProcessing").val(result.brandName);
            $("#brandOwnerProcessing").val(result.brandOwnerName);
            $("#emailProcessing").val(result.contactEmail);
            $("#phoneNumberProcessing").val(result.contactNumber);
            $("#requestTimeProcessing").val(formatISODate(result.sendRequestTime));
            $("#addressProcessing").val(result.address);
            $("#websiteProcessing").val(result.webUrl);
            $("#requestTimeStartProcessing").val(formatISODate(result.startHandleRequestTime));
        }
    })
}

function showDetailSuccess(id) {
    $.ajax({
        url: '/admin/registration/detail/' + id,
        type: 'GET',
        success: function (result) {
            $("#becomeAPartnerRequestIdSuccess").val(result.becomeAPartnerRequestId);
            $("#brandNameSuccess").val(result.brandName);
            $("#brandOwnerSuccess").val(result.brandOwnerName);
            $("#emailSuccess").val(result.contactEmail);
            $("#phoneNumberSuccess").val(result.contactNumber);
            $("#requestTimeSuccessSuccess").val(formatISODate(result.approveHandleRequestTime));
            $("#requestTimeSuccess").val(formatISODate(result.sendRequestTime));
            $("#addressSuccess").val(result.address);
            $("#websiteSuccess").val(result.webUrl);
            $("#requestTimeProcessSuccess").val(formatISODate(result.startHandleRequestTime));
        }
    })
}

function showDetailCancel(id) {
    $.ajax({
        url: '/admin/registration/detail/' + id,
        type: 'GET',
        success: function (result) {
            $("#becomeAPartnerRequestIdSuccess").val(result.becomeAPartnerRequestId);
            $("#brandNameCancel").val(result.brandName);
            $("#brandOwnerCancel").val(result.brandOwnerName);
            $("#emailCancel").val(result.contactEmail);
            $("#phoneNumberCancel").val(result.contactNumber);
            $("#cancelHandleRequest").val(formatISODate(result.cancelHandleRequestTime));
            $("#addressCancel").val(result.address);
            $("#websiteCancel").val(result.webUrl);
            $("#requestTimeStartCancel").val(formatISODate(result.startHandleRequestTime));
            $("#cancelReason").val(result.cancelReason);
        }
    })
}

function formatISODate(isoDateString) {
    const date = new Date(isoDateString);
    const hours = String(date.getUTCHours()+ 7).padStart(2, '0');
    const minutes = String(date.getUTCMinutes()).padStart(2, '0');
    const day = String(date.getUTCDate()).padStart(2, '0');
    const month = String(date.getUTCMonth() + 1).padStart(2, '0'); // Months are zero-based
    const year = date.getUTCFullYear();

    return `${hours}:${minutes} ${day}/${month}/${year}`;
}
