<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <footer class="footer bg-dark text-center text-white py-3">
        <p>© Bản quyền thuộc về 2024 AnimeVS.TV tất cả các quyền đã được bảo lưu.</p>
    </footer>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- additional bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"
        defer></script>
    <!-- bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"
        defer></script>
    <!-- moment.js -->
    <script src="https://cdn.jsdelivr.net/npm/moment@2.30.1/moment.min.js" defer></script>
    <!-- toast - toastr -->
    <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/toastr.min.js" defer></script>
    <!-- datepicker - flatpickr -->
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <!-- general scripts -->
    <script src="<c:url value='/resources/js/utils.js'/>" defer></script>
     <script>
    document.addEventListener("DOMContentLoaded", function() {
    	<c:if test="${not empty errorMessage}">
        toastr.error("${errorMessage}");
        </c:if>
    })
    </script>
    
</body>

</html>