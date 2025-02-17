<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="noticeModal">
	    <div class="modalContent">
	        <span class="closeModal">&times;</span>
	        <h3>공지 작성</h3>
	        <form class="noticeForm">
	        	<div class="noticeTitle">
		            <label for="title">제목</label>
		            <input type="text" id="inputTitle" name="title" value="${notice.title}">	        	
	        	</div>
	            <div class="noticeContent">
		            <label for="content">내용</label>
		            <textarea id="inputContent" name="content">${notice.content}</textarea>	            
	            </div>
	            <button id="btnSubmitNotice" type="submit">등록</button>
	        </form>
	    </div>
	</div>
	
	<script>
		const btnSubmitNotice = document.getElementById("btnSubmitNotice");
		// 공지추가 요청
		btnSubmitNotice.addEventListener('click', ()=>{
			event.preventDefault();
			
			let inputTitle = document.getElementById("inputTitle").value;
			let inputContent = document.getElementById("inputContent").value;
			
			console.log(inputTitle);
			console.log(inputContent);
			
			
			let objData = {
				'title':inputTitle,
				'content':inputContent
			};
			
			let jsonData = JSON.stringify(objData);
			
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/notice",
				headers:{
					"Content-type":"application/json;"
				},
				data: jsonData,
                success: function(result) {
                    console.log("공지 추가 성공");
                    location.reload();
                },
                error: function(error) {
                    alert("공지 추가에 실패했습니다.");
                }
			});
		});
	</script>
</body>
</html>