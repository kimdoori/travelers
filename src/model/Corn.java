package model;

public class Corn {

	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String birth;
	private String phone;
	private int like_num;
	private int pop_num;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	
	
	
	
	public int getPop_num() {
		return pop_num;
	}
	public void setPop_num(int pop_num) {
		this.pop_num = pop_num;
	}
	public Corn() {
		super();
	}
	
	
	public Corn(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	
	public Corn(String id, String pw, String name, String nickname, String birth, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.phone = phone;
	}
	public Corn(String id, String pw, String name, String nickname, String birth, String phone, int like_num) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.phone = phone;
		this.like_num = like_num;
	}
	
	
	
	public Corn(String id, String pw, String name, String nickname, String birth, String phone, int like_num,
			int pop_num) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.phone = phone;
		this.like_num = like_num;
		this.pop_num = pop_num;
	}
	@Override
	public String toString() {
		return "Corn [id=" + id + ", pw=" + pw + ", name=" + name + ", nickname=" + nickname + ", birth=" + birth
				+ ", phone=" + phone + ", like_num=" + like_num + ", pop_num=" + pop_num + "]";
	}
	
	
	
	
	
	
	
	
}
