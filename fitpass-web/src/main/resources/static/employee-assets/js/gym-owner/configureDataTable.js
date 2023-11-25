// Khởi tạo đối tượng để lưu trữ các selectedIds cho từng bảng
var selectedIdsObject = {};
function configureDataTable(tableId, checkboxColumnIndex, idColumnIndex, selectedInputId) {
    var dataTable = $(tableId).DataTable({
        select: {
            style: 'multi',
            selector: 'td:last-child'
        },
        columnDefs: [
            {
                className: 'select-checkbox',
                targets: checkboxColumnIndex,
                orderDataType: 'dom-checkbox'
            },
            {
                targets: [idColumnIndex],
                visible: false,
                data: "ID"
            }
        ],
        language: {
            "url": '//cdn.datatables.net/plug-ins/1.13.7/i18n/vi.json'
        }
    });

    // Sự kiện khi hàng được chọn
    dataTable.on('select', function (e, dt, type, indexes) {
        var rowData = dataTable.rows(indexes).data().toArray();
        rowData.forEach(function (row) {
            console.log(row);
            // Lấy tên của bảng (tableId) để xác định mảng selectedIds tương ứng
            var tableName = $(tableId).attr('id');
            if (!selectedIdsObject[tableName]) {
                selectedIdsObject[tableName] = [];
            }
            selectedIdsObject[tableName].push(row.ID); // Thêm ID vào mảng

            // Cập nhật giá trị của input type = hidden
            updateHiddenInput(selectedInputId, selectedIdsObject[tableName].join(','));
        });
        console.log("Selected IDs:", selectedIdsObject);
    });

    // Sự kiện khi hàng bị hủy chọn
    dataTable.on('deselect', function (e, dt, type, indexes) {
        var rowData = dataTable.rows(indexes).data().toArray();
        rowData.forEach(function (row) {
            var tableName = $(tableId).attr('id');
            var index = selectedIdsObject[tableName].indexOf(row.ID);

            if (index !== -1) {
                selectedIdsObject[tableName].splice(index, 1); // Xóa ID khỏi mảng
            }

            // Cập nhật giá trị của input type = hidden
            updateHiddenInput(selectedInputId, selectedIdsObject[tableName].join(','));
        });
        console.log("Selected IDs:", selectedIdsObject);
    });
}

function updateHiddenInput(inputId, value) {
    var hiddenInput = $('#' + inputId);
    if (hiddenInput.length) {
        hiddenInput.val(value);
    }
}