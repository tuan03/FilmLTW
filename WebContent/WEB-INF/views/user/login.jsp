<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partical/head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/login-page.css'/>">
    <title>Đăng nhập</title>

<%@ include file="../partical/header.jsp" %>
    <main id="page-main">
        <div class="login-form-box">
            <form id="login-form" action="login.htm" method="post">
                <div class="form-header">
                    <h2>Đăng nhập</h2>
                </div>
                <div class="form-group">
                    <label for="email">
                        <i class="bi bi-envelope"></i>
                        <span>Email đăng nhập</span>
                    </label>
                    <input type="email" id="email" name="email" placeholder="Nhập email của bạn...">
                    <div class="invalid-message" hidden>
                        <i class="bi bi-exclamation-triangle-fill"></i>
                        <span>Vui lòng nhập email.</span>
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
                        <span>Vui lòng nhập mật khẩu.</span>
                    </div>
                </div>
                <div class="form-group">
                    <a href="forgot-password.htm" class="forgot-password">Quên mật khẩu?</a>
                </div>
                <div class="form-group checkbox-group">
                    <input type="checkbox" id="remember" name="remember">
                    <label for="remember">Lưu mật khẩu</label>
                </div>
                <button type="submit" class="btn-login">ĐĂNG NHẬP</button>
                <div class="register-group">
                    <span>Chưa có tài khoản?</span>
                    <a href="sign-up.htm" class="btn-register">ĐĂNG KÝ ngay!</a>
                </div>
            </form>
        </div>
    </main>

	<!-- independent script -->
    <script src="<c:url value='/resources/js/login-page.js'/>" defer></script>
<%@ include file="../partical/footer.jsp" %>