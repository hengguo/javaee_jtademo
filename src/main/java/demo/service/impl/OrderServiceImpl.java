package demo.service.impl;

import javax.annotation.Resource;

import demo.domain.order.Order;
import demo.mapper.order.OrderMapper;
import demo.service.OrderService;

/** 
 * @PackageName : demo.service.impl
 * @ClassName: OrderServiceImpl 
 * @author Wang.Hengguo
 * @date 2015年7月3日
 *  
 */
public class OrderServiceImpl implements OrderService{
    @Resource(name="orderDao")
    private OrderMapper orderDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

}
