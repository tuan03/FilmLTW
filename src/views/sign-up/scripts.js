const signUpForm = $("#sign-up-form")

// đăng ký
signUpForm.on("submit", function (e) {
    e.preventDefault()

    const fullName = $("#full-name").val()
    const email = $("#email").val()
    const password = $("#password").val()
    const confirmPassword = $("#retype-password").val()

    let valid = true

    if (!fullName) {
        $("#full-name").closest(".form-group").find(".invalid-message").attr("hidden", false)
        valid = false
    } else {
        $("#full-name").closest(".form-group").find(".invalid-message").attr("hidden", true)
    }

    if (!validateEmail(email)) {
        // Validate email
        $("#email").closest(".form-group").find(".invalid-message").attr("hidden", false)
        valid = false
    } else {
        $("#email").closest(".form-group").find(".invalid-message").attr("hidden", true)
    }

    // Validate password length
    if (!validatePassword(password)) {
        $("#password").closest(".form-group").find(".invalid-message").attr("hidden", false)
        valid = false
    } else {
        $("#password").closest(".form-group").find(".invalid-message").attr("hidden", true)
    }

    // Validate password confirmation
    if (password !== confirmPassword || !password || !confirmPassword) {
        $("#retype-password").closest(".form-group").find(".invalid-message").attr("hidden", false)
        valid = false
    } else {
        $("#retype-password").closest(".form-group").find(".invalid-message").attr("hidden", true)
    }

    if (valid) {
        e.target.submit()
    } else {
        toastr.error("Lỗi! Không thể đăng ký, vui lòng kiểm tra lại các trường!")
    }
})

const hide_show_password = (target, is_shown_btn) => {
    const input = target.parentElement.closest(".input-wrapper").querySelector("input")
    if (is_shown_btn) {
        input.type = "password"
        target.hidden = true
        target.nextElementSibling.hidden = false
    } else {
        input.type = "text"
        target.hidden = true
        target.previousElementSibling.hidden = false
    }
}

function validateEmail(email) {
    return email_regex.test(email)
}

function validatePassword(password) {
    return password_regex.test(password)
}
