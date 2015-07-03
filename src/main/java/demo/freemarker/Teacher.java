package demo.freemarker;
public class Teacher {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString(){
	    return "t: "+ this.userName;
	}
}