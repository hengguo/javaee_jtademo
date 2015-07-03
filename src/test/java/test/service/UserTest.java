package test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.common.Constrants;
import demo.common.DataSourceContextHolder;
import demo.domain.User;
import demo.mapper.UserMapper;
import demo.service.UserService;

public class UserTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-config.xml");

//    @Test
    public void testDao() {
        
//        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
//        UserService userDao = (UserService)ac.getBean("userService");
        // hibernate创建实体
        UserMapper userDao = (UserMapper)ac.getBean("userDao");

        User u1 = userDao.selectUser(3l);

        DataSourceContextHolder.setDataSourceType(Constrants.User);// 设置为另一个数据源

        User u2 = userDao.selectUserGroup(3l);
        
        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
        User u3 = userDao.selectUserGroup(3l);
        
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);

    }
    @Test
    public void testService() {
        
//        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
        UserService userService = (UserService)ac.getBean("userService");
        // hibernate创建实体

        User u2 = userService.selectUserGroup(3l);
        User u1 = userService.selectUser(3l);

//        DataSourceContextHolder.setDataSourceType(Constrants.User);// 设置为另一个数据源

        
//        DataSourceContextHolder.setDataSourceType(Constrants.Admin);// 设置为另一个数据源
        User u3 = userService.selectUserGroup(3l);
        
        System.out.println(u2);
        System.out.println(u1);
        System.out.println(u3);

    }
    
    @Test
    public void testRollback(){
        UserService userService = (UserService)ac.getBean("userService");
        userService.rollbackOper();
    }


}