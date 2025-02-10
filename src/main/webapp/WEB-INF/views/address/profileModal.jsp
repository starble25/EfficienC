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
<title>Insert title here</title>

<link href="/css/profileModal.css?after" rel="stylesheet">

</head>
<body>
    <div class="modalBackground" style="display: none;">
        <div class="profileModalContainer">
            <div class="modalHeader">
                <button>X</button>
            </div>
            <div class="modalBody">
                <div>
                    <img src="/image/profileImage.jpg">
                </div>
                <div>
                    <!-- <div id="modalName">${name}</div>
                    <div id="modalTel">${tel}</div>
                    <div id="modalEmail">${email}</div>
                    <div id="modalDeptCode">${deptCode}</div>
                    <div id="modalPositionCode">${positionCode}</div> -->
                    
                    <div id="modalName"></div>
                    <div id="modalTel"></div>
                    <div id="modalEmail"></div>
                    <div id="modalDeptCode"></div>
                    <div id="modalPositionCode"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>