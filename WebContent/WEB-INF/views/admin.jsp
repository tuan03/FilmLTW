<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp"%>
<!-- independent style -->
<link rel="stylesheet" href="resources/css/admin-page.css">
<title>AnimeVS - Anime Vietsub</title>
<%@ include file="partical/header.jsp"%>

<main id="page-main">
	<div class="container">
		<div class="section">
			<div class="section-title-box">
				<h2 class="section-title">Các thể loại hiện có</h2>
				<button class="add-genre" id="add-genre-btn">
					<i class="bi bi-plus-circle"></i> <span>Thêm thể loại</span>
				</button>
			</div>
			<div class="genres">
				<c:forEach var="genre" items="${genres }">
					<form action="genre/edit.htm?id=${genre.id }" method="post">
						<div class="genre">
							<input class="editor" name="content"
								placeholder="Nhập thể loại..." type="text"
								value="${genre.name }" />
							<div class="actions">
								<button type="submit" class="save-action"
									style="background-color: transparent;">
									<i class="bi bi-check-circle"></i>
								</button>
								<a href="genre/del.htm?id=${genre.id }" class="delete-action">
									<i class="bi bi-trash3"></i>
								</a>
							</div>
						</div>
					</form>
				</c:forEach>


			</div>
		</div>
		<div class="section">
			<div class="section-title-box">
				<h2 class="section-title">Tìm kiếm phim</h2>
			</div>
			<div class="search-series">
				<div class="form-group">
					<div class="input-wrapper">
						<div class="search-icon">
							<i class="bi bi-search"></i>
						</div>
						<input id="search-series-input"
							placeholder="Tìm phim theo tên phim..." name="series-name"
							autocomplete="on">
					</div>
				</div>
				<div class="search-result">
					<div class="add-series-btn-box">
						<span></span> <a class="add-series-btn" href="#"> <i
							class="bi bi-plus-circle"></i> <span>Thêm phim</span>
						</a>
					</div>
					<table id="search-series-result-table">
						<tr>
							<th>Tên phim</th>
							<th>Số tập</th>
							<th>Ngày công chiếu</th>
							<th></th>
						</tr>

						<c:forEach var="movie" items="${movies }">
							<tr>
								<td class="series-title">${movie.title }</td>
								<td>${movie.description }</td>
								<td>${movie.releaseDate }</td>
								<td>
									<div class="actions">
										<button class="delete-btn" data-bs-toggle="tooltip"
											data-bs-placement="bottom" title="Xóa phim">
											<i class="bi bi-trash3"></i>
										</button>
										<a class="edit-btn" data-bs-toggle="tooltip"
											data-bs-placement="bottom" title="Chỉnh sửa phim"
											href="admin/update-series.htm?id=${movie.id }"> <i
											class="bi bi-pencil-square"></i>
										</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</main>

<!-- independent script -->
<script src="resources/js/admin-page.js" defer></script>

<%@ include file="partical/footer.jsp"%>