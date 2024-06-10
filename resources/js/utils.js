toastr.options = {
    closeButton: false,
    debug: false,
    newestOnTop: false,
    progressBar: true,
    positionClass: "toast-bottom-left",
    preventDuplicates: false,
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "3000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut",
}

const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
})

const password_regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/
const email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

flatpickr("#search-filter-start-date", { dateFormat: "d/m/Y" })
flatpickr("#search-filter-end-date", { dateFormat: "d/m/Y" })

const pageHeader = $("#page-header")
const pageHeader_searchInput = pageHeader.find(".search-box-navbar .search-input-navbar")
const pageHeader_searchFilterSection = pageHeader.find(".search-filter-section")
const pageHeader_searchFilterModal = pageHeader_searchFilterSection.find(".search-filter-modal")

pageHeader_searchInput.on("focus", function (e) {
    pageHeader_searchFilterSection.attr("hidden", false)
})

pageHeader_searchFilterModal.on("click", function (e) {
    pageHeader_searchFilterSection.attr("hidden", true)
})

function searchSeries(e) {
    e.preventDefault()

    const startDate = $("#search-filter-start-date").val()
    const endtDate = $("#search-filter-end-date").val()
    const genre = $("#search-filter-genre").val()
    const keyword = $("#search-input-navbar").val()
    console.log(">>> filter >>>", { startDate, endtDate, genre, keyword })

    let valid = true

    if (!keyword) {
        toastr.error("Trường tìm kiếm trống!")
        valid = false
    }

    if (valid) {
        e.target.submit()
    }
}

// kích hoạt khi nhấn phím enter của thẻ input search trên thanh header
$("#search-form-navbar").on("submit", searchSeries)

// kích hoạt khi nhấn nút "Lọc và tìm kiếm phim" của filter section
$("#search-filter-form").on("submit", searchSeries)

async function logoutHandler(e) {
    e.preventDefault()
    e.target.submit()
}

$("#logout-form").on("submit", logoutHandler)
