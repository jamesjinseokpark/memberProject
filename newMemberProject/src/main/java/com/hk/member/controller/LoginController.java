package com.hk.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.member.service.MemberService;
import com.hk.member.dto.MemberVO;

@Controller
@RequestMapping("/auth") //접속 시 auth/login에서 시
public class LoginController {

	@Autowired
	MemberService memberService;

	@GetMapping("/login")
	public String loginGet() {
		return "auth/loginGet";
	}

	@PostMapping("/login")
	public String loginPost(HttpSession session, MemberVO member) {

		MemberVO loginMember = memberService.memberLogin(member);
		if(loginMember == null) {

			//패스 워드 오류시 전달

			return "auth/loginFail";

		} else {

			session.setAttribute("loginMember", loginMember);

			//보관소 > spring에서 Servlet사용시 Session사용 

			return "redirect:../member/list";

		}
	}
	
	@RequestMapping(value="logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "auth/logout"; // -> 미 구현
	}

}
