import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.domain.order.Order;
import demo.domain.user.User;
import demo.service.UserOrderService;

/**   
 * @Title: JTATest.java 
 * @Package  
 * @author Wang.Hengguo  
 * @date 2015年7月3日
 * @version V1.0   
 */

/**
 * @PackageName :
 * @ClassName: JTATest
 * @author Wang.Hengguo
 * @date 2015年7月3日
 * 
 */
public class JTATest {
    private static UserOrderService userOrderService;

    @BeforeClass
    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        userOrderService = (UserOrderService) context.getBean("userOrderService");
    }
    
    @Test
    public void addUseOrder(){
        User u = new User();
        u.setId(1001);
        u.setName("Andrew");
        Order o = new Order();
        o.setName("Nike Shoes");
        userOrderService.addUserOrder(u, o);
    }

}
