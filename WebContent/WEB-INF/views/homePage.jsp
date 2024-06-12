<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp" %>
    <!-- independent style -->
    <link rel="stylesheet" href="resources/css/home-page.css">
    <title>AnimeVS - Anime Vietsub</title>

<%@ include file="partical/header.jsp" %>
    <main id="page-main">
        <section class="series-list-section">
            <!-- all series //:MARK -->
            <div class="series-list-box all-series">
                <div class="section-title">
                    <div class="section-title-strong">
                        Trang ${currentPage }
                    </div>
                </div>

                <div class="series-list">
                    <c:forEach var="movie" items="${newMovie}">
           
                    <a class="card" href="cinema.htm?id=${movie.movieId}">
                        <div class="series-avatar-wrapper">
                            <img src="${movie.poster_url}"
                                alt="Character">
                            <div data-toggle="tooltip" data-placement="bottom" title="Lượt xem: ${movie.totalViews}"
                                class="views-count">${movie.totalViews}</div>
                        </div>
                        <div class="card-info">
                            <div class="card-title" data-toggle="tooltip" data-placement="bottom"
                                title="${movie.title}">
                                ${movie.title}
                            </div>
                        </div>
                    </a>

        </c:forEach>
                </div>

                <nav class="pagination-box">
                
                    <ul class="pagination">
    <c:if test="${currentPage > 1}">
        <li class="page-item">
            <a class="page-link" href="page.htm?page=${currentPage - 1}" aria-label="Previous">
                <span aria-hidden="true">Trước</span>
            </a>
        </li>
    </c:if>
    <c:forEach var="j" begin="1" end="${numPage}">
        <li class="page-item">
            <a class="page-link ${currentPage == j ? 'active' : ''}" href="page.htm?page=${j}">${j}</a>
        </li>
    </c:forEach>
    <c:if test="${currentPage < numPage}">
        <li class="page-item">
            <a class="page-link" href="page.htm?page=${currentPage + 1}">Tiếp</a>
        </li>
    </c:if>
</ul>

                </nav>
            </div>
        </section>
    </main>   
    <!-- independent script -->
    <script src="resources/js/home-page.js" defer></script>

<%@ include file="partical/footer.jsp" %>