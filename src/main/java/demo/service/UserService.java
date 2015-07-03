package demo.service;

import java.util.List;
import java.util.Map;

import demo.common.page.Page;
import demo.domain.User;

public interface UserService {

	public User selectUser(Long id);
	public User selectUserGroup(Long id);
	
	public List<User> selectUsersPageList(Page page);
	
	public void deleteAndInsert(User user);

	/**
	 * 操作会出错  事务会rollback
	 * @Title: rollbackOper
	 * @Description: (这里用一句话描述这个方法的作用). <br/> 
	 * @author Wang.Hengguo
	 */
	public void rollbackOper();
}
