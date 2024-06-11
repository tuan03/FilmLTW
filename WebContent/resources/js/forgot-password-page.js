$("#type-email-form").on("submit", function (e) {
    e.preventDefault()

    const form = e.target
    const formData = new FormData(form)

    const email = formData.get("email")

    let valid = true

    if (!email) {
        valid = false
        $(this).find(".form-group .form-group-message").attr("hidden", false)
    } else {
        if (email_regex.test(email)) {
            $(this).find(".form-group .form-group-message").attr("hidden", true)
        } else {
            valid = false
            $(this).find(".form-group .form-group-message").attr("hidden", false)
        }
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể gửi email đến máy chủ!")
    }
})
