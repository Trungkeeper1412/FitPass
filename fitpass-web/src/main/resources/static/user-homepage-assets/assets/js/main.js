/**
 * Template Name: Gp
 * Updated: Aug 30 2023 with Bootstrap v5.3.1
 * Template URL: https://bootstrapmade.com/gp-free-multipurpose-html-bootstrap-template/
 * Author: BootstrapMade.com
 * License: https://bootstrapmade.com/license/
 */
document.addEventListener('DOMContentLoaded', function () {
    // Đây là nơi bạn có thể gọi hàm của bạn
    updateQuantityCart();
    // Gọi hàm check thông báo confirm
    checkConfirmCheckIn();
    checkConfirmCheckOut();
    checkNotificationEmployee();
});

function updateQuantityCart() {
    let cartNum = document.querySelector(".cart-badge");
    fetch("/cart/quantityInCart", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then((data) => {
            let number = parseInt(data);
            console.log(number);
            cartNum.textContent = number;
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function checkNotificationEmployee() {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
    // Gửi yêu cầu lên sever lấy ra thông báo thành công
    $.ajax({
        type: "GET",
        url: `/notification/notificationCheckInSuccessEmployee`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            if (data.length > 0) {
                console.log('Các thông báo trả về từ người dùng check in thành công đến employee', data);
                data.forEach(function (item) {
                    // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                    sendSeenNotification(item.notificationId);
                    toastr["success"](`${item.message}`, "Xác nhận check in")
                })
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong checkNotificationEmployee");
        }
    });

    $.ajax({
        type: "GET",
        url: `/notification/notificationCheckOutSuccessEmployee`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            if (data.length > 0) {
                console.log('Các thông báo trả về từ người dùng check in đến employee', data);
                data.forEach(function (item) {
                    // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                    sendSeenNotification(item.notificationId);
                    toastr["success"](`${item.message}`, "Xác nhận check out")
                })
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong checkNotificationEmployee");
        }
    });

    $.ajax({
        type: "GET",
        url: `/notification/notificationCheckInCancelEmployee`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            if (data.length > 0) {
                console.log('Các thông báo trả về từ người dùng hủy check in đến employee', data);
                data.forEach(function (item) {
                    // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                    sendSeenNotification(item.notificationId);
                    toastr["error"](`${item.message}`, "Xác nhận check in")
                })
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong checkNotificationEmployee");
        }
    });

    $.ajax({
        type: "GET",
        url: `/notification/notificationCheckOutCancelEmployee`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            if (data.length > 0) {
                console.log('Các thông báo trả về từ người dùng hủy check in đến employee', data);
                data.forEach(function (item) {
                    // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                    sendSeenNotification(item.notificationId);
                    toastr["error"](`${item.message}`, "Xác nhận check out")
                })
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong checkNotificationEmployee");
        }
    });
}

function sendSeenNotification(id) {
    $.ajax({
        type: "GET",
        url: `/notification/seen?id=${id}`,
        success: function (data) {
            console.log("Trạng thái update status seen notification: ", data)
        },
        error: function () {
            alert("Đã xảy ra lỗi trong quá trình update status seen notification");
        }
    });
}

function checkConfirmCheckIn() {
    $.ajax({
        type: "GET",
        url: `/notification/confirmCheckIn`,
        success: function (data) {
            // Nếu có thông báo thì hiện
            if (data.notificationId > 0) {
                console.log('Thông báo xác nhận check in', data);
                // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                sendSeenNotification(data.notificationId);
                Swal.fire({
                    title: `Xác nhận check in`,
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'No',
                    reverseButtons: true,
                }).then((result) => {
                    // Nếu người dùng nhấn xác nhận thì gửi yêu cầu lên sever
                    // Khi người dùng nhấn xác nhận thì mình sẽ insert vào history các trường
                    if (result.isConfirmed) {
                        $.ajax({
                            type: "GET",
                            url: `/employee/flexible/checkin?id=${data.message}&uis=${data.userIdSend}&uir=${data.userIdReceive}&di=${data.departmentId}&cancel=no`,
                            success: function (data) {

                            },
                            error: function () {
                                alert("Đã xảy ra lỗi gửi check in thành công");
                            }
                        });
                    } else {
                        $.ajax({
                            type: "GET",
                            url: `/employee/flexible/checkin?id=${data.message}&uis=${data.userIdSend}&uir=${data.userIdReceive}&di=${data.departmentId}&cancel="yes"`,
                            success: function (data) {

                            },
                            error: function () {
                                alert("Đã xảy ra lỗi gửi check in không thành công");
                            }
                        });
                    }
                })
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong quá trình lấy thông báo xác nhận check in");
        }
    });
}

function checkConfirmCheckOut() {
    $.ajax({
        type: "GET",
        url: `/notification/confirmCheckOut`,
        success: function (data) {

            // Nếu có thông báo thì hiện
            if (data.orderDetailConfirmCheckOut.durationHavePractice > 0) {
                console.log('Thông báo xác nhận check out', data);
                // Gửi lại yêu cầu đã đọc lên sever (chuyển status của notification = 1 là đã đọc)
                sendSeenNotification(data.notification.notificationId);
                if (data.orderDetailConfirmCheckOut.creditAfterPay < 0) {
                    Swal.fire({
                        title: `Bạn không đủ số dư credit để thanh toán`,
                        icon: 'error',
                        html: `
<p class="fw-bold">Phòng tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.departmentName}</span></p>
<p  class="fw-bold">Gói tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.gymPlanName}</span></p>
<p  class="fw-bold">Giá gói: <span class="fw-normal">${data.orderDetailConfirmCheckOut.pricePerHours} credit/giờ</span></p>
<p  class="fw-bold">Đã tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.durationHavePractice} phút</span></p>
<p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditInWallet} credit</span></p>
<p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
<p  class="fw-bold">Số dư credit còn lại: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditAfterPay} credit</span></p>
                `,
                        showCancelButton: true,
                        confirmButtonText: 'Nạp thêm credit',
                        cancelButtonText: 'No',
                        reverseButtons: true,
                    })
                } else {
                    Swal.fire({
                        title: `Xác nhận thanh toán`,
                        icon: 'question',
                        html: `
<p class="fw-bold">Phòng tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.departmentName}</span></p>
<p  class="fw-bold">Gói tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.gymPlanName}</span></p>
<p  class="fw-bold">Giá gói: <span class="fw-normal">${data.orderDetailConfirmCheckOut.pricePerHours} credit/giờ</span></p>
<p  class="fw-bold">Đã tập: <span class="fw-normal">${data.orderDetailConfirmCheckOut.durationHavePractice} phút</span></p>
<p  class="fw-bold">Số dư credit trong ví: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditInWallet} credit</span></p>
<p  class="fw-bold">Số credit cần trả: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditNeedToPay} credit</span></p>
<p  class="fw-bold">Số dư credit còn lại: <span class="fw-normal">${data.orderDetailConfirmCheckOut.creditAfterPay} credit</span></p>
                `,
                        showCancelButton: true,
                        confirmButtonText: 'Yes',
                        cancelButtonText: 'No',
                        reverseButtons: true,
                    }).then((result) => {
                        // Nếu người dùng nhấn xác nhận thì gửi yêu cầu lên sever
                        if (result.isConfirmed) {
                            const dataToSend = {
                                orderDetailId: data.orderDetailConfirmCheckOut.orderDetailId,
                                checkInHistoryId: data.orderDetailConfirmCheckOut.historyCheckInId,
                                checkOutTime: data.orderDetailConfirmCheckOut.checkOutTime,
                                totalCredit: data.orderDetailConfirmCheckOut.creditNeedToPay,
                                creditAfterPay: data.orderDetailConfirmCheckOut.creditAfterPay,
                                notification: data.notification,
                                cancel: "No"
                            };
                            $.ajax({
                                type: "POST",
                                url: `/employee/flexible/checkout`,
                                headers: {
                                    "Content-Type": "application/json",
                                },
                                data: JSON.stringify(dataToSend),
                                success: function (data) {
                                    console.log("Update check out time vào db check in history thành công", data)
                                },
                                error: function () {
                                    alert("Đã xảy ra lỗi trong quá trình check in");
                                }
                            });
                        } else {
                            const dataToSend = {
                                orderDetailId: data.orderDetailConfirmCheckOut.orderDetailId,
                                checkInHistoryId: data.orderDetailConfirmCheckOut.historyCheckInId,
                                checkOutTime: data.orderDetailConfirmCheckOut.checkOutTime,
                                totalCredit: data.orderDetailConfirmCheckOut.creditNeedToPay,
                                creditAfterPay: data.orderDetailConfirmCheckOut.creditAfterPay,
                                notification: data.notification,
                                cancel: "Yes"
                            };
                            $.ajax({
                                type: "POST",
                                url: `/employee/flexible/checkout`,
                                headers: {
                                    "Content-Type": "application/json",
                                },
                                data: JSON.stringify(dataToSend),
                                success: function (data) {
                                    console.log("Update check out time vào db check in history thành công", data)
                                },
                                error: function () {
                                    alert("Đã xảy ra lỗi trong quá trình check in");
                                }
                            });
                        }
                    })
                }
            }
        },
        error: function () {
            alert("Đã xảy ra lỗi trong quá trình lấy thông báo xác nhận check in");
        }
    });
}

(function () {
    "use strict";

    /**
     * Easy selector helper function
     */
    const select = (el, all = false) => {
        el = el.trim()
        if (all) {
            return [...document.querySelectorAll(el)]
        } else {
            return document.querySelector(el)
        }
    }

    /**
     * Easy event listener function
     */
    const on = (type, el, listener, all = false) => {
        let selectEl = select(el, all)
        if (selectEl) {
            if (all) {
                selectEl.forEach(e => e.addEventListener(type, listener))
            } else {
                selectEl.addEventListener(type, listener)
            }
        }
    }

    /**
     * Easy on scroll event listener
     */
    const onscroll = (el, listener) => {
        el.addEventListener('scroll', listener)
    }

    /**
     * Navbar links active state on scroll
     */
    let navbarlinks = select('#navbar .scrollto', true)
    const navbarlinksActive = () => {
        let position = window.scrollY + 200
        navbarlinks.forEach(navbarlink => {
            if (!navbarlink.hash) return
            let section = select(navbarlink.hash)
            if (!section) return
            if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
                navbarlink.classList.add('active')
            } else {
                navbarlink.classList.remove('active')
            }
        })
    }
    window.addEventListener('load', navbarlinksActive)
    onscroll(document, navbarlinksActive)

    /**
     * Scrolls to an element with header offset
     */
    const scrollto = (el) => {
        let header = select('#header')
        let offset = header.offsetHeight

        let elementPos = select(el).offsetTop
        window.scrollTo({
            top: elementPos - offset,
            behavior: 'smooth'
        })
    }

    /**
     * Toggle .header-scrolled class to #header when page is scrolled
     */
    let selectHeader = select('#header')
    if (selectHeader) {
        const headerScrolled = () => {
            if (window.scrollY > 100) {
                selectHeader.classList.add('header-scrolled')
            } else {
                selectHeader.classList.remove('header-scrolled')
            }
        }
        window.addEventListener('load', headerScrolled)
        onscroll(document, headerScrolled)
    }

    /**
     * Back to top button
     */
    let backtotop = select('.back-to-top')
    if (backtotop) {
        const toggleBacktotop = () => {
            if (window.scrollY > 100) {
                backtotop.classList.add('active')
            } else {
                backtotop.classList.remove('active')
            }
        }
        window.addEventListener('load', toggleBacktotop)
        onscroll(document, toggleBacktotop)
    }

    /**
     * Mobile nav toggle
     */
    on('click', '.mobile-nav-toggle', function (e) {
        select('#navbar').classList.toggle('navbar-mobile')
        this.classList.toggle('bi-list')
        this.classList.toggle('bi-x')
    })

    /**
     * Mobile nav dropdowns activate
     */
    on('click', '.navbar .dropdown > a', function (e) {
        if (select('#navbar').classList.contains('navbar-mobile')) {
            e.preventDefault()
            this.nextElementSibling.classList.toggle('dropdown-active')
        }
    }, true)

    /**
     * Scrool with ofset on links with a class name .scrollto
     */
    on('click', '.scrollto', function (e) {
        if (select(this.hash)) {
            e.preventDefault()

            let navbar = select('#navbar')
            if (navbar.classList.contains('navbar-mobile')) {
                navbar.classList.remove('navbar-mobile')
                let navbarToggle = select('.mobile-nav-toggle')
                navbarToggle.classList.toggle('bi-list')
                navbarToggle.classList.toggle('bi-x')
            }
            scrollto(this.hash)
        }
    }, true)

    /**
     * Scroll with ofset on page load with hash links in the url
     */
    window.addEventListener('load', () => {
        if (window.location.hash) {
            if (select(window.location.hash)) {
                scrollto(window.location.hash)
            }
        }
    });

    /**
     * Preloader
     */
    let preloader = select('#preloader');
    if (preloader) {
        window.addEventListener('load', () => {
            preloader.remove()
        });
    }

    /**
     * Clients Slider
     */
    new Swiper('.clients-slider', {
        speed: 400,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
            clickable: true
        },
        breakpoints: {
            320: {
                slidesPerView: 2,
                spaceBetween: 40
            },
            480: {
                slidesPerView: 3,
                spaceBetween: 60
            },
            640: {
                slidesPerView: 4,
                spaceBetween: 80
            },
            992: {
                slidesPerView: 6,
                spaceBetween: 120
            }
        }
    });

    /**
     * Porfolio isotope and filter
     */
    window.addEventListener('load', () => {
        let portfolioContainer = select('.portfolio-container');
        if (portfolioContainer) {
            let portfolioIsotope = new Isotope(portfolioContainer, {
                itemSelector: '.portfolio-item'
            });

            let portfolioFilters = select('#portfolio-flters li', true);

            on('click', '#portfolio-flters li', function (e) {
                e.preventDefault();
                portfolioFilters.forEach(function (el) {
                    el.classList.remove('filter-active');
                });
                this.classList.add('filter-active');

                portfolioIsotope.arrange({
                    filter: this.getAttribute('data-filter')
                });
                portfolioIsotope.on('arrangeComplete', function () {
                    AOS.refresh()
                });
            }, true);
        }

    });

    /**
     * Initiate portfolio lightbox
     */
    const portfolioLightbox = GLightbox({
        selector: '.portfolio-lightbox'
    });

    /**
     * Portfolio details slider
     */
    new Swiper('.portfolio-details-slider', {
        speed: 400,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
            clickable: true
        }
    });

    /**
     * Testimonials slider
     */
    new Swiper('.testimonials-slider', {
        speed: 600,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false
        },
        slidesPerView: 'auto',
        pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
            clickable: true
        }
    });

    /**
     * Animation on scroll
     */
    window.addEventListener('load', () => {
        AOS.init({
            duration: 1000,
            easing: "ease-in-out",
            once: true,
            mirror: false
        });
    });

    /**
     * Initiate Pure Counter
     */
    new PureCounter();

    /* Phân trang */
    document.addEventListener("DOMContentLoaded", function () {
        const pageLinks = document.querySelectorAll(".page-link");
        const tabPanes = document.querySelectorAll(".tab-pane");

        pageLinks.forEach(function (link) {
            link.addEventListener("click", function (event) {
                event.preventDefault();

                // Loại bỏ lớp "active" từ tất cả các tab pane
                tabPanes.forEach(function (pane) {
                    pane.classList.remove("active");
                });

                // Lấy id của tab pane được chọn
                const targetId = this.getAttribute("href").substring(1);

                // Hiển thị tab pane tương ứng
                document.getElementById(targetId).classList.add("active");
            });
        });
    });

    /* Inventory */
    document.addEventListener("DOMContentLoaded", function () {
        // Add click event listeners for item cards in each tab
        addClickListeners("all");
        addClickListeners("unactivated");
        addClickListeners("active");
        addClickListeners("activated");

        function showItemDetail(element, tabName) {
            // Tìm các class có chứa "tab-pane" của tab đang được chọn
            var tabContent = document.querySelector(".tab-pane.active");

            // Tạo ID thẻ chi tiết dựa trên tab
            var detailCardId = "item-detail-card-" + tabName;

            // Tìm thẻ chi tiết trong nội dung tab hiện tại
            var detailCard = tabContent.querySelector("#" + detailCardId);

            // Ẩn thông báo "Chưa chọn vật phẩm"
            tabContent.querySelector("#noItemSelected").style.display = "none";

            // Hiển thị chi tiết vật phẩm
            detailCard.style.display = "block";

            // Cập nhật thông tin chi tiết vật phẩm từ data-attributes của element
            detailCard.querySelector("#gymName").textContent = element.getAttribute("data-gym-name");
            detailCard.querySelector("#itemType").textContent = "Loại thẻ: " + element.getAttribute("data-item-type");
            detailCard.querySelector("#itemDescription").textContent = "Mô tả: " + element.getAttribute("data-item-description");
            detailCard.querySelector("#itemPrice").textContent = "Giá thẻ: " + element.getAttribute("data-item-price");
            detailCard.querySelector("#itemStatus").textContent = "Trạng thái: " + element.getAttribute("data-item-status");

            var activationDate = element.getAttribute("data-activation-date") == "null" ? "null" : new Date(element.getAttribute("data-activation-date"));
            // Duration
            var activationPeriod = parseInt(element.getAttribute("data-activation-period"), 10);
            // Plan after active valid
            var planAfterActiveValid = parseInt(element.getAttribute("data-item-plan-after-active"), 10);
            var purchaseDate = new Date(element.getAttribute("data-item-buyDate"));

            if (activationDate != "null") {
                // Tính ngày hết hạn bằng cộng thời hạn kích hoạt vào ngày mua
                var expirationDate = new Date(activationDate);
                expirationDate.setDate(expirationDate.getDate() + planAfterActiveValid);
            }

            // Hiển thị ngày mua, thời hạn kích hoạt và ngày hết hạn
            detailCard.querySelector(".purchase-date span").textContent = activationDate == "null" ? formatDate(purchaseDate) : formatDate(activationDate);
            detailCard.querySelector(".activation-period span").textContent = activationPeriod + " ngày";

            if (element.getAttribute("data-item-status") == "Chưa kích hoạt") {
                detailCard.querySelector(".expiration-date").style.display = "none"
                detailCard.querySelector(".activate-time").style.display = "block"
                detailCard.querySelector(".activate-time span").textContent = element.getAttribute("data-item-duration")
            } else {
                detailCard.querySelector(".expiration-date").style.display = "block"
                detailCard.querySelector(".activate-time").style.display = "none"
                detailCard.querySelector(".expiration-date span").textContent = activationDate == "null" ? "" : formatDate(expirationDate);
            }


            detailCard.querySelector(".orderDetailId").value = element.getAttribute("data-item-id");
            detailCard.querySelector(".duration").value = element.getAttribute("data-item-plan-after-active");

            console.log(detailCard.querySelector(".orderDetailId").value)
            console.log(detailCard.querySelector(".duration").value)
            console.log(element.getAttribute("data-item-id"));
            console.log(element.getAttribute("data-item-duration"));

            // Check xem trạng thái của gói là chưa kích hoạt thì hiện nút kích hoạt
            const displayValue = element.getAttribute("data-item-status") == "Chưa kích hoạt" ? "block" : "none";
            detailCard.querySelector(".formActive").style.display = displayValue;
        }

        function addClickListeners(tabName) {
            var itemCards = document.querySelectorAll(".item-card-" + tabName);

            for (var i = 0; i < itemCards.length; i++) {
                itemCards[i].addEventListener("click", function () {
                    showItemDetail(this, tabName);
                });
            }
        }


    });

    function formatDate(date) {
        var day = date.getDate();
        var month = date.getMonth() + 1; // Lưu ý rằng tháng bắt đầu từ 0
        var year = date.getFullYear();

        // Đảm bảo rằng ngày và tháng có hai chữ số
        if (day < 10) {
            day = '0' + day;
        }
        if (month < 10) {
            month = '0' + month;
        }

        return day + '/' + month + '/' + year;
    }

    $(document).ready(function () {
        // Bắt sự kiện khi một tab được nhấn
        $('#myTabs a').on('click', function (e) {
            e.preventDefault();
            $(this).tab('show');
        });
    });
})()
$(function () {
    $("#include-navbar").load("navbar.html");
    $("#include-footer").load("footer.html");
});




