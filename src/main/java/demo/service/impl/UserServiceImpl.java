package demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import demo.common.Constrants;
import demo.common.DataSourceContextHolder;
import demo.domain.user.User;
import demo.mapper.user.UserMapper;
import demo.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    @Override
    public User selectUser(Long id) {
        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
        return userDao.selectUser(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(User user) {

        this.userDao.addUser(user);
    }

}
