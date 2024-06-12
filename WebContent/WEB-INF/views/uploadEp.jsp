<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp" %>
     <link rel="stylesheet" href="resources/css/update-series-page.css">
    <title>AnimeVS - Anime Vietsub</title>
<%@ include file="partical/header.jsp" %>
   

   



    <main id="page-main">

        <section class="upload-new-episode-section">
            <div class="section-title-box">
                <h2 class="section-title">Thêm tập mới cho phim:</h2>
            </div>
            <div class="upload-new-episode-box">
                <label for="upload-new-episode-input" class="upload-new-episode-btn">
                    <span>Tải lên tập mới</span>
                    <span>+</span>
                </label>
                <input type="file" accept="video/*" id="upload-new-episode-input" hidden />
            </div>
            <div class="upload-progress-bar"></div>
            <div class="new-episode-title">
                <input id="upload-video-title" type="text" placeholder="Nhập tiêu đề cho tập mới ở đây..." />
				<input id="upload-video-ep-number" type="text" placeholder="Nhập số tập cho tập mới ở đây..." />
            </div>
        </section>

    </main>


    <script src="resources/js/update-series-page.js" defer></script>


	<!-- independent script -->
    <script src="<c:url value='resources/js/login-page.js'/>" defer></script>
<%@ include file="partical/footer.jsp" %>