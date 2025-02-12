
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/css/addressMain.css?after" rel="stylesheet">

</head>
<body>
	<h1>주소록 메인 페이지</h1>
	<h2>${showAddress}</h2>
    <div class="addressMainContainer">
        <div class="searchBar">
	        <form action="" method="post">
	            <input type="text" placeholder="이름 또는 이메일로 검색" name="searchKeyword">
	            <button id="btnSearchUser">검색</button>
            </form>
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
                
                <!-- 내 주소록 -->
                <c:if test="${ showAddress == 1 }">
                <tbody id="myAddressList">
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
                        <td><button type="button" class="btnDeleteUser">삭제</button></td>
                    </tr>
                    
                    <c:forEach var="addressUser" items="${addressUserList}">
                        <tr 
                            class="listRow" 
                            userId="${addressUser.id}" 
                            userName="${addressUser.name}"
                            userTel="${addressUser.tel}"
                            userEmail="${addressUser.email}"
                            userDeptCode="${addressUser.deptCode}"
                            userPositionCode="${addressUser.positionCode}"
                        >
                        <td>${addressUser.name}</td>
                        <td>${addressUser.tel}</td>
                        <td>${addressUser.email}</td>
                        <td>${addressUser.deptCode}</td>
                        <td>${addressUser.positionCode}</td>
                        <td>
	                        <button 
	                        	type="button" class="btnDeleteUser"
	                        	onClick="removeUser(${addressUser.id})"
	                       	>삭제</button>
                       	</td>
                        <%@ include file="/WEB-INF/views/address/profileModal.jsp" %>
                    	</tr>
                    </c:forEach>
                </tbody>
                </c:if>

                <!-- 검색결과 주소록 -->
				<c:if test="${ showAddress == 2 }">
                <tbody id="searchUserList">
                    <c:forEach var="user" items="${userList}">
                        <tr 
                            class="listRow" 
                            userId="${user.id}", 
                            userName="${user.name}",
                            userTel="${user.tel}"
                            userEmail="${user.email}"
                            userDeptCode="${user.deptCode}"
                            userPositionCode="${user.positionCode}"
                        >
                        <td>${user.name}</td>
                        <td>${user.tel}</td>
                        <td>${user.email}</td>
                        <td>${user.deptCode}</td>
                        <td>${user.positionCode}</td>
                        <td><button type="button" class="btnAddUser">추가</button></td>
                        <%@ include file="/WEB-INF/views/address/profileModal.jsp" %>
                    	</tr>
                    </c:forEach>
                </tbody>
                </c:if>
                
            </table>
        </div>

	<script src="/js/addressMain.js"></script>
    <script>
        const listRows = document.getElementsByClassName("listRow");
        for (let row of listRows) {
            row.addEventListener('click', () => {
                // 모달 div Id에 값 세팅
                document.getElementById("modalName").innerText = row.getAttribute("userName");
                document.getElementById("modalTel").innerText = row.getAttribute("userTel");
                document.getElementById("modalEmail").innerText = row.getAttribute("userEmail");
                document.getElementById("modalDeptCode").innerText = row.getAttribute("userDeptCode");
                document.getElementById("modalPositionCode").innerText = row.getAttribute("userPositionCode");
                
                // 클릭 시 모달 열림
                document.querySelector(".modalBackground").style.display = "flex";
            });
        }

        const modalBackground = document.querySelector(".modalBackground");
        // 클릭 시 모달 닫힘
        modalBackground.addEventListener('click', (event) => {
            if (event.target === modalBackground) {
                modalBackground.style.display = "none";
            }
        });

        // 주소록 삭제
		function removeUser(userId) {
			if(confirm("정말 삭제하시겠습니까?")){
				location.href = '/address/removeUser?userId=' + userId;
			} 
		}

        // 주소록 추가
        const btnAddUser = document.querySelectorAll(".btnAddUser");
        function addUser(userId) {
            
        }

        // 검색
//         const btnSearchUser = document.getElementById("btnSearchUser");
//         const myAddressList = document.getElementById("myAddressList");
//         const searchUserList = document.getElementById("searchUserList");
//         btnSearchUser.addEventListener('click', ()=> {
//             myAddressList.style.display = "none";
//             searchUserList.style.display = "";
//         });

    </script>
</body>
</html>