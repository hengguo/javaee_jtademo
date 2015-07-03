package demo.service;

import demo.domain.order.Order;
import demo.domain.user.User;


public interface UserOrderService {

	public void addUserOrder(User user,Order order);
}
