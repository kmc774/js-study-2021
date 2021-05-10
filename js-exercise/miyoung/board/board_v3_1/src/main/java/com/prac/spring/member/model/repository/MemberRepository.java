package com.prac.spring.member.model.repository;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.prac.spring.member.model.vo.Member;


@Mapper
public interface MemberRepository {

	
	@Insert("INSERT INTO M_MEMBER (user_idx, user_id, password) values(sc_user_idx.nextval, #{userId}, #{password})")
	int InsertMember(Member member);
	
	@Select("SELECT * FROM M_MEMBER WHERE USER_ID = #{userId}")
	Member loginMember(@Param("userId")String userId);
	
	
	
	
	
	
}
