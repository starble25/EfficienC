<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:set var="id" value="${param.id}" /> --%>
<%-- <c:set var="name" value="${param.name}" /> --%>
<%-- <c:set var="tel" value="${param.tel}" /> --%>
<%-- <c:set var="email" value="${param.email}" /> --%>
<%-- <c:set var="deptCode" value="${param.deptCode}" /> --%>
<%-- <c:set var="positionCode" value="${param.positionCode}" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 상세보기</title>

<link href="/css/profileModal.css?after" rel="stylesheet">

</head>
<body>
    <div class="modalBackground" style="display: none;">
        <div class="profileModalContainer">
            <!-- 아래 div들 'id' 바꾸면 안됨 -->
            <div class="modalHeader">
                <div id="modalName">이름</div>
                <button>X</button>
            </div>
            <div class="modalBody">
                <div>
                    <img src="/image/profileImage.jpg">
                </div>
                <div class="itemContainer">
                    <div class="item">
                        <div class="itemName">연락처</div>
                        <div id="modalTel">연락처</div>
                    </div>
                    <div class="item">
                        <div class="itemName">이메일</div>
                        <div id="modalEmail">이메일</div>
                    </div>
                    <div class="item">
                        <div class="itemName">부서</div>
                        <div id="modalDeptCode">부서</div>
                    </div>
                    <div class="item">
                        <div class="itemName">직급</div>
                        <div id="modalPositionCode">직급</div>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</body>
</html>