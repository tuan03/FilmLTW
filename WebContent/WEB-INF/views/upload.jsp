<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp" %>
     <link rel="stylesheet" href="resources/css/upload-series-page.css">
    <title>AnimeVS - Anime Vietsub</title>

<%@ include file="partical/header.jsp" %>
   
    <main id="page-main">
        <section class="upload-video-section">
            <div class="upload-video-container">
                <h1 class="upload-video-title">Đăng tải phim</h1>
                <p class="notice">Các trường với <span class="required">*</span> là bắt buộc</p>
                <form action="upload.htm" method="post" id="upload-video-form" enctype="multipart/form-data">
                    <div class="d-flex justify-content-between" style="column-gap: 20px;">
                        <div style="width: 30%;">
                            <div class="form-group movie-cover">
                                <label for="movie-cover">
                                    <span>Bìa phim</span>
                                    <span class="required">*</span>
                                </label>
                                <input type="file" id="movie-cover" name="movie-cover" accept="image/*" />
                                <div class="invalid-message" hidden>Vui lòng chọn bìa phim</div>
                                <div id="movie-cover-preview"></div>
                            </div>
                        </div>
                        <div style="width: 70%;">
                            <div class="form-group movie-title">
                                <label for="movie-title">
                                    <span>Tiêu đề</span>
                                    <span class="required">*</span>
                                </label>
                                <input type="text" id="movie-title" name="movie-title"
                                    placeholder="Nhập tiêu đề phim...">
                                <div class="invalid-message" hidden>Vui lòng nhập tiêu đề cho phim</div>
                            </div>
                            <div class="form-group movie-description">
                                <label for="movie-description">Mô tả</label>
                                <textarea id="movie-description" name="movie-description" rows="5"
                                    placeholder="Nhập mô tả phim..."></textarea>
                                <div class="invalid-message" hidden>Vui lòng nhập mô tả cho phim</div>
                            </div>
                            <div class="form-group movie-genre">
                                <label for="movie-genre">
                                    <span>Thể loại phim</span>
                                    <span class="required">*</span>
                                </label>
                                <div class="select-genres-box container">
                                    <div class="dropdown">
                                        <button class="select-genres-btn btn btn-secondary dropdown-toggle"
                                            type="button" data-bs-toggle="dropdown">
                                            Chọn các thể loại cho phim
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            
                                            <c:forEach var="genre" items="${genres }">
                                            <li>
                                                <span class="dropdown-item" href="#" data-genre-id="${genre.id }"
                                                    data-genre-label="Hành động">
                                                    ${genre.name }
                                                </span>
                                            </li>
                                            </c:forEach>
                                            
                                        </ul>
                                    </div>
                                    <div id="chips-container" class="d-flex flex-wrap"></div>
                                </div>
                                <div class="invalid-message" hidden>Vui lòng chọn ít nhất 1 thể loại cho phim</div>
                            </div>
                            <div class="form-group release-date">
                                <label for="release-date">
                                    <span>Ngày công chiếu</span>
                                    <span class="required">*</span>
                                </label>
                                <input type="date" id="release-date" name="release-date"
                                    placeholder="Chọn ngày công chiếu...">
                                <div class="invalid-message" hidden>Vui lòng chọn ngày công chiếu cho phim</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit">Đăng tải</button>
                    </div>
                </form>
            </div>
        </section>
    </main>


    <!-- independent script -->
    <script src="resources/js/upload-series-page.js" defer></script>
<%@ include file="partical/footer.jsp" %>