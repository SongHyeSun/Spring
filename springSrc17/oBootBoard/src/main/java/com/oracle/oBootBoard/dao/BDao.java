package com.oracle.oBootBoard.dao;

import java.util.ArrayList;
import com.oracle.oBootBoard.dto.BDto;

public interface BDao {
	public ArrayList<BDto> boardList();
	public BDto contentView(String strId);
	
	//Dao방식
	public void modify(int bId, String bName, String bTitle, String bContent);
	
	//DTO 방식
	public void modify3(BDto bDto);
	
	public void delete(int bId);
	public void write(String bName, String bTitle, String bContent);
	public BDto reply_view(int strbId);
	public BDto reply(int bId, String bName, String bTitle, 
			String bContent, int bGroup, int bStep, int bIndent);

}
