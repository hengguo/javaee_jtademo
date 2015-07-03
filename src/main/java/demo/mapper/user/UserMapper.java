package demo.mapper.user;

import org.springframework.stereotype.Repository;

import demo.domain.user.User;

@Repository("userDao")
public interface UserMapper {

	public User selectUser(Long id);
	
	public void addUser(User user);

}
