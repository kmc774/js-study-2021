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
	
	
	
	List<Board> selectBoardList(Paging paging); //�ش� �������� �Խñ� ��ȸ
	
	
	int insertFile(FileVo files); // ���� insert
	
	
	@Select("SELECT COUNT(*) FROM M_BOARD")
	int selectContentCnt(); // �Խñ� ��������
	
	
	@Select("SELECT * FROM M_BOARD WHERE BD_IDX = #{bdIdx} ")
	Board viewDetail(@Param("bdIdx")int bdIdx); //�Խñ� �ڼ�������
	
	@Select("SELECT * FROM M_FILE WHERE TYPE_IDX = #{bdIdx}")
	List<FileVo> viewFile(@Param("bdIdx")String bdIdx);
	
	
	@Insert("INSERT INTO M_BOARD (BD_IDX, TITLE, CONTENT, USER_ID, IS_PUBLIC) VALUES (SC_BD_IDX.NEXTVAL, #{title}, #{content}, #{userId}, #{isPublic})")
	int insertBoard(Board board); // �Խñ� ����ϱ�
	
	
	@Update("UPDATE M_BOARD SET TITLE = #{title}, CONTENT = #{content}, IS_PUBLIC = #{isPublic}, UPDATE_DATE = #{updateDate} WHERE BD_IDX= #{bdIdx}")
	int updateBoard(Board board); // �Խñ� �����ϱ�
	
	
	@Delete("DELETE FROM M_BOARD WHERE BD_IDX = #{bdIdx}")
	int deleteBoard(@Param("bdIdx")int bdIdx); //�Խñ� �����ϱ�
	
	
	@Update("UPDATE M_BOARD SET BD_COUNT = BD_COUNT+1 WHERE BD_IDX = #{bdIdx}")
	int updateCount(@Param("bdIdx") int bdIdx);
	
	
	@Select("SELECT COUNT(*) FROM M_RECOMMEND WHERE USER_ID = #{userId} AND BD_IDX = #{bdIdx}")
	int checkRecommend(@Param("bdIdx")int bdIdx, @Param("userId")String userId);
	
	
	@Insert("INSERT INTO M_RECOMMEND(REC_IDX, USER_ID, BD_IDX) VALUES (SC_REC_IDX.NEXTVAL,#{userId}, #{bdIdx})")
	int insertRecommend(@Param("bdIdx")int bdIdx, @Param("userId")String userId);
	
	
	
	
}
