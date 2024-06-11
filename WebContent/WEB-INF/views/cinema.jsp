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
            <section class="video-player-n-episodes-list">
                <!-- trình chơi video -->
                <section class="video-player-section">
                    <div class="video-player-box">
                        <video id="video-player" playsinline controls>
                            <source src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-576p.mp4"
                                type="video/mp4" />
                            <source src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-576p.mp4"
                                type="video/webm" />
                            <span>Trình duyệt của bạn không hỗ trợ loại video này</span>
                        </video>
                    </div>
                </section>

                <!-- danh sách các tập -->
                <section class="episodes-section">
                    <h2 class="section-title">DANH SÁCH TẬP</h2>
                    <div class="total-videos">Tổng số: 12 tập</div>
                    <div class="episodes-list">
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">1</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title active">Tập 1 - Ma cà rồng tự kỷ ra ngoài xã hội</div>
                                <div class="episode-views">27,160 lượt xem</div>
                            </div>
                        </a>
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">2</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title">Tập 2 - Bùng nổ hành động phản loạn</div>
                                <div class="episode-views">11,906 lượt xem</div>
                            </div>
                        </a>
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">3</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title">Tập 3 - Bóng tối của công chúa Ma cà rồng tự kỷ</div>
                                <div class="episode-views">10,046 lượt xem</div>
                            </div>
                        </a>
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">4</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title">Tập 4 - Có Hồng Tuất</div>
                                <div class="episode-views">12,257 lượt xem</div>
                            </div>
                        </a>
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">5</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title">Tập 5 - Sakuna Memoir và các Thất Hồng Thiên</div>
                                <div class="episode-views">10,880 lượt xem</div>
                            </div>
                        </a>
                        <a class="episode" href="#">
                            <div class="episode-number">
                                <span class="value">6</span>
                            </div>
                            <div class="episode-info">
                                <div class="episode-title">Tập 6 - Hội nghị bàn tròn và những kẻ ác ẩn</div>
                                <div class="episode-views">15,500 lượt xem</div>
                            </div>
                        </a>
                    </div>
                </section>
            </section>

            <!-- thông tin phim -->
            <section class="series-info-section">
                <div class="series-info">
                    <h1 class="series-title">HIKIKOMARI KYUUKETSUKI NO MONMON TẬP 1</h1>
                    <div class="views">179,581 lượt xem</div>
                    <div class="likes">
                        <button data-bs-toggle="tooltip" data-bs-placement="bottom" title="Thích phim">
                            <i class="bi bi-heart"></i>
                        </button>
                        <span data-bs-toggle="tooltip" data-bs-placement="bottom" title="Số lượt thích">491</span>
                    </div>
                    <div class="main-content">
                        <img class="series-poster"
                            src="https://s199.imacdn.com/vg/2023/10/07/4b533645aa0cb6de_5b3617ec43fe0931_44689169665128553.jpg"
                            alt="Hikikomari Kyuuketsuki no Monmon">
                        <div class="details">
                            <div class="tags">
                                <div class="tags-title">Thể loại:</div>
                                <div>
                                    <span>Hài Hước</span>
                                    <span>Giả Tưởng</span>
                                </div>
                            </div>
                            <p class="series-description">
                                Ba năm sống khép kín, ma cà rồng Terakomari Gandesblood (gọi tắt là Komari), tỉnh dậy
                                và nhận ra mình
                                đã được bổ nhiệm làm Chỉ huy trong Quân đội Hoàng gia Mulnite! Vấn đề là, đơn vị mới
                                của cô chỉ bao
                                gồm những kẻ lưu manh hiếu chiến, những người nổi dậy chống lại cấp trên của họ khi
                                có dấu hiệu yếu
                                kém nhất. Mặc dù Komari xuất thân từ dòng ma cà rồng mạnh mẽ nhưng việc từ chối uống
                                máu đã khiến cô
                                trở thành hình ảnh của một người tầm thường - gầy gò, thiếu phối hợp và không giỏi
                                phép thuật. Với
                                những khó khăn đangBa năm sống khép kín, ma cà rồng Terakomari Gandesblood (gọi tắt là
                                Komari), tỉnh dậy
                                và nhận ra mình
                                đã được bổ nhiệm làm Chỉ huy trong Quân đội Hoàng gia Mulnite! Vấn đề là, đơn vị mới
                                của cô chỉ bao
                                gồm những kẻ lưu manh hiếu chiến, những người nổi dậy chống lại cấp trên của họ khi
                                có dấu hiệu yếu
                                kém nhất. Mặc dù Komari xuất thân từ dòng ma cà rồng mạnh mẽ nhưng việc từ chối uống
                                máu đã khiến cô
                                trở thành hình ảnh của một người tầm thường - gầy gò, thiếu phối hợp và không giỏi
                                phép thuật. Với
                                những khó khăn đangBa năm sống khép kín, ma cà rồng Terakomari Gandesblood (gọi tắt là
                                Komari), tỉnh dậy
                                và nhận ra mình
                                đã được bổ nhiệm làm Chỉ huy trong Quân đội Hoàng gia Mulnite! Vấn đề là, đơn vị mới
                                của cô chỉ bao
                                gồm những kẻ lưu manh hiếu chiến, những người nổi dậy chống lại cấp trên của họ khi
                                có dấu hiệu yếu
                                kém nhất. Mặc dù Komari xuất thân từ dòng ma cà rồng mạnh mẽ nhưng việc từ chối uống
                                máu đã khiến cô
                                trở thành hình ảnh của một người tầm thường - gầy gò, thiếu phối hợp và không giỏi
                                phép thuật. Với
                                những khó khăn đang
                            </p>
                        </div>
                    </div>
                </div>
            </section>

            <!-- section bình luận -->
            <section class="comment-section">
                <!-- section viết bình luận của user -->
                <form action="#" method="post" id="make-comment-form" class="make-comment-form">
                    <div class="make-comment-title">
                        <i class="bi bi-chat-quote"></i>
                        <span class="title-text">Bình luận</span>
                    </div>
                    <div class="make-comment-input-wrapper">
                        <input type="text" placeholder="Viết bình luận của bạn tại đây..." name="make-comment" />
                        <button type="submit" class="send-icon-btn">
                            <i class="bi bi-send"></i>
                        </button>
                    </div>
                    <div class="invalid-message" hidden>Vui lòng nhập bình luận</div>
                </form>

                <!-- section danh sách các bình luận được viết -->
                <div class="comments-box">
                    <div class="comment">
                        <div class="avatar-wrapper">
                            <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                alt="Ảnh đại diện" class="avatar">
                        </div>
                        <div class="comment-n-replies">
                            <div class="comment-details">
                                <div class="comment-header-n-content">
                                    <div class="comment-header">
                                        <span class="username">Yahy</span>
                                    </div>
                                    <div class="comment-content">Khi cánh cửa này đóng lại thì tức là...</div>
                                </div>
                                <div class="comment-footer">
                                    <div class="reply-icon" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                        title="Phản hồi bình luận này" onclick="showReplyCommentForm(this)">
                                        <i class="bi bi-reply"></i>
                                        <span>25</span>
                                    </div>
                                    <div class="like-icon" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                        title="Thích bình luận này">
                                        <i class="bi bi-hand-thumbs-up"></i>
                                        <span>25</span>
                                    </div>
                                    <div class="comment-date">
                                        <span class="time-ago">3 tháng trước</span>
                                    </div>
                                </div>
                            </div>
                            <div class="replies-box">
                                <div class="comment reply">
                                    <div class="avatar-wrapper">
                                        <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                            alt="Ảnh đại diện" class="avatar">
                                    </div>
                                    <div class="comment-details">
                                        <div class="comment-header-n-content">
                                            <div class="comment-header">
                                                <span class="username">Lôi Chí Chang</span>
                                            </div>
                                            <div class="comment-content">Là vãn tuồng, ai về nhà nấy</div>
                                        </div>
                                        <div class="comment-footer">
                                            <div class="comment-date">
                                                <span class="time-ago">3 tháng trước</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="comment reply">
                                    <div class="avatar-wrapper">
                                        <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                            alt="Ảnh đại diện" class="avatar">
                                    </div>
                                    <div class="comment-details">
                                        <div class="comment-header-n-content">
                                            <div class="comment-header">
                                                <span class="username">Bi</span>
                                            </div>
                                            <div class="comment-content">Tôi cũng vậy</div>
                                        </div>
                                        <div class="comment-footer">
                                            <div class="comment-date">
                                                <span class="time-ago">3 tháng trước</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <form action="#" method="post" data-reply-comment-id="user-2"
                                class="make-comment-form reply" hidden onsubmit="replyComment(event)">
                                <div class="make-comment-input-wrapper">
                                    <input type="text" placeholder="Phản hồi bình luận của Yahy..."
                                        name="make-comment" />
                                    <button type="submit" class="send-icon-btn">
                                        <i class="bi bi-send"></i>
                                    </button>
                                </div>
                                <div class="invalid-message" hidden>Vui lòng nhập bình luận</div>
                            </form>
                        </div>
                    </div>
                    <div class="comment">
                        <div class="avatar-wrapper">
                            <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                alt="Ảnh đại diện" class="avatar">
                        </div>
                        <div class="comment-n-replies">
                            <div class="comment-details">
                                <div class="comment-header-n-content">
                                    <div class="comment-header">
                                        <span class="username">Yahy</span>
                                    </div>
                                    <div class="comment-content">Khi cánh cửa này đóng lại thì tức là...</div>
                                </div>
                                <div class="comment-footer">
                                    <div class="reply-icon" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                        title="Phản hồi bình luận này" onclick="showReplyCommentForm(this)">
                                        <i class="bi bi-reply"></i>
                                        <span>25</span>
                                    </div>
                                    <div class="like-icon" data-bs-toggle="tooltip" data-bs-placement="bottom"
                                        title="Thích bình luận này">
                                        <i class="bi bi-hand-thumbs-up"></i>
                                        <span>25</span>
                                    </div>
                                    <div class="comment-date">
                                        <span class="time-ago">3 tháng trước</span>
                                    </div>
                                </div>
                            </div>
                            <div class="replies-box">
                                <div class="comment reply">
                                    <div class="avatar-wrapper">
                                        <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                            alt="Ảnh đại diện" class="avatar">
                                    </div>
                                    <div class="comment-details">
                                        <div class="comment-header-n-content">
                                            <div class="comment-header">
                                                <span class="username">Lôi Chí Chang</span>
                                            </div>
                                            <div class="comment-content">Là vãn tuồng, ai về nhà nấy</div>
                                        </div>
                                        <div class="comment-footer">
                                            <div class="comment-date">
                                                <span class="time-ago">3 tháng trước</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="comment reply">
                                    <div class="avatar-wrapper">
                                        <img src="https://www.cartoonize.net/wp-content/uploads/2024/05/avatar-maker-photo-to-cartoon.png"
                                            alt="Ảnh đại diện" class="avatar">
                                    </div>
                                    <div class="comment-details">
                                        <div class="comment-header-n-content">
                                            <div class="comment-header">
                                                <span class="username">Bi</span>
                                            </div>
                                            <div class="comment-content">Tôi cũng vậy</div>
                                        </div>
                                        <div class="comment-footer">
                                            <div class="comment-date">
                                                <span class="time-ago">3 tháng trước</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <form action="#" method="post" data-reply-comment-id="user-2"
                                class="make-comment-form reply" hidden onsubmit="replyComment(event)">
                                <div class="make-comment-input-wrapper">
                                    <input type="text" placeholder="Phản hồi bình luận của Yahy..."
                                        name="make-comment" />
                                    <button type="submit" class="send-icon-btn">
                                        <i class="bi bi-send"></i>
                                    </button>
                                </div>
                                <div class="invalid-message" hidden>Vui lòng nhập bình luận</div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="pagination-box">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Trước</a></li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Sau</a></li>
                        </ul>
                    </nav>
                </div>
            </section>
        </div>
    </main>
	    <!-- video player - plyr -->
    <script src="https://cdn.plyr.io/3.6.12/plyr.polyfilled.js" defer></script>
    <script src="resources/js/cinema-page.js" defer></script>
<%@ include file="partical/footer.jsp" %>