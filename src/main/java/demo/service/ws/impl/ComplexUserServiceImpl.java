package demo.service.ws.impl;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import demo.domain.user.User;
import demo.service.ws.ComplexUserService;


/**
 * <b>function:</b> WebService传递复杂对象，如JavaBean、Array、List、Map等
 * @author Wang.Hengguo
 * @date 2015年1月28日下午2:55:56
 */
@WebService
@SOAPBinding(style = Style.RPC)
public class ComplexUserServiceImpl implements ComplexUserService {
    
    public User getUserByName(@WebParam(name = "name") String name) {
        User user = new User();
        user.setId(new Date().getSeconds());
        user.setName(name);
        user.setPassword("aa");
        user.setCreateTime(new Date());
        return user;
    }
    
    public void setUser(User user) {
        System.out.println("############Server setUser###########");
        System.out.println("setUser:" + user);
    }
}