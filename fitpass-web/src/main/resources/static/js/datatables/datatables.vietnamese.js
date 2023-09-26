var vietnamese = {
  "sProcessing": "Đang xử lý...",
  "sLengthMenu": "Xem _MENU_ mục",
  "sZeroRecords": "Không có dữ liệu",
  "sInfo": "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
  "sInfoEmpty": "Đang xem 0 đến 0 trong tổng số 0 mục",
  "sInfoFiltered": "(được lọc từ _MAX_ mục)",
  "sInfoPostFix": "",
  "sSearch": "Tìm kiếm:",
  "sUrl": "",
  "oPaginate": {
    "sFirst": "Đầu",
    "sPrevious": "Trước",
    "sNext": "Tiếp",
    "sLast": "Cuối"
  }
};

$(document).ready(function() {
  $('#order-history-table, #member-table').dataTable({
    "oLanguage": vietnamese
  });
});

$(document).ready(function() {
  $('#order-history-table, #member-table').DataTable();
});