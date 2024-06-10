const changePasswordForm = $("#change-password-form")

changePasswordForm.on("submit", function (e) {
    e.preventDefault()

    const oldPassword = $("#old-password").val()
    const newPassword = $("#new-password").val()
    const retypeNewPassword = $("#retype-new-password").val()

    let valid = true

    if (!oldPassword) {
        valid = false
        const invalidMessage = $("#old-password").closest(".form-group").find(".invalid-message")
        invalidMessage.find(".message").html("Vui lòng không bỏ trống trường này")
        invalidMessage.attr("hidden", false)
    } else {
        $("#old-password").closest(".form-group").find(".invalid-message").attr("hidden", true)
    }

    if (!newPassword) {
        valid = false
        const invalidMessage = $("#new-password").closest(".form-group").find(".invalid-message")
        invalidMessage.find(".message").html("Vui lòng không bỏ trống trường này")
        invalidMessage.attr("hidden", false)
    } else {
        if (password_regex.test(newPassword)) {
            $("#new-password").closest(".form-group").find(".invalid-message").attr("hidden", true)
        } else {
            $("#new-password")
                .closest(".form-group")
                .find(".invalid-message .message")
                .html(
                    "Mật khẩu phải chứa ít nhất một chữ cái thường, một chữ cái in hoa và một số."
                )
        }
    }

    if (!retypeNewPassword) {
        valid = false
        const invalidMessage = $("#retype-new-password")
            .closest(".form-group")
            .find(".invalid-message")
        invalidMessage.find(".message").html("Vui lòng không bỏ trống trường này")
        invalidMessage.attr("hidden", false)
    } else {
        if (retypeNewPassword !== newPassword) {
            valid = false
            $("#retype-new-password")
                .closest(".form-group")
                .find(".invalid-message message")
                .html("Mật khẩu nhập lại không khớp.")
        } else {
            $("#retype-new-password")
                .closest(".form-group")
                .find(".invalid-message")
                .attr("hidden", true)
        }
    }

    if (valid) {
        e.target.submit()
    } else {
        toastr.error("Không thể cập nhật thông tin người dùng!")
    }
})
