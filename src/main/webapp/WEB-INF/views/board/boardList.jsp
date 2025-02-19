<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--     2줄 임포트 추가 -->
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.app.dto.board.Board"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
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
} */

table {
	width: 600px;
	border-collapse: collapse;
	margin-top: 10px;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

a {
	text-decoration: none;
	color: blue;
}

.button {
	display: block;
	margin: 10px 0;
	padding: 5px;
	background: green;
	color: white;
	text-align: center;
	width: 100px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
	<div class="boardContainer">
		<h2>게시글 목록</h2>

		<a href="form" class="button">새 글 작성</a>
		<!-- 추가: 새 글 작성 버튼 -->

		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>삭제</th>
			</tr>
			<%
			List<Board> boardList = (List<Board>) request.getAttribute("boardList");
			if (boardList != null && !boardList.isEmpty()) { // 수정: 목록이 비어 있을 경우 대비
				for (Board board : boardList) {
			%>
			<tr>
				<td><%=board.getId()%></td>
				<td><a href="list"><%=board.getTitle()%></a></td>
				<%--         <td><a href="boardDetail?id=<%= board.getId() %>"><%= board.getTitle() %></a></td> <!-- 추가: 제목 클릭 시 상세 페이지 이동 --> --%>
				<td><%=board.getCreatedAt()%></td>
				<td>
					<form action="${pageContext.request.contextPath}/board/delete"
						method="post" style="display: inline;">
						<input type="hidden" name="id" value="<%=board.getId()%>">
						<button type="submit" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
					</form>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="3">게시글이 없습니다.</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>