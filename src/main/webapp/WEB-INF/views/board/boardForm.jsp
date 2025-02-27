<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
<style>

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
.boardContainer {
	float:left;
}

/* body {
	font-family: Arial, sans-serif;
	width: 600px;
	margin: auto;
}
 */
label {
	display: block;
	margin-top: 10px;
}

input, textarea {
	width: 100%;
	padding: 5px;
	margin-top: 5px;
}

button {
	margin-top: 10px;
	padding: 5px 10px;
	background-color: green;
	color: white;
	border: none;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
	<div class="boardContainer">
		<h2>게시글 작성</h2>
		<!--     <form action="submitBoard" method="post" enctype="multipart/form-data"> -->
		<form action="${pageContext.request.contextPath}/board/submit"
			method="post" enctype="multipart/form-data">

			<label>제목</label> <input type="text" name="title" required> <label>내용</label>
			<textarea name="content" rows="5" required></textarea>

			<label>첨부파일</label> <input type="file" name="uploadFile">

			<button type="submit">등록</button>
		</form>

		<!--     목록 페이지로 이동 -->
		<a href="list">목록으로</a>
	</div>
</body>
</html>