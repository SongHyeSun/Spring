package com.oracle.oBootBoardWarPorm.dto;

import java.sql.Timestamp;

//DTO에 값을 담아갈 때, 생성자를 통해서 가져갈 수도 있고 setting을 통해서 가져갈 수도 있다.
// 여기서는 setting을 통해 가져 간 방식
public class BDto {
	int 		bId;
	String		bName;
	String		bTitle;
	String		bContent;
	Timestamp	bDate;
	int 		bHit;
	int 		bGroup;
	int 		bStep;
	int 		bIndent;
	
	public BDto() {
		
	}
	
	//현재 BDto의 생성자를 여러개 생성해서 overloading
	public BDto(int bId, String bName, String bTitle, String bContent,
					 Timestamp bDate, int bHit,
					 int bGroup, int bStep, int bIndent) {
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}
	
	public BDto(int bId, String bName, String bTitle, String bContent) {
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
	}
	
	public BDto(String bName, String bTitle, String bContent) {
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
	}
	
	public BDto(int bId) {
		this.bId = bId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	
}
