<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partical/head.jsp" %>
    <link rel="stylesheet" href="resources/css/forgot-password-page.css">
    <title>AnimeVS - Anime Vietsub</title>
<%@ include file="../partical/header.jsp" %>
  

    <main id="page-main">
        <form action="forgot-password.htm" method="post" id="type-email-form">
            <div class="form-title">
                QUÊN MẬT KHẨU
            </div>
            <div class="form-description">
                Chúng tôi sẽ gửi thông tin tới địa chỉ email liên kết với tài khoản của bạn.
            </div>
            <div class="form-message" hidden>
                <div class="invalid">
                    <i class="bi bi-exclamation-triangle-fill"></i>
                    <span>Lỗi gì đó!!!</span>
                </div>
                <div class="valid">
                    <i class="bi bi-check-circle-fill"></i>
                    <span>Đã gửi.</span>
                </div>
            </div>
            <div class="form-group">
                <div class="input-wrapper">
                    <input type="text" id="email" name="email" placeholder="Nhập email của bạn..." />
                </div>
                <div class="form-group-message" hidden>
                    <i class="bi bi-exclamation-triangle-fill"></i>
                    <span>Vui lòng nhập đúng định dạng địa chỉ email</span>
                </div>
            </div>
            <button type="submit" class="submit-btn">
                <span>Gửi</span>
                <i class="bi bi-send-fill"></i>
            </button>
        </form>
    </main>

    <!-- independent script -->
    <script src="resources/js/forgot-password-page.js" defer></script>


	<!-- independent script -->
    <script src="<c:url value='/resources/js/login-page.js'/>" defer></script>
<%@ include file="../partical/footer.jsp" %>