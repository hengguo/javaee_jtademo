package demo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import demo.common.page.Page;
import demo.domain.User;

@Repository("userDao")
public interface UserMapper {

	public User selectUser(Long id);
	public User selectUser(Map<Object, Object> map);
	public User selectUserGroup(Long id);
	public List<User> selectUsersPageList(Page page);
	
	public void deleteUser(Long id);
	public void addUser(User user);

}
