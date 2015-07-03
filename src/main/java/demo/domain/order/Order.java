/**   
 * @Title: Order.java 
 * @Package demo.domain.order 
 * @author Wang.Hengguo  
 * @date 2015年7月3日
 * @version V1.0   
 */
package demo.domain.order;

/**
 * @PackageName : demo.domain.order
 * @ClassName: Order
 * @author Wang.Hengguo
 * @date 2015年7月3日
 * 
 */
public class Order {

    private String userId;
    private String userName;
    private String id;
    private String name;
    private Long num;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

}
