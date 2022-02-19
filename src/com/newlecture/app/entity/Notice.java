package com.newlecture.app.entity;

import java.util.Date;

public class Notice { //값을 담을 수 있는 그릇
	private int id;
	private String title;
	private String writerId;
	private String content;
	private Date regDate;
	private int hit;
	private String files;
	
	public Notice() {
		
	}
	
	public Notice(int id, String title, String writerId, String content, Date regDate, int hit, String files) {
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.files = files;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	
	
}
