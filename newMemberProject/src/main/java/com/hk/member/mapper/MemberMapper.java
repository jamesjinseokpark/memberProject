package com.hk.member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hk.member.dto.MemberVO;

@Repository
public interface MemberMapper {
	
//	crud를 구현한다 
//	1. 전체리스트
//	@Select ("select from members")
//	
	public List<MemberVO> memberList(); //리스트 불러오기
	
	public int memberAdd(MemberVO member); //등록
	
	public int memberUpdate(MemberVO member);// 수정 
	
	public MemberVO memberGetOne(int mno); //정보호출
	
	public int memberDelete (int mno); // 삭제 
	
	public MemberVO memberLogin(MemberVO member);

	public MemberVO checkIdDup(String email);

}
