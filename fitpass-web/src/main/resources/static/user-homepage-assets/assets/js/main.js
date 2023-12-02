/**
 * Template Name: Gp
 * Updated: Aug 30 2023 with Bootstrap v5.3.1
 * Template URL: https://bootstrapmade.com/gp-free-multipurpose-html-bootstrap-template/
 * Author: BootstrapMade.com
 * License: https://bootstrapmade.com/license/
 */
document.addEventListener('DOMContentLoaded', function () {
    updateQuantityCart()
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
            cartNum.textContent = number;
        })
        .catch((error) => {
            console.error("Error:", error);
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
                detailCard.querySelector(".activate-time span").textContent = element.getAttribute("data-item-before-active")
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

//change avatar
let user_img = document.getElementById('user-ava');
let input_img = document.getElementById('exampleFormControlFile1');
input_img.onchange = (e) => {
    if (input_img.files[0])
        user_img.src = URL.createObjectURL(input_img.files[0]);
};



