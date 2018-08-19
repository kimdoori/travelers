package model;

import java.sql.Time;

public class Pop {
	private int id;
	private String title;
	private String location;
	private String content;
	private int like_num;
	private Time reg_Date;
	
	
	
	public Pop() {
		super();
	}
	public Pop(int id, String title, String location, String content, int like_num, Time reg_Date) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.content = content;
		this.like_num = like_num;
		this.reg_Date = reg_Date;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public Time getReg_Date() {
		return reg_Date;
	}
	public void setReg_Date(Time reg_Date) {
		this.reg_Date = reg_Date;
	}
	@Override
	public String toString() {
		return "Pop [id=" + id + ", title=" + title + ", location=" + location + ", content=" + content + ", like_num="
				+ like_num + ", reg_Date=" + reg_Date + "]";
	}
	
	
	

}
