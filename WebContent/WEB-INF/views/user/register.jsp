<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partical/head.jsp" %>
    <link rel="stylesheet" href="resources/css/sign-up-page.css">
    <title>Đăng Kí</title>

<%@ include file="../partical/header.jsp" %>

    <main id="page-main">
        <div class="sign-up-form-box">
            <form:form id="sign-up-form" action="sign-up.htm" method="post" modelAttribute="User">
                <div class="form-header">
                    <h2>Đăng ký</h2> 

                </div>
                <div class="form-group">
                    <label for="email">
                        <i class="bi bi-person-fill"></i>
                        <span>Họ và Tên</span>
                    </label>
                    <input id="full-name" name="fullname" placeholder="Nhập họ và tên của bạn..." value="${User.fullname }">
                    <div class="invalid-message" hidden>
                        <i class="bi bi-exclamation-triangle-fill"></i>
                        <span>Vui lòng nhập họ và tên.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">
                        <i class="bi bi-envelope"></i>
                        <span>Email đăng nhập</span>
                    </label>
                    <input id="email" name="email" placeholder="Nhập email của bạn..." value="${User.email }">
                    <div class="invalid-message" hidden>
                        <i class="bi bi-exclamation-triangle-fill"></i>
                        <span>Vui lòng nhập email hợp lệ.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">
                        <i class="bi bi-lock-fill"></i>
                        <span>Mật khẩu</span>
                    </label>
                    <div class="input-wrapper">
                        <input type="password" id="password" name="password" placeholder="Nhập mật khẩu của bạn...">
                        <div class="password-btn shown" hidden onclick="hide_show_password(this,true)">
                            <i class="bi bi-eye"></i>
                        </div>
                        <div class="password-btn hidden" onclick="hide_show_password(this,false)">
                            <i class="bi bi-eye-slash"></i>
                        </div>
                    </div>
                    <div class="invalid-message" hidden>
                        <i class="bi bi-exclamation-triangle-fill"></i>
                        <span>Mật khẩu phải chứa ít nhất một chữ cái thường, một chữ cái in hoa và một số.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">
                        <i class="bi bi-lock-fill"></i>
                        <span>Nhập lại mật khẩu</span>
                    </label>
                    <div class="input-wrapper">
                        <input type="password" id="retype-password" name="retype-password"
                            placeholder="Nhập lại mật khẩu của bạn...">
                        <div class="password-btn shown" hidden onclick="hide_show_password(this,true)">
                            <i class="bi bi-eye"></i>
                        </div>
                        <div class="password-btn hidden" onclick="hide_show_password(this,false)">
                            <i class="bi bi-eye-slash"></i>
                        </div>
                    </div>
                    <div class="invalid-message" hidden>
                        <i class="bi bi-exclamation-triangle-fill"></i>
                        <span>Mật khẩu nhập lại không khớp.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remember"
                        style="font-size: 0.9rem; text-align: center; padding: 0 5px; font-style: italic;">
                        Khi nhấn vào nút ĐĂNG KÝ bạn đồng ý với chính sách
                        điều khoản của chúng tôi.
                    </label>
                </div>
                <button type="submit" class="btn-register">ĐĂNG KÝ</button>
                <div class="login-group">
                    <span>Đã có tài khoản?</span>
                    <a href="login.htm" class="btn-login">ĐĂNG NHẬP!</a>
                </div>
            </form:form>
        </div>
    </main>

    <script src="resources/js/sign-up-page.js" defer></script>
    



<%@ include file="../partical/footer.jsp" %>