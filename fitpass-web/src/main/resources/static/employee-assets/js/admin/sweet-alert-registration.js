function showCancelConfirmation() {
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
                } else {
                    resolve();
                }
            });
        },
        preConfirm: (reason) => {
            return new Promise((resolve) => {
                setTimeout(() => {
                    resolve();
                    Swal.fire('Hủy đơn thành công', '', 'success');
                    $('#pendingModal').modal('hide');
                }, 300);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    });
}

function showProcessingConfirmation() {
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
                    Swal.fire('Xử lý đơn thành công', '', 'success');
                    $('#pendingModal').modal('hide');
                    // $('#pendingModal').modal('hide');
                }, 300);
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    });
}