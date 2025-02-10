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

	<form action="" method="post">

		<label for="name">이름<br> <input type="text" name="name"><br>
		</label> <label for="jumin">주민번호<br> <input type="text"
			class="jumin" name="frontJumin"> - <input type="password"
			class="jumin" name="backJumin"><br>
		</label> <label for="tel">전화번호<br> <input type="text" name="tel">
			<br>
		</label> <label for="email"> 이메일<br> <input type="text"
			name="email" id="email"> <input type="button" value="인증하기"
			id="emailAuth"><br> <input type="text" name="checkemail">
			<button>인증번호 확인</button> <br>
		</label> <label for="pw">비밀번호<br> <input type="password"
			name="pw"><br> 비밀번호 확인<br>
		</label>


	</form>
	<button type="submit">회원가입 하기</button>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
    function sendNumber(){
        $("#mail_number").css("display","block");
        $.ajax({
            url:"/email", 
            type:"post",
            dataType:"json",
            data:{"email" : $("#email").val()},
            success: function(data){
                alert("인증번호 발송");
                $("#Confirm").attr("value",data);
            },
	        }
        });
    }

    function confirmNumber(){
        var number1 = $("#number").val();
        var number2 = $("#Confirm").val();

        if(number1 == number2){
            alert("인증되었습니다.");
        }else{
            alert("번호가 다릅니다.");
        }
    }
</script>
</html>