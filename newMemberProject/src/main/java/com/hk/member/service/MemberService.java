package com.hk.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.member.dto.MemberVO;
import com.hk.member.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired // component-scan ... servlet-context.xml
	MemberMapper memberMapper; // setConnection new를 해줘야

	public List<MemberVO> memberList() {
		return memberMapper.memberList(); // mybatis를 호출하는 

	}

	public int memberAdd(MemberVO member) {

		int rev = memberMapper.memberAdd(member);
		return rev;

	}

	public MemberVO memberGetOne(int mno) {
		return memberMapper.memberGetOne(mno);

	}

	public int memberUpdate(MemberVO member) {

		int rev = memberMapper.memberUpdate(member);
		return rev;

	}

	public int memberDelete(int mno) {
		return memberMapper.memberDelete(mno);

	}
	
	public MemberVO memberLogin(MemberVO member) {
		return memberMapper.memberLogin(member);
	}

	public MemberVO checkIdDup(String email) {
		// TODO Auto-generated method stub
		return memberMapper.checkIdDup(email);
	}


}
