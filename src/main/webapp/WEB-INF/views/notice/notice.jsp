<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<link href="/css/notice.css?after" rel="stylesheet">

</head>
<body>
    <div class="noticeContainer">
        <div id="noticeHeader">
            <h2>공지사항</h2>
            <button id="btnInputNotice">공지작성</button>
        </div>
        <div class="itemContainer">
            <table>
                <tr>
                    <th class="colTitle">제목</th>
                    <th class="colAuthor">작성자</th>
                    <th class="colDate">작성일</th>
                    <th class="colBtn">수정/삭제</th>
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
                        <td colspan="4">${notice.content}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(".showContent").click(function(){
            $(this).next(".rowContent").toggle();
        });
        
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
                    error: function(xhr, status, error) {
                        alert("공지 삭제에 실패했습니다.");
                    }
                });
            }
        });
    </script>
</body>
</html>