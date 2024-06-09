flatpickr("#search-filter-start-date", { dateFormat: "d/m/Y" })
flatpickr("#search-filter-end-date", { dateFormat: "d/m/Y" })

const pageHeader = $("#page-header")
const pageHeader_searchInput = pageHeader.find(".search-box-navbar .search-input-navbar")
const pageHeader_searchFilterSection = pageHeader.find(".search-filter-section")
const pageHeader_searchFilterModal = pageHeader_searchFilterSection.find(".search-filter-modal")
const pageHeader_filterForm = $("#search-filter-form")
const pageHeader_searchForm = $("#search-form-navbar")

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
pageHeader_searchForm.on("submit", searchSeries)

// kích hoạt khi nhấn nút "Lọc và tìm kiếm phim" của filter section
pageHeader_filterForm.on("submit", searchSeries)
