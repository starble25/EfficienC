package com.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}

	/*
	 * 관리자 : 1. id 와 pw 가 동시에 일치하는 사용자 users Table find 2. users Table -> cmp_id 체크,
	 * admin 권한 체크 후 사이트 이동 //권한 있을 시 navbar에 관리자<->사용자 사이트 이동 자유롭게
	 * 
	 * 1. id 검색 -> 사용자 users Table find 2. 회사코드 부여된 사용자 List -> users Table find (
	 * 회사 코드) 3. 사용자 삭제 및 직급, 부서 수정
	 * 
	 */
}
