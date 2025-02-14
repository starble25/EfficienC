<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/signup.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<h2 class="formTitle">회원가입</h2>
	<form action="" method="post" class="signupForm">
		<div class="form">
			
			
			<div class="formSection">
				<div class = "itemContainer">
					<input type="text" name="name" class = "item name"  placeholder="이름">
				</div>
				<div class = "itemContainer">
					<input type="text" name="jumin" class = "item jumin" placeholder="주민등록번호">
				</div>
				<div class = "itemContainer">
					<input type="text" name="tel" class = "item tel" placeholder="휴대전화번호">
				</div>
			</div>
			
			<div class="formSection">
				<div class="itemContainer">
					<input placeholder="이메일" name="email" id="email" type="email" class = "item email" autofocus><br>
				</div>
				<div class="authReq btn">
					<input type="button" value="인증" id="requestAuthCode" class = "item"><br>
				</div>
				<div class="itemContainer">
					<input  placeholder="인증코드 입력" maxlength="6" disabled="disabled"
						name="authCode" id="authCode" type="text" autofocus class = "item">
				</div>

				<div class="authCode btn">
				<input type="button" value="확인" id="inputAuthCode" class="item" >
				</div>
				
				<div class="auth msg">
				<span id="emailAuthWarn"></span>
				</div>
				
				<div class="itemContainer">
					<input type="password" name="pw" class="item" placeholder="비밀번호" >
				</div>
			
				<div>
				<input type="submit" value="회원가입" class="item btn btn-lg btn-success btn-block"
					id="signup btn" disabled="disabled" >
				</div>
		</div>
		<div class="formSection">
				<div class="authDetail">
					<input type="checkbox" class="checkbox">
					<input type="text" value="인증 약관 전체 동의"  class="item detail" readonly>
				</div>
		</div>
	</div>
</form>
</body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	//인증하기 버튼을 눌렀을 때 동작
	$("#requestAuthCode").click(function() {
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
				alert("회원가입을 위한 인증 코드가 입력하신 이메일로 전송 되었습니다.");
			}
		}); //End Ajax
	});
</script>
<script type="text/javascript">
	//인증 코드 비교
	$("#inputAuthCode").click(function() {
		const inputCode = $("#authCode").val(); //인증번호 입력 칸에 작성한 내용 가져오기

		console.log("입력코드 : " + inputCode);
		console.log("인증코드 : " + code);

		if (Number(inputCode) === code) {
			$("#emailAuthWarn").html('인증번호가 일치합니다.');
			$("#emailAuthWarn").css('color', 'green');
			$('#requestAuthCode').attr('disabled', true);
			$('#email').attr('readonly', true);
			$("#signupBtn").attr("disabled", false);
		} else {
			$("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
			$("#emailAuthWarn").css('color', 'red');
			$("#signupBtn").attr("disabled", true);
		}
	});
</script>


</html>