package demo.domain.user;
import java.util.Date;
import java.util.List;

/**
 * User 实体类
 * @author Wang.Hengguo
 * @date 2015年1月28日下午2:49:01
 */
public class User {

	private long id;

	private String name;

	private String password;

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	@Override
	public String toString(){
		return this.id +" "+ this.name;
	}
}