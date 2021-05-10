package com.prac.spring.board.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.prac.spring.board.model.vo.Board;
import com.prac.spring.common.paging.Paging;

@Mapper
public interface BoardRepository {
	
	
	
	List<Board> selectAllList(Paging paging); //모든 게시글 조회
	
	
	@Select("SELECT COUNT(*) FROM M_BOARD")
	int selectContentCnt();
	
	@Select("SELECT * FROM M_BOARD WHERE BD_IDX = #{bdIdx} ")
	Board viewDetail(@Param("bdIdx")int bdIdx); //게시글 자세히보기
	
	
	@Insert("INSERT INTO M_BOARD (BD_IDX, TITLE, CONTENT) VALUES (SC_BD_IDX.NEXTVAL, #{title}, #{content})")
	int insertBoard(Board board); // 게시글 등록하기
	
	
	@Update("UPDATE M_BOARD SET TITLE = #{title}, CONTENT = #{content}, UPDATE_DATE = #{updateDate} WHERE BD_IDX= #{bdIdx}")
	int updateBoard(Board board); // 게시글 수정하기
	
	
	@Delete("DELETE FROM M_BOARD WHERE BD_IDX = #{bdIdx}")
	int deleteBoard(@Param("bdIdx")int bdIdx); //게시글 삭제하기
}
