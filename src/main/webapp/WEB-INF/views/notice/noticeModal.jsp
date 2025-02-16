<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="noticeModal">
	    <div class="modal-content">
	        <span class="closeModal" class="close">&times;</span>
	        <h3>공지 작성</h3>
	        <form class="noticeForm">
	            <label for="title">제목</label>
	            <input type="text" id="title" name="title" required>
	            
	            <label for="content">내용</label>
	            <textarea id="content" name="content" required></textarea>
	            
	            <button type="submit">등록</button>
	        </form>
	    </div>
	</div>
</body>
</html>