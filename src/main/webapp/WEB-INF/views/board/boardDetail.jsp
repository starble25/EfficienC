<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--     2줄 임포트 추가 -->
<%@ page import="java.util.List"%>
<%@ page import="com.app.dto.board.Board"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Board board = (Board) request.getAttribute("board");
	if (board == null) {
	%>
	<p>게시글이 존재하지 않습니다.</p>
	<%
	return;
	}
	%>
	<h2>게시글 수정</h2>

	<form action="${pageContext.request.contextPath}/board/submit"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="<%=board.getId()%>"> <label>제목</label>
		<input type="text" name="title" value="<%=board.getTitle()%>"
			required> <label>내용</label>
		<textarea name="content" rows="5" required><%=board.getContent()%></textarea>

		<label>첨부파일</label> <input type="file" name="uploadFile">

		<button type="submit">등록</button>
	</form>
</body>
</html>