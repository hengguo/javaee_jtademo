package demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import demo.common.Constrants;
import demo.common.DataSourceContextHolder;
import demo.common.page.Page;
import demo.domain.User;
import demo.mapper.UserMapper;
import demo.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {

	private static int ctn = 0;

	public UserServiceImpl() {
		System.out.println("UserServiceImpl initialized count = " + ctn);
		UserServiceImpl.ctn++;
	}
	@Resource
	private UserMapper userDao;
	@Override
	public User selectUser(Long id) {
        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
		return userDao.selectUser(id);
	}

	@Override
	public User selectUserGroup(Long id) {
        DataSourceContextHolder.setDataSourceType(Constrants.User);// 设置为另一个数据源
		return userDao.selectUserGroup(id);
	}

	@Override
	public List<User> selectUsersPageList(Page page) {
		return userDao.selectUsersPageList(page);
	}

	@Override
	public void deleteAndInsert(User user) {
		System.out.println("delete之前存在这条数据吗?");
		System.out.println(this.userDao.selectUser(user.getId()));
		this.userDao.deleteUser(user.getId());
		System.out.println("delete之后存在这条数据吗?");
		System.out.println(this.userDao.selectUser(user.getId()));
		this.userDao.addUser(user);
		System.out.println("插入之后存在这条数据吗?");
		System.out.println(this.userDao.selectUser(user.getId()));

	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollbackOper() {
        User u1 = this.userDao.selectUser(1l);
        System.out.println(u1);
        
        
        User u2 = this.userDao.selectUser(1l);
        System.out.println(u2);
        
//        User user = new User();
//        user.setId(130l);
//        this.userDao.addUser(user);
//        
//        DataSourceContextHolder.setDataSourceType(Constrants.User);// 设置为另一个数据源
//        
//        User user2 = new User();
//        user2.setId(118L);
//        this.userDao.addUser(user2);
    }

}
