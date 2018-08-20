package model;

public class Comment {
	
	private int id;
	private int pop_id;
	private String corn_id;
	private String corn_name;
	private String comment;
	private String reg_date;
	
	
	
	public Comment(int pop_id, String corn_id, String corn_name, String comment) {
		super();
		this.pop_id = pop_id;
		this.corn_id = corn_id;
		this.corn_name = corn_name;
		this.comment = comment;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCorn_name() {
		return corn_name;
	}



	public void setCorn_name(String corn_name) {
		this.corn_name = corn_name;
	}



	public String getReg_date() {
		return reg_date;
	}



	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}



	public int getPop_id() {
		return pop_id;
	}



	public void setPop_id(int pop_id) {
		this.pop_id = pop_id;
	}



	public String getCorn_id() {
		return corn_id;
	}



	public void setCorn_id(String corn_id) {
		this.corn_id = corn_id;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Comment() {
		super();
	}



	public Comment(int pop_id, String corn_id, String comment) {
		super();
		this.pop_id = pop_id;
		this.corn_id = corn_id;
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "Comment [pop_id=" + pop_id + ", corn_id=" + corn_id + ", comment=" + comment + "]";
	}
	
	

}
