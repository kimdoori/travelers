package model;

import java.sql.Time;

public class Pop {
	private int id;
	private String title;
	private String location;
	private String content;
	private int like_num;
	private String photo1;
	private String photo2;
	private String photo3;
	
	private String reg_Date;
	
	
	
	public Pop() {
		super();
	}
	
	
	
	public Pop(String title, String location, String content, int like_num, String photo1, String photo2,
			String photo3) {
		super();
		this.title = title;
		this.location = location;
		this.content = content;
		this.like_num = like_num;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
	}



	public Pop(int id, String title, String location, String content, int like_num, String reg_Date) {
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
	public String getReg_Date() {
		return reg_Date;
	}
	public void setReg_Date(String string) {
		this.reg_Date = string;
	}



	public String getPhoto1() {
		return photo1;
	}



	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}



	public String getPhoto2() {
		return photo2;
	}



	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}



	public String getPhoto3() {
		return photo3;
	}



	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}



	@Override
	public String toString() {
		return "Pop [id=" + id + ", title=" + title + ", location=" + location + ", content=" + content + ", like_num="
				+ like_num + ", photo1=" + photo1 + ", photo2=" + photo2 + ", photo3=" + photo3 + ", reg_Date="
				+ reg_Date + "]";
	}
	
	
	

}
