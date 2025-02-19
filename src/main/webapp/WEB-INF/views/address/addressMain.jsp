
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/css/addressMain.css?after" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" />

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
	<h1>주소록 메인 페이지</h1>
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
                            userName="${user.name}"
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
                        
                        <!-- 주소록 추가시 버튼 비활성화 -->
						<c:forEach var="address" items="${addressUserList}">
                            <c:if test="${user.id == address.id}">
                                <script>
                                    document.addEventListener("DOMContentLoaded", function() {
                                        const button = document.querySelector(`button[data-user-id="${user.id}"]`);
                                        if (button) {
                                            button.disabled = true;
                                            button.innerText = "추가됨";
                                            //button.style.backgroundColor = "red";
                                        }
                                    });
                                </script>
                            </c:if>
                        </c:forEach>
                        <td><button 
                                type="button" class="btnAddUser" 
                                data-user-id="${user.id}" 
                            >추가</button></td>
                        <%@ include file="/WEB-INF/views/address/profileModal.jsp" %>
                    	</tr>
                    </c:forEach>
                </tbody>
                </c:if>
                
            </table>
        </div>
    </div>

	<script src="/js/addressMain.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
        const listRows = document.getElementsByClassName("listRow");
        for (let row of listRows) {
            row.addEventListener('click', () => {
            	// 삭제 버튼이 아닌 경우에만 모달 열기
                if (!event.target.classList.contains('btnDeleteUser') && 
                    !event.target.classList.contains('btnAddUser')) {
                    
                    // 모달 div Id에 값 세팅
                    document.getElementById("modalName").innerText = row.getAttribute("userName");
                    document.getElementById("modalTel").innerText = row.getAttribute("userTel");
                    document.getElementById("modalEmail").innerText = row.getAttribute("userEmail");
                    document.getElementById("modalDeptCode").innerText = row.getAttribute("userDeptCode");
                    document.getElementById("modalPositionCode").innerText = row.getAttribute("userPositionCode");

                    // 클릭 시 모달 열림
                    document.querySelector(".modalBackground").style.display = "flex";
                }
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
        btnAddUser.forEach( button => {
            button.addEventListener('click', (event) => {
                const userId = event.target.getAttribute("data-user-id");
                console.log("userId : " + userId);
                
                $.ajax({
    				type: "POST",
    				url: "http://localhost:8080/address/addUser",
    				headers:{
    					"Content-type":"application/json;"
    				},
    				data: userId,
    				success: function(result){
    					console.log("ajax success");
    					
    					// 주소록 추가 성공시 버튼 비활성화
    					button.disabled = true;
    					button.innerText = "추가됨";
//     					button.style.backgroundColor = "red"
    					
    				},
    				error: function(error){
    					console.log("addressMain.jsp btnAddUser ajax error")
    					console.log(error);
    				}
    			});
            });
        })

    </script>
</body>
</html>