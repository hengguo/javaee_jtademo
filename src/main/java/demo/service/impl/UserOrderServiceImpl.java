package demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.domain.order.Order;
import demo.domain.user.User;
import demo.mapper.order.OrderMapper;
import demo.mapper.user.UserMapper;
import demo.service.UserOrderService;

/**
 * @PackageName : demo.service.impl
 * @ClassName: UserOrderServiceImpl
 * @author Wang.Hengguo
 * @date 2015年7月3日
 * 
 */
@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService {
    @Resource(name = "orderDao")
    private OrderMapper orderDao;
    @Resource
    private UserMapper userDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserOrder(User user, Order order) {
        userDao.addUser(user);
        orderDao.addOrder(order);
    }

}
