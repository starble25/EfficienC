<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<link href="/css/notice.css?after" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" />

</head>
<body>
	<!-- 공통 사이드바 include -->
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

    <div class="noticeContainer">
        <div id="noticeHeader">
            <h2>공지사항</h2>
            <button id="btnInputNotice">공지작성</button>
        </div>
        <div class="itemContainer">
            <table>
                <tr style="background-color: #9ae49d;">
                    <th class="colTitle">제목</th>
                    <th class="colAuthor">작성자</th>
                    <th class="colDate">작성일</th>
                    <th class="colBtn">삭제</th>
                </tr>
                
                <c:forEach var="notice" items="${noticeList}">
                    <tr class="showContent">
                        <td class="colTitle">${notice.title}</td>
                        <td class="colAuthor">${notice.author}</td>
                        <td class="colDate">${notice.regDate}</td>
                        <td class="colBtn">
                        	<button type="button" class="btnDeleteNotice" value="${notice.id}">삭제</button>
                        </td>
                    </tr>
                    <tr class="rowContent" style="display:none;">
                        <td colspan="4" style="padding: 0 0 0 15px; line-height: 5px;">${notice.content}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    
    <!-- 공지사항 작성 Modal -->
    <%@ include file="/WEB-INF/views/notice/noticeModal.jsp" %>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    	//공지내용 토글
        $(".showContent").click(function(){
            $(this).next(".rowContent").toggle();
            $(this).next(".rowContent").toggleClass("rowAnime");
        });
        
    	//공지삭제
        $(".btnDeleteNotice").click(function(event){
            event.preventDefault();
            const noticeId = $(this).val();

            if (confirm("해당 공지를 삭제하시겠습니까?")) {
                $.ajax({
                    url: "/notice/delete/" + noticeId,
                    type: "DELETE",
                    success: function(result) {
                        location.reload(); // 삭제 후 페이지 새로고침
                    },
                    error: function(error) {
                        alert("공지 삭제에 실패했습니다.");
                    }
                });
            }
        });
        
        // 공지작성
        // 모달 열기
        $("#btnInputNotice").click(function() {
            $(".noticeModal").css("display", "flex");
        });

        // 모달 닫기
        $(".closeModal").click(function() {
            $(".noticeModal").css("display", "none");
        });

        // 모달 영역 밖 클릭 시 닫기
        $(window).click(function(event) {
            if ($(event.target).is(".noticeModal")) {
                $(".noticeModal").css("display", "none");
            }
        });
    </script>
</body>
</html>