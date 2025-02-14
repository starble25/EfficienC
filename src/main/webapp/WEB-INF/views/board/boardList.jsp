<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--     2줄 임포트 추가 -->
    <%@ page import="java.util.List" %>
<%@ page import="com.app.dto.board.Board" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>
    body { font-family: Arial, sans-serif; width: 600px; margin: auto; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    th, td { border: 1px solid black; padding: 8px; text-align: center; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; color: blue; }
    .button { display: block; margin: 10px 0; padding: 5px; background: green; color: white; text-align: center; width: 100px; border-radius: 5px; }
</style>
</head>
<body>
<h2>게시글 목록</h2>

<a href="boardForm" class="button">새 글 작성</a> <!-- 추가: 새 글 작성 버튼 -->

<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성일</th>
    </tr>
    <%
        List<Board> boardList = (List<Board>) request.getAttribute("boardList");
        if (boardList != null && !boardList.isEmpty()) { // 수정: 목록이 비어 있을 경우 대비
            for (Board board : boardList) {
    %>
    <tr>
        <td><%= board.getId() %></td>
        <td><a href="boardDetail?id=<%= board.getId() %>"><%= board.getTitle() %></a></td> <!-- 추가: 제목 클릭 시 상세 페이지 이동 -->
        <td><%= board.getCreatedAt() %></td>
    </tr>
    <% 
            }
        } else { 
    %>
    <tr>
        <td colspan="3">게시글이 없습니다.</td>
    </tr>
    <% } %>
</table>
</body>
</html>