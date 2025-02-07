<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/css/addressMain.css" rel="stylesheet">
<!-- <link href="../../../resources/css/addressMain.css" rel="stylesheet"> -->

</head>
<body>
	<h1>주소록 메인 페이지</h1>
    <div class="addressMainContainer">
        <div class="searchBar">
            <input type="text" placeholder="이름 또는 연락처로 검색">
            <button>검색</button>
        </div>
        <div class="addressList">
            <table>
                <thead>
                    <tr class="">
                        <th>이름</th>
                        <th>연락처</th>
                        <th>이메일</th>
                        <th>부서</th>
                        <th>직급</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 여기에 주소 데이터가 추가될 예정입니다 -->
                    <tr class="listRow">
                        <td>홍길동</td>
                        <td>010-1234-5678</td>
                        <td>hong@example.com</td>
                        <td>개발팀</td>
                        <td>사장</td>
                        <td><button type="button" class="btnDeleteRow">삭제</button></td>
                    </tr>
                    <tr class="listRow">
                        <td>홍길동2</td>
                        <td>010-1234-5678</td>
                        <td>qqqq@example.com</td>
                        <td>우리팀</td>
                        <td>팀원</td>
                        <td><button type="button" class="btnDeleteRow">삭제</button></td>
                    </tr>
                </tbody>
            </table>
        </div>

	<script src="/js/addressMain.js"></script>
<!-- 	<script src="../../../resources/js/addressMain.js"></script> -->
</body>
</html>