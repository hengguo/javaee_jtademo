package demo.freemarker;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private String userPassword;
	private Integer age;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private Teacher t = new Teacher();
	
	
	   /**
     * 获取userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * 设置userName
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * 获取userPassword
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }


    /**
     * 设置userPassword
     * @param userPassword userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    /**
     * 获取age
     * @return age
     */
    public Integer getAge() {
        return age;
    }


    /**
     * 设置age
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }


    /**
     * 获取teachers
     * @return teachers
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }


    /**
     * 设置teachers
     * @param teachers teachers
     */
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }


    /**
     * 获取t
     * @return t
     */
    public Teacher getT() {
        return t;
    }


    /**
     * 设置t
     * @param t t
     */
    public void setT(Teacher t) {
        this.t = t;
    }


    public String toString(){
	       return "u:"+this.userName;
	   }


    /** 
     * @Title: addTeacher
     * @author Wang.Hengguo
     * @param t2 
     */
    public void addTeacher(Teacher t2) {
        this.teachers.add(t2);
    }
	
}