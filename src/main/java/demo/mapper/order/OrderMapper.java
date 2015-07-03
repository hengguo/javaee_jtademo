package demo.mapper.order;

import org.springframework.stereotype.Repository;

import demo.domain.order.Order;

@Repository("orderDao")
public interface OrderMapper {
    public void addOrder(Order order);

}
