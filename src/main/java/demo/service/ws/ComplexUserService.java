/**   
* @Title: ComplexUserService.java 
* @Package demo.service.ws 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Wang.Hengguo  
* @date 2015年1月28日
* @version V1.0   
*/
package demo.service.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import demo.domain.user.User;

/**
 * 
 * @Package demo.service.ws 
 * @author Wang.Hengguo
 * @date 2015年1月28日下午2:51:53
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ComplexUserService {
    
    public User getUserByName(@WebParam(name = "name") String name);
    
    public void setUser(User user);
}