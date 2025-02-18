<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar">
		<div class="logo">LOGO</div>
		<ul class="menu">
			<!-- requestScope.menuList 로부터 메뉴 생성 -->
			<c:forEach var="menuItem" items="${requestScope.menuList}">
				<!-- EL 삼항연산자 (EL 2.2 이상) -->
				<li class="${menuItem.active ? 'active' : ''}"><a
					href="<c:url value='${menuItem.link}'/>"> <c:out
							value="${menuItem.name}" />
				</a></li>
			</c:forEach>
		</ul>
	</div>
	
	<!-- 추가된 JavaScript: 현재 URL에 따라 active 클래스 부여 -->
	<script>
		document.addEventListener("DOMContentLoaded", function() {
		    var currentPath = window.location.pathname;
		    var menuLinks = document.querySelectorAll('.menu li a');
		    menuLinks.forEach(function(link) {
		        var href = link.getAttribute('href');
		        if (currentPath.indexOf(href) !== -1) {
		            link.parentElement.classList.add('active');
		        }
		    });
		});
	</script>
</body>
</html>
