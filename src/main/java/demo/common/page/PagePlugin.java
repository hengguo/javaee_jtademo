/** 
 * @Project:     sy_oa  
 * @Title:       PagePlugin.java 
 * @Package:     com.sy.common.page 
 * @Description: 分页处理拦截器
 * @author:      WangYandong
 * @date:        2013-8-4 上午9:27:39 
 * @version:     v1.0
 */
package demo.common.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {

    private static String dialect   = "";
    private static String pageSqlId = "";

    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper .getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            if (mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject instanceof Page) {  
                    Page page = (Page) parameterObject;  
                    //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性  
                    //拦截到的prepare方法参数是一个Connection对象  
                    Connection connection = (Connection)ivk.getArgs()[0];  
                    //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
                    String sql = boundSql.getSql();  
                    //给当前的page参数对象设置总记录数  
                    this.setTotalRecord(page,  
                           mappedStatement, connection);  
                    //获取分页Sql语句  
                    String pageSql = this.generatePageSql(sql, page);  
                    //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句  
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }  
                //--------
                /*
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                } else {
                    Connection connection = (Connection) ivk.getArgs()[0];
                    String sql = boundSql.getSql();
                    String countSql = " SELECT COUNT(*) FROM ( " + sql + " ) tmp_count ";
                    PreparedStatement countStmt = connection.prepareStatement(countSql);
                    BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                        boundSql.getParameterMappings(), parameterObject);
                    setParameters(countStmt, mappedStatement, countBS, parameterObject);
                    ResultSet rs = countStmt.executeQuery();
                    int count = 0;
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                    rs.close();
                    countStmt.close();
                    Page page = null;
                    if (parameterObject instanceof Page) {
                        page = (Page) parameterObject;
                        page.setEntityOrField(true);
                        page.setTotalResult(count);
                    } else {
                        Field pageField = ReflectHelper
                            .getFieldByFieldName(parameterObject, "page");
                        if (pageField != null) {
                            page = (Page) ReflectHelper
                                .getValueByFieldName(parameterObject, "page");
                            if (page == null)
                                page = new Page();
                            page.setEntityOrField(false);
                            page.setTotalResult(count);
                            ReflectHelper.setValueByFieldName(parameterObject, "page", page);
                        } else {
                            throw new NoSuchFieldException(parameterObject.getClass().getName()
                                                           + "不存在 page属性！");
                        }
                    }
                    String pageSql = generatePageSql(sql, page);
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }*/
            }
        }
        return ivk.proceed();
    }

    /**
     * @Title:       setParameters 
     * @Description: 为SQL赋值
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException    
     * @return:      void
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-4 下午12:22:53
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,
                               BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters")
            .object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration
                .newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                               && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(
                                propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter "
                                                    + propertyName + " of statement "
                                                    + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**
     * @Title:       generatePageSql 
     * @Description: 根据SQL方言生成特定的分页SQL
     * @param sql
     * @param page
     * @return    
     * @return:      String
     * @throws:      
     * @author:      WangYandong
     * @date:        2013-8-4 下午12:23:23
     */
    private String generatePageSql(String sql, Page page) {
        if (page != null && StringUtils.isNotEmpty(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equalsIgnoreCase(dialect)) {
                pageSql.append(sql);
                pageSql.append(" LIMIT " + page.getCurrentResult() + "," + page.getPageCount());
            } else if ("oracle".equalsIgnoreCase(dialect)) {
                pageSql.append(" SELECT * FROM (SELECT tmp_tb.*, ROWNUM row_id FROM ( ");
                pageSql.append(sql);
                pageSql.append(" ) tmp_tb WHERE ROWNUM <= ");
                pageSql.append(page.getCurrentResult() + page.getPageCount());
                pageSql.append(" ) WHERE row_id > ");
                pageSql.append(page.getCurrentResult());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    /**
     * @Title:       setProperties
     * @Description: 设置数据库方言
     * @param p 
     * @author:      WangYandong
     * @date:        2013-8-4 下午12:24:45
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (StringUtils.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (StringUtils.isEmpty(pageSqlId)) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
    }
    
    /** 
     * 给当前的参数对象page设置总记录数 
     * 
     * @param page Mapper映射语句对应的参数对象 
     * @param mappedStatement Mapper映射语句 
     * @param connection 当前的数据库连接 
     */  
    private void setTotalRecord(Page page,  
           MappedStatement mappedStatement, Connection connection) {  
       //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。  
       //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。  
       BoundSql boundSql = mappedStatement.getBoundSql(page);  
       //获取到我们自己写在Mapper映射语句中对应的Sql语句  
       String sql = boundSql.getSql();  
       //通过查询Sql语句获取到对应的计算总记录数的sql语句  
       String countSql = " SELECT COUNT(*) FROM ( " + sql + " ) tmp_count ";

       //通过BoundSql获取对应的参数映射  
       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
       //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。  
//       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);  
       //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象  
       ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, boundSql);  
       //通过connection建立一个countSql对应的PreparedStatement对象。  
       PreparedStatement pstmt = null;  
       ResultSet rs = null;  
       try {  
           pstmt = connection.prepareStatement(countSql);  
           //通过parameterHandler给PreparedStatement对象设置参数  
           parameterHandler.setParameters(pstmt);  
           //之后就是执行获取总记录数的Sql语句和获取结果了。  
           rs = pstmt.executeQuery();  
           if (rs.next()) {  
              int totalRecord = rs.getInt(1);  
              //给当前的参数page对象设置总记录数  
              page.setTotalResult(totalRecord);
           }  
       } catch (SQLException e) {  
           e.printStackTrace();  
       } finally {  
           try {  
              if (rs != null)  
                  rs.close();  
               if (pstmt != null)  
                  pstmt.close();  
           } catch (SQLException e) {  
              e.printStackTrace();  
           }  
       }  
    } 

}
