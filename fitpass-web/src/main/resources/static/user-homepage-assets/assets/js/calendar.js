document.addEventListener('DOMContentLoaded', function () {
    $.ajax({
        type: "GET",
        url: `/calendar/getListCheckIn`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            loadCalendar(data);
        },
        error: function () {
            alert("Đã xảy ra lỗi trong getListCheckInCalendar");
        }
    });
});

// CALENDAR
function loadCalendar(data){
    var initialLocaleCode = 'vi';
    var calendarEl = document.getElementById('calendar');
    const myModal = new bootstrap.Modal(document.getElementById('form'));
    const dangerAlert = document.getElementById('danger-alert');
    const close = document.querySelector('.btn-close');

    var events = data.map(function(item) {
        return {
            id: item.checkInHistoryId,
            title: item.gymDepartmentName,
            start: item.checkInTime
        };
    });
    console.log(events)

    // không đc phép để null -> để 1 obj trống để insert vào
    var userEvents = JSON.parse(localStorage.getItem('events')) || {

    };
    // Lưu localStorage theo userID
    let userId = document.getElementById("userId").value;

    // Kiểm tra xem userId đã tồn tại trong userEvents chưa
    if (!userEvents[userId]) {
        // Nếu userId chưa tồn tại, tạo một mảng rỗng để lưu trữ các sự kiện của người dùng đó
        userEvents[userId] = [];
    }

    const userEventById = userEvents[userId];

    const allEvent = [...events, ...userEventById];

    console.log(allEvent);


    var calendar = new FullCalendar.Calendar(calendarEl, {
        customButtons: {
            customButton: {
                text: 'Đặt kế hoạch tập',
                click: function () {
                    myModal.show();
                    const modalTitle = document.getElementById('modal-title');
                    const submitButton = document.getElementById('submit-button');
                    modalTitle.innerHTML = 'Kế hoạch tập'
                    submitButton.innerHTML = 'Lưu kế hoạch'
                    submitButton.classList.remove('btn-primary');
                    submitButton.classList.add('btn-primary');
                    close.addEventListener('click', () => {
                        myModal.hide()
                    })
                }
            }
        },
        headerToolbar: {
            right: 'customButton today prev,next',
            center: 'title',
            left: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        locale: initialLocaleCode,
        buttonIcons: true, // show the prev/next text
        weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        editable: false,

        dayMaxEvents: true, // allow "more" link when too many events
        events: allEvent,
        eventDidMount: function (info) {
            info.el.addEventListener('contextmenu', function (e) {
                e.preventDefault();
                let existingMenu = document.querySelector('.context-menu');
                existingMenu && existingMenu.remove();
                let menu = document.createElement('div');
                menu.className = 'context-menu';
                menu.innerHTML = `<ul>
            <li><i class="fas fa-edit"></i>Chỉnh sửa</li>
            <li><i class="fas fa-trash-alt"></i>Xóa</li>
          </ul>`;

                const eventIndex = userEventById.findIndex(event => event.id === info.event.id);

                document.body.appendChild(menu);
                menu.style.top = e.pageY + 'px';
                menu.style.left = e.pageX + 'px';

                // Edit context menu

                menu.querySelector('li:first-child').addEventListener('click', function () {
                    menu.remove();

                    const editModal = new bootstrap.Modal(document.getElementById('form'));
                    const modalTitle = document.getElementById('modal-title');
                    const titleInput = document.getElementById('event-title');
                    const startDateInput = document.getElementById('start-date');
                    const endDateInput = document.getElementById('end-date');
                    const colorInput = document.getElementById('event-color');
                    const submitButton = document.getElementById('submit-button');
                    const cancelButton = document.getElementById('cancel-button');
                    modalTitle.innerHTML = 'Edit Event';
                    titleInput.value = info.event.title;
                    startDateInput.value = moment(info.event.start).format('YYYY-MM-DD');
                    endDateInput.value = moment(info.event.end, 'YYYY-MM-DD').subtract(1, 'day').format('YYYY-MM-DD');
                    colorInput.value = info.event.backgroundColor;
                    submitButton.innerHTML = 'Save Changes';

                    editModal.show();
                    submitButton.classList.remove('btn-success');
                    submitButton.classList.add('btn-primary');

                    // Edit button

                    submitButton.addEventListener('click', function () {
                        const updatedEvents = {
                            id: info.event.id,
                            title: titleInput.value,
                            start: startDateInput.value,
                            end: moment(endDateInput.value, 'YYYY-MM-DD').add(1, 'day').format('YYYY-MM-DD'),
                            backgroundColor: colorInput.value,
                        };

                        if (updatedEvents.end <= updatedEvents.start) {
                            // Add an if statement to check end date
                            dangerAlert.style.display = 'block';
                            return;
                        }

                        const eventIndex = userEventById.findIndex(event => event.id === updatedEvents.id);
                        userEventById.splice(eventIndex, 1, updatedEvents);
                        userEvents[userId] = userEventById;
                        localStorage.setItem('events', JSON.stringify(userEvents));

                        // Update the event in the calendar
                        const calendarEvent = calendar.getEventById(info.event.id);
                        calendarEvent.setProp('title', updatedEvents.title);
                        calendarEvent.setStart(updatedEvents.start);
                        calendarEvent.setEnd(updatedEvents.end);
                        calendarEvent.setProp('backgroundColor', updatedEvents.backgroundColor);

                        editModal.hide();
                    });
                });

                // Delete menu
                menu.querySelector('li:last-child').addEventListener('click', function () {
                    const deleteModal = new bootstrap.Modal(document.getElementById('delete-modal'));
                    const modalBody = document.getElementById('delete-modal-body');
                    const cancelModal = document.getElementById('cancel-button');
                    modalBody.innerHTML = `Bạn có chắc chắn xóa hoạt động <b>"${info.event.title}"</b>`;
                    deleteModal.show();

                    const deleteButton = document.getElementById('delete-button');
                    deleteButton.addEventListener('click', function () {
                        userEventById.splice(eventIndex, 1);
                        userEvents[userId] = userEventById;
                        localStorage.setItem('events', JSON.stringify(userEvents));
                        calendar.getEventById(info.event.id).remove();
                        deleteModal.hide();
                        menu.remove();
                    });

                    cancelModal.addEventListener('click', function () {
                        deleteModal.hide();
                    });
                });

                document.addEventListener('click', function () {
                    menu.remove();
                });
            });
        },

    });

    // calendar.on('select', function (info) {
    //
    //     const startDateInput = document.getElementById('start-date');
    //     const endDateInput = document.getElementById('end-date');
    //     startDateInput.value = info.startStr;
    //     const endDate = moment(info.endStr, 'YYYY-MM-DD').subtract(1, 'day').format('YYYY-MM-DD');
    //     endDateInput.value = endDate;
    //     if (startDateInput.value === endDate) {
    //         endDateInput.value = '';
    //     }
    //     console.log(1);
    // });

    calendar.render();

    const form = document.querySelector('form');

    // Tạo kế hoạch tập
    form.addEventListener('submit', function (event) {
        event.preventDefault(); // prevent default form submission

        // retrieve the form input values
        const title = document.querySelector('#event-title').value;
        const startDate = document.querySelector('#start-date').value;
        const endDate = document.querySelector('#end-date').value;
        const color = document.querySelector('#event-color').value;
        const endDateFormatted = moment(endDate, 'YYYY-MM-DD').add(1, 'day').format('YYYY-MM-DD');
        const eventId = uuidv4();

        console.log(eventId);

        if (endDateFormatted <= startDate) { // add if statement to check end date
            dangerAlert.style.display = 'block';
            return;
        }

        let userId = document.getElementById("userId").value;

        // Kiểm tra xem userId đã tồn tại trong userEvents chưa
        if (!userEvents[userId]) {
            // Nếu userId chưa tồn tại, tạo một mảng rỗng để lưu trữ các sự kiện của người dùng đó
            userEvents[userId] = [];
        }

        const newEvent = {
            id: eventId,
            title: title,
            start: startDate,
            end: endDateFormatted,
            allDay: true,
            backgroundColor: color
        };

        // add the new event to the myEvents array
        // myEvents.push(newEvent);

        userEvents[userId].push(newEvent)

        // render the new event on the calendar
        calendar.addEvent(newEvent);

        // save events to local storage
        localStorage.setItem('events', JSON.stringify(userEvents));

        myModal.hide();
        form.reset();
    });

    myModal._element.addEventListener('hide.bs.modal', function () {
        dangerAlert.style.display = 'none';
        form.reset();
    });

    // Trong sự kiện click trên ngày trong lịch
    calendar.on('eventClick', function (info) {
        // Lấy thông tin buổi tập tương ứng với ngày này
        //const workoutData = getWorkoutDataForDate(info.id);

        getWorkoutDataForDate(info.event.id, function (data) {
            console.log(data);
            if (data !== null) {
                // Xử lý dữ liệu và trả về đối tượng chứa thông tin buổi tập
                const workoutData = {
                    gymLocation: data.gymDepartmentName,
                    gymAddress: data.address,
                    date: data.date,
                    checkInTime: data.time,
                    membershipPackage: data.gymPlanName
                };

                if(data.feedBackId == 0) {
                    document.getElementById('gym-location').value = workoutData.gymLocation;
                    document.getElementById('gym-address').value = workoutData.gymAddress;
                    document.getElementById('workout-date').value = workoutData.date;
                    document.getElementById('check-in-time').value = workoutData.checkInTime;
                    document.getElementById('membership-package').value = workoutData.membershipPackage;

                    document.getElementById("checkInHistoryIdReview").value = data.checkInHistoryId;
                    document.getElementById("departmentIdReview").value = data.gymDepartmentId;

                    const detailModal = new bootstrap.Modal(document.getElementById('detail-modal'));
                    detailModal.show();
                } else {
                    $.ajax({
                        type: "GET",
                        url: `/calendar/getFeedback?id=${data.checkInHistoryId}`,
                        success: function (feedback) {
                            console.log("feedback", feedback)
                            document.getElementById('gym-location-review').value = workoutData.gymLocation;
                            document.getElementById('gym-address-review').value = workoutData.gymAddress;
                            document.getElementById('workout-date-review').value = workoutData.date;
                            document.getElementById('check-in-time-review').value = workoutData.checkInTime;
                            document.getElementById('membership-package-review').value = workoutData.membershipPackage;

                            document.getElementById("checkInHistoryIdReviewed").value = data.checkInHistoryId;

                            document.getElementById("departmentIdReviewed").value = data.gymDepartmentId;
                            document.getElementById("feedbackId").value = feedback.feedbackId;



                            $('#star-rating-reviewed').attr('data-star', feedback.rating);
                            const detailModal = new bootstrap.Modal(document.getElementById('post-review-detail-modal'));
                            detailModal.show();
                        },
                        error: function () {
                            alert("Đã xảy ra lỗi trong getFeedback");
                        }
                    });
                }




            } else {
                // Xử lý lỗi nếu có.
                console.error("Đã xảy ra lỗi trong getListCheckInCalendar");
            }
        });

    });

    // Hàm để lấy thông tin buổi tập dựa trên ngày (thay thế bằng dữ liệu thực tế của bạn)
    function getWorkoutDataForDate(id, callback) {
        // Thực hiện việc truy vấn dữ liệu hoặc xác định thông tin buổi tập dựa trên ngày
        $.ajax({
            type: "GET",
            url: `/calendar/getDetail?id=${id}`,
            success: function (data) {
                console.log(data);
                // Nếu có thông báo thì hiện
                callback(data);
            },
            error: function () {
                callback(null);
            }
        });
        // Trả về đối tượng chứa thông tin buổi tập
        // Ví dụ:
        // return {
        //     gymLocation: "SKY CITY TOWER - QUẬN ĐỐNG ĐA",
        //     gymAddress: "Sky City, Tầng M, 88 Láng Hạ, P.Láng Hạ, Q.Đống Đa, Hà Nội",
        //     date: date,
        //     checkInTime: "12:36 AM",
        //     membershipPackage: "Gói linh hoạt"
        // };
    }

    // Xử lý khi người dùng nhấp vào liên kết "Đánh giá ngay"
    const reviewLink = document.getElementById('review-link');
    reviewLink.addEventListener('click', function (event) {
        event.preventDefault();

        // Lấy thông tin gymLocation
        const gymLocation = document.getElementById('gym-location').value;

        // Hiển thị modal đánh giá
        const reviewModal = new bootstrap.Modal(document.getElementById('review-modal'));
        reviewModal.show();

        // Hiển thị gymLocation trong modal đánh giá
        const gymLocationInReviewModal = document.getElementById('gym-location-in-review-modal');
        gymLocationInReviewModal.innerHTML = `<strong>Cơ sở tập: ${gymLocation}</strong>`;
    });

    const reviewedLink = document.getElementById('reviewed-link');
    reviewedLink.addEventListener('click', function (event) {
        event.preventDefault();

        // Lấy thông tin gymLocation
        const gymLocation = document.getElementById('gym-location').value;

        // Hiển thị modal đánh giá
        const reviewedModal = new bootstrap.Modal(document.getElementById('reviewed-modal'));
        reviewedModal.show();

        // Hiển thị gymLocation trong modal đánh giá
        const gymLocationInReviewModal = document.getElementById('gym-location-in-reviewed-modal');
        gymLocationInReviewModal.innerHTML = `<strong>Cơ sở tập: ${gymLocation}</strong>`;

        let id = document.getElementById("checkInHistoryIdReviewed").value;

        $.ajax({
            type: "GET",
            url: `/calendar/getFeedback?id=${id}`,
            success: function (data) {
                var radioId = 'star' + data.rating;
                $('#your-rating ' + '#' + radioId).prop('checked', true);
                $('#your-thoughts').val(data.comments);
            },
            error: function () {
                alert("Đã xảy ra lỗi trong getListCheckInCalendar");
            }
        });
    });
}