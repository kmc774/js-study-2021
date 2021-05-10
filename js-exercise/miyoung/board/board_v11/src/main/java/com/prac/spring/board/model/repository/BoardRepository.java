package com.prac.spring.board.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.prac.spring.board.model.vo.Board;
import com.prac.spring.common.file.FileVo;
import com.prac.spring.common.paging.Paging;

@Mapper
public interface BoardRepository {
	
	
	
	List<Board> selectBoardList(Paging paging); //해당 페이지의 게시글 조회
	
	
	int insertFile(FileVo files); // 파일 insert
	
	
	@Select("SELECT COUNT(*) FROM M_BOARD")
	int selectContentCnt(); // 게시글 가져오기
	
	
	@Select("SELECT * FROM M_BOARD WHERE BD_IDX = #{bdIdx} ")
	Board viewDetail(@Param("bdIdx")int bdIdx); //게시글 자세히보기
	
	@Select("SELECT * FROM M_FILE WHERE TYPE_IDX = #{bdIdx}")
	List<FileVo> viewFile(@Param("bdIdx")String bdIdx);
	
	
	@Insert("INSERT INTO M_BOARD (BD_IDX, TITLE, CONTENT, USER_ID, IS_PUBLIC) VALUES (SC_BD_IDX.NEXTVAL, #{title}, #{content}, #{userId}, #{isPublic})")
	int insertBoard(Board board); // 게시글 등록하기
	
	
	@Update("UPDATE M_BOARD SET TITLE = #{title}, CONTENT = #{content}, IS_PUBLIC = #{isPublic}, UPDATE_DATE = #{updateDate} WHERE BD_IDX= #{bdIdx}")
	int updateBoard(Board board); // 게시글 수정하기
	
	
	@Delete("DELETE FROM M_BOARD WHERE BD_IDX = #{bdIdx}")
	int deleteBoard(@Param("bdIdx")int bdIdx); //게시글 삭제하기
	
	
	@Update("UPDATE M_BOARD SET BD_COUNT = BD_COUNT+1 WHERE BD_IDX = #{bdIdx}")
	int updateCount(@Param("bdIdx") int bdIdx);
	
	
	@Select("SELECT COUNT(*) FROM M_RECOMMEND WHERE USER_ID = #{userId} AND BD_IDX = #{bdIdx}")
	int checkRecommend(@Param("bdIdx")int bdIdx, @Param("userId")String userId);
	
	
	@Insert("INSERT INTO M_RECOMMEND(REC_IDX, USER_ID, BD_IDX) VALUES (SC_REC_IDX.NEXTVAL,#{userId}, #{bdIdx})")
	int insertRecommend(@Param("bdIdx")int bdIdx, @Param("userId")String userId);
	
	
	
	
}
