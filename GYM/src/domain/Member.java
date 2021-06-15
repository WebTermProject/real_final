package domain;

public class Member {
	private  String member_id;
	private String password;
	private String name;
	private String phone;
	
	public Member(){
		
	}
	public Member(String member_id, String password){
		this.member_id = member_id;
		this.password= password;
		
	}
	public Member(String member_id, String password,String name, String phone){
		this.member_id = member_id;
		this.password= password;
		this.name = name;
		this.phone=phone;
	}
	
	public String getMemberID() {
		return member_id;
	}
	public void setMemberID(String member_id) {
		this.member_id = member_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}

