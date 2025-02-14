<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작업 목록</title>
<style>
.board {
	display: flex;
}

.column {
	width: 30%;
	padding: 10px;
	border: 1px solid #ccc;
}

.task {
	padding: 10px;
	margin: 5px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>작업 목록</h1>
	<form action="tasks/add" method="post">
		<input type="text" name="title" placeholder="새 작업 입력" required>
		<button type="submit">추가</button>
	</form>

	<div class="board">
		<div class="column">
			<h3>할 일</h3>
			<c:forEach var="task" items="${tasks}">
				<c:if test="${task.status == 'TODO'}">
					<div class="task">${task.title}
						<form action="tasks/updateStatus" method="post">
							<input type="hidden" name="id" value="${task.id}"> <input
								type="hidden" name="status" value="IN_PROGRESS">
							<button type="submit">진행 중으로</button>
						</form>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<div class="column">
			<h3>진행 중</h3>
			<c:forEach var="task" items="${tasks}">
				<c:if test="${task.status == 'IN_PROGRESS'}">
					<div class="task">${task.title}
						<form action="tasks/updateStatus" method="post">
							<input type="hidden" name="id" value="${task.id}"> <input
								type="hidden" name="status" value="DONE">
							<button type="submit">완료</button>
						</form>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>