<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp"%>
<!-- independent style -->
<link rel="stylesheet" href="resources/css/update-series-page.css">
<title>AnimeVS - Anime Vietsub</title>
<%@ include file="partical/header.jsp"%>

<main id="page-main">
	<section class="series-info-section">
		<div class="section-title-box">
			<h2 class="section-title">Chỉnh sửa thông tin phim:</h2>
		</div>
		<div class="first-words">Click chuột vào khu vực bạn muốn chỉnh
			sửa và thực hiện chỉnh sửa.</div>
		<form:form class="series-info" id="update-series-form" modelAttribute="NewMovie"
			action="admin/update-series/${movie.id }.htm" method="post">
			<div class="series-title">
				<input type="text" value="${movie.title }" name="title" />
			</div>
			<div class="main-content">
				<img class="series-poster" src=${movie.posterUrl }
					alt="${movie.title }">
				<div class="details">
					<div class="tags">
						<div class="tags-title">Thể loại:</div>
						<div class="genres">
							<c:forEach var="genre" items="${movieGenres }">
								<span>${genre.name }</span>
							</c:forEach>
						</div>
						<button type="button" class="add-genre" onclick="addGenres(this)"
							data-type-is-add="true">
							<i class="bi bi-plus-circle-fill"></i> <span>Thêm</span>
						</button>
					</div>
					<div class="select-genres-box container" hidden>
						<div class="dropdown">
							<button
								class="select-genres-btn btn btn-secondary dropdown-toggle"
								type="button" data-bs-toggle="dropdown">Chọn các thể
								loại cho phim</button>
							<ul class="dropdown-menu">
								<c:forEach var="genre" items="${genres }">
									<li><span class="dropdown-item"
										data-genre-id="${genre.id }" data-genre-label="${genre.name }">${genre.name }</span></li>
								</c:forEach>
							</ul>
						</div>
						<div id="chips-container" class="d-flex flex-wrap"></div>
					</div>
					<textarea name="description" class="series-description">${movie.description }</textarea>
				</div>
			</div>
			</div>
			<div class="submit-btn-box">
				<span></span>
				<button type="submit" class="update-btn">Cập nhật thông tin
					phim</button>
			</div>
		</form:form>
	</section>

	<section class="upload-new-episode-section">
		<div class="section-title-box">
			<h2 class="section-title">Thêm tập mới cho phim:</h2>
		</div>
		<div class="upload-new-episode-box">
			<label for="upload-new-episode-input" class="upload-new-episode-btn">
				<span>Tải lên tập mới</span> <span>+</span>
			</label> <input type="file" accept="video/*" id="upload-new-episode-input"
				hidden />
		</div>
		<div class="upload-progress-bar"></div>
		<form action="eposide/upload.htm?id=${movie.id }" method="post" id="episode-info-form-after-upload" hidden enctype="multipart/form-data">
                <div class="first-words">Nhập thông tin cho tập mới tải lên</div>
                <div class="form-groups-container">
                    <div class="form-group new-episode-url">
                        <input type="text" placeholder="URL của video" name="urlVideo" readonly />
                        <div class="invalid-message"></div>
                    </div>
                    <div class="form-group new-episode-title">
                        <input type="text" placeholder="Nhập tiêu đề cho tập mới ở đây..." name="title" />
                        <div class="invalid-message"></div>
                    </div>
                    <div class="form-group new-episode-ep-number">
                        <input type="text" placeholder="Nhập số tập cho tập mới ở đây..." name="numberEp" />
                        <div class="invalid-message"></div>
                    </div>
                </div>
                <button class="save-data-btn" type="submit">Lưu thông tin</button>
            </form>
	</section>

	<section class="episodes-section">
		<h2 class="section-title">DANH SÁCH TẬP</h2>
		<div class="total-videos">Tổng số: ${movieEpisodes.size() } tập</div>
		<div class="episodes-list">
			<c:forEach var="episode" items="${movieEpisodes }">
				<div class="episode">
					<div class="episode-number">
						<span class="value">${episode.episodeNumber }</span>
					</div>
					<div class="episode-info">
						<form class="episode-title active" action="#" method="post"
							onsubmit="updateEpisode(event)">
							<input type="text" value="${episode.title }" name="new-title" />
						</form>
						<div class="episode-views">${episode.views }lượtxem</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</main>

<!-- independent script -->
<script src="resources/js/update-series-page.js" defer></script>

<%@ include file="partical/footer.jsp"%>