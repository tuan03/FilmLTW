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

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

const password_regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/
const email_regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
