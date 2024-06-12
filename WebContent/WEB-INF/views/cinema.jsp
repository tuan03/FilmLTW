<%@page import="ptithcm.utils.FormatTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="partical/head.jsp" %>
    <link rel="stylesheet" href="resources/css/cinema-page.css">
        <!-- video player - plyr -->
    <link rel="stylesheet" href="https://cdn.plyr.io/3.7.8/plyr.css" />
    <title>AnimeVS - Anime Vietsub</title>

<%@ include file="partical/header.jsp" %>

    <main id="page-main">
        <div class="top-container">
            <section class="video-player-n-episodes-list" style='${selectedEpisode == null ? "display: none;": ""}'>
                <!-- trình chơi video -->
                <section class="video-player-section">
                    <div class="video-player-box">
                        <video id="video-player" playsinline controls>
                            <source src="${selectedEpisode.videoUrl }"
                                type="video/mp4" />
                            <source src="${selectedEpisode.videoUrl }"
                                type="video/webm" />
                            <span>Trình duyệt của bạn không hỗ trợ loại video này</span>
                        </video>
                    </div>
                </section>

                <!-- danh sách các tập -->
                
                <section class="episodes-section">
                    <h2 class="section-title">DANH SÁCH TẬP</h2>
                    <div class="total-videos">Tổng số: ${movie.episodes.size()} tập</div>
                    <div class="episodes-list">
                    	<c:forEach var="episode" items="${movie.episodes}" varStatus="loop">
    <c:choose>
        <c:when test="${episode.episodeNumber eq selectedEpisode.episodeNumber}">
            <a class="episode red" href="cinema.htm?id=${movie.id}&ep=${episode.episodeNumber}">
                <div class="episode-number">
                    <span class="value">${loop.index + 1}</span>
                </div>
                <div class="episode-info">
                    <div class="episode-title active">Tập ${loop.index + 1} - ${episode.title}</div>
                    <div class="episode-views">${episode.getViews()} lượt xem</div>
                </div>
            </a>
        </c:when>
        <c:otherwise>
            <a class="episode" href="cinema.htm?id=${movie.id}&ep=${episode.episodeNumber}">
                <div class="episode-number">
                    <span class="value">${loop.index + 1}</span>
                </div>
                <div class="episode-info">
                    <div class="episode-title">Tập ${loop.index + 1} - ${episode.title}</div>
                    <div class="episode-views">${episode.getViews()} lượt xem</div>
                </div>
            </a>
        </c:otherwise>
    </c:choose>
</c:forEach>


                    </div>
                </section>
            </section>

            <!-- thông tin phim -->
            <section class="series-info-section">
                <div class="series-info">
                    <h1 class="series-title">${movie.title }</h1>
                    <div class="views">${totalViews } lượt xem</div>
                    <div class="likes">
                        <button data-bs-toggle="tooltip" data-bs-placement="bottom" title="Thích phim">
                            <i class="bi bi-heart"></i>
                        </button>
                        <span data-bs-toggle="tooltip" data-bs-placement="bottom" title="Số lượt thích">491</span>
                    </div>
                    <div class="main-content">
                        <img class="series-poster"
                            src="${movie.posterUrl }"
                            alt="Hikikomari Kyuuketsuki no Monmon">
                        <div class="details">
                            <div class="tags">
                                <div class="tags-title">Thể loại:</div>
                                	<c:forEach var="genre" items="${movie['genres']}">
									    <span>${genre.name}</span>
									</c:forEach>
                                <div>
                                	
                                	
                                </div>
                            </div>
                            <p class="series-description">
                                ${movie.description }
                            </p>
                        </div>
                    </div>
                </div>
            </section>





            <!-- section bình luận -->
            <section class="comment-section">
                <!-- section viết bình luận của user -->
                <form:form action="cinema.htm?id=${movie.id }&ep=${selectedEpisode.episodeNumber}" method="post" id="make-comment-form" class="make-comment-form">
                    <div class="make-comment-title">
                        <i class="bi bi-chat-quote"></i>
                        <span class="title-text">Bình luận</span>
                    </div>
                    <div class="make-comment-input-wrapper">
                        <input type="text" placeholder="Viết bình luận của bạn tại đây..." name="content" />
                        <button type="submit" class="send-icon-btn">
                            <i class="bi bi-send"></i>
                        </button>
                    </div>
                    <div class="invalid-message" hidden>Vui lòng nhập bình luận</div>
                </form:form>

                <!-- section danh sách các bình luận được viết -->
                <div class="comments-box">
                <c:forEach var="cmt" items="${comment}">
                    <div class="comment">
                    <div class="avatar-wrapper">
                            <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                alt="Ảnh đại diện" class="avatar">
                        </div>
                        <div class="comment-n-replies">
                        	
                            <div class="comment-details">
                                <div class="comment-header-n-content">
                                    <div class="comment-header">
                                        <span class="username">${cmt.user.fullname }</span>
                                    </div>
                                    <div class="comment-content">${cmt.content }</div>
                                </div>
                                <div class="comment-footer">
                                    <div class="reply-icon" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                        title="Phản hồi bình luận này" onclick="showReplyCommentForm(this)">
                                        <i class="bi bi-reply"></i>
                                        <span>${cmt.replies.size() }</span>
                                    </div>

                                    <div class="comment-date">
                                        <span class="time-ago">${FormatTime.formatTimestamp(cmt.createdAt) }</span>
                                    </div>
                                </div>
                            </div>
                            <div class="replies-box">
                            <c:forEach var="replie" items="${cmt.replies}">
                                <div class="comment reply">
                                    <div class="avatar-wrapper">
                                        <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                            alt="Ảnh đại diện" class="avatar">
                                    </div>
                                    <div class="comment-details">
                                        <div class="comment-header-n-content">
                                            <div class="comment-header">
                                                <span class="username">${replie.user.fullname }</span>
                                            </div>
                                            <div class="comment-content">${replie.content }</div>
                                        </div>
                                        <div class="comment-footer">
                                            <div class="comment-date">
                                                <span class="time-ago">${FormatTime.formatTimestamp(replie.createdAt) }</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </div>
                            <form action="reply.htm?id=${movie.id }&ep=${selectedEpisode.episodeNumber}&idC=${cmt.id}"  method="post" data-reply-comment-id="user-2" class="make-comment-form reply" hidden onsubmit="replyComment(event)">
                                <div class="make-comment-input-wrapper">
                                    <input type="text" placeholder="Phản hồi bình luận của Yahy..."
                                        name="content" />
                                    <button type="submit" class="send-icon-btn">
                                        <i class="bi bi-send"></i>
                                    </button>
                                </div>
                                <div class="invalid-message" hidden>Vui lòng nhập bình luận</div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </section>
        </div>
    </main>
	    <!-- video player - plyr -->
    <script src="https://cdn.plyr.io/3.6.12/plyr.polyfilled.js" defer></script>
    <script src="resources/js/cinema-page.js" defer></script>
<%@ include file="partical/footer.jsp" %>