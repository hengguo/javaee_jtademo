package demo.domain;
import java.util.Date;

/**
 * @describe: 描述User和Group之间的<strong>映射</strong>关系
 * @author: Nirvana
 * @version: V1.0 2011-3-11下午02:57:52 create
 */
public class UserGroupLink {

	private User user;

	private Group group;

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}