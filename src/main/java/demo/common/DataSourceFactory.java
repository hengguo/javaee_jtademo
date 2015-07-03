package demo.common;
/**   
* @Title: O2MFactory.java 
* @Package com.gome.o2m.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Wang.Hengguo  
* @date 2015年3月6日
* @version V1.0   
*/


import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * @PackageName : com.gome.o2m.common
 * @ClassName: O2MFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Wang.Hengguo
 * @date 2015年3月6日
 *  
 */
public class DataSourceFactory implements ApplicationContextAware {

    /** 
     * @Title: getO2MDataSource
     * @Description: (这里用一句话描述这个方法的作用). <br/> 
     * @author Wang.Hengguo
     * @return 
     */
    public DataSourceFactory() {}
    public DataSource getDataSource(){
        String sp = DataSourceContextHolder.getDataSourceType();
        return getDataSource(sp);
    }
    public DataSource getDataSource(String dataSourceName){
        System.err.println("dataSourceName:"+dataSourceName);
            if(dataSourceName==null||dataSourceName.equals("")){
                System.err.println("set dataSourceName to defaultDataSource!");
                return (DataSource)this.applicationContext.getBean("defaultDataSource");
            }
            return (DataSource)this.applicationContext.getBean(dataSourceName);
     }

    private ApplicationContext applicationContext = null;
    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
