<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>회원가입</h2>

	<form action="" method="post" class="form-group">
		<p>이름</p>
		<input type="text" name="name"><br>
		<p>주민번호</p>
		<input type="text" name="jumin"><br>
		<p>전화번호</p>
		<input type="text" name="tel"><br>
		<p>이메일</p>
		<input class="form-control" placeholder="이메일을 입력해주세요." name="email" id="email" type="email" autofocus>
		<div> 
			<input type="button" value="인증하기" id="emailAuth" class="btn btn-primary"><br>
		</div>
		 <input class="form-control" placeholder="인증 코드 6자리를 입력해주세요." maxlength="6" disabled="disabled" name="authCode" id="authCode" type="text" autofocus>
		<span id="emailAuthWarn"></span>
		<p>비밀번호</p>
		<input type="submit" value="회원가입" class="btn btn-lg btn-success btn-block" id="registerBtn" disabled="disabled">
	</form>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	//인증하기 버튼을 눌렀을 때 동작
	$("#emailAuth").click(function() {
		const email = $("#email").val(); //사용자가 입력한 이메일 값 얻어오기
		
		//Ajax로 전송
		$.ajax({
			url : '/emailAuth',
			data : {
				email : email
			},
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				console.log("result : " + result);
				$("#authCode").attr("disabled", false);
				code = result;
				alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
			}
		}); //End Ajax
	});
	
	//인증 코드 비교
	$("#authCode").on("focusout", function() {
		const inputCode = $("#authCode").val(); //인증번호 입력 칸에 작성한 내용 가져오기

		console.log("입력코드 : " + inputCode);
		console.log("인증코드 : " + code);

		if (Number(inputCode) === code) {
			$("#emailAuthWarn").html('인증번호가 일치합니다.');
			$("#emailAuthWarn").css('color', 'green');
			$('#emailAuth').attr('disabled', true);
			$('#email').attr('readonly', true);
			$("#registerBtn").attr("disabled", false);
		} else {
			$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
			$("#emailAuthWarn").css('color', 'red');
			$("#registerBtn").attr("disabled", true);
		}
	});
</script>


</html>