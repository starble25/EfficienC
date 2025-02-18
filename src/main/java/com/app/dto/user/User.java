package com.app.dto.user;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Getter;


@Data
public class User {
	
	
	int id;
	
	@NotBlank(message = "이메일을 다시 입력해주세요")
	@Email
	String email;
	
	@NotBlank(message = "비밀번호가 형식에 맞지않습니다.(대/소문자 1자 포함, 특수문자 1개 포함, 최소 12글자 최대 16글자")
	@Length(min=12, max=16)
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{12,}$", message = "비밀번호가 형식에 맞지않습니다.(대/소문자 1자 포함, 특수문자 1개 포함, 최소 12글자 최대 16글자")
	String pw;
	
	@NotBlank(message = "이름은 필수 입력사항입니다.")
	@Pattern(regexp="^[ㄱ-ㅎ가-힣]*$", message="한글만 입력할 수 있습니다." )
	String name;
	
	@NotBlank(message = "전화번호는 필수 입력사항입니다.")
	@Length(min=10, max=11, message="정확한 전화번호를 입력해주세요" )
	@Pattern(regexp="^[0-9]*$", message="숫자만 입력할 수 있습니다." )
	String tel;
	
	@NotBlank(message = "주민등록번호는 필수 입력사항입니다.")
	@Pattern(regexp="^[0-9]*$" , message="숫자만 입력할 수 있습니다." )
	@Length(min=11, max=11, message="주민번호를 다시 입력해주세요" )
	String jumin;
	
	String positionCode;
	String deptCode;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	int cmpId;
	
}