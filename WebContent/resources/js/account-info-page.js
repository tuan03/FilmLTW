const pageMain = $("#page-main")
const accountInfoForm = $("#account-info-form")

accountInfoForm.on("submit", function (e) {
    e.preventDefault()
    const form = e.target

    const formData = new FormData(form)
    const email = formData.get("email")
    const username = formData.get("username")
    const gender = formData.get("gender")
    console.log(">>> form data >>>", { email, username, gender })

    let valid = true

    if (!email) {
        valid = false
        $(this).find(".form-group.email .invalid-message").attr("hidden", false)
    } else {
        if (email_regex.test(email)) {
            $(this).find(".form-group.email .invalid-message").attr("hidden", true)
        } else {
            valid = false
            $(this).find(".form-group.email .invalid-message").attr("hidden", false)
        }
    }
    if (!username) {
        valid = false
        $(this).find(".form-group.username .invalid-message").attr("hidden", false)
    } else {
        $(this).find(".form-group.username .invalid-message").attr("hidden", true)
    }

    if (valid) {
        form.submit()
    } else {
        toastr.error("Không thể cập nhật thông tin người dùng!")
    }
})
