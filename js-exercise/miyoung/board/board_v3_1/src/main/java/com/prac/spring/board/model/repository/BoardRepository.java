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
	
	
	
	List<Board> selectAllList(Paging paging); //��� �Խñ� ��ȸ
	
	
	@Select("SELECT COUNT(*) FROM M_BOARD")
	int selectContentCnt();
	
	@Select("SELECT * FROM M_BOARD WHERE BD_IDX = #{bdIdx} ")
	Board viewDetail(@Param("bdIdx")int bdIdx); //�Խñ� �ڼ�������
	
	
	@Insert("INSERT INTO M_BOARD (BD_IDX, TITLE, CONTENT) VALUES (SC_BD_IDX.NEXTVAL, #{title}, #{content})")
	int insertBoard(Board board); // �Խñ� ����ϱ�
	
	
	@Update("UPDATE M_BOARD SET TITLE = #{title}, CONTENT = #{content}, UPDATE_DATE = #{updateDate} WHERE BD_IDX= #{bdIdx}")
	int updateBoard(Board board); // �Խñ� �����ϱ�
	
	
	@Delete("DELETE FROM M_BOARD WHERE BD_IDX = #{bdIdx}")
	int deleteBoard(@Param("bdIdx")int bdIdx); //�Խñ� �����ϱ�
}
