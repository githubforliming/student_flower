package interceptor;

import entity.PageQo;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("执行拦截器代码");
        // 获取参数 这里指定如果参数是PageQo的子类，才会进行分页 其他不进行分页
        StatementHandler  statementHandler = (StatementHandler) invocation.getTarget();
        //获取StatementHandler的包装类
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        BoundSql boundSql = statementHandler.getBoundSql();
        Object param = boundSql.getParameterObject();
        // 参数是PageQo的子类 才会进行分页操作
        if(param instanceof PageQo) {
            // 强转 主要是为了分页参数
            PageQo pageQo = (PageQo) param;
            //获取原始SQL语句
            String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
            System.out.println("原sql:"+originalSql);
            String sql = originalSql.trim() + " limit " + (pageQo.getPageNum()-1)* pageQo.getPageSize() + ", " + pageQo.getPageSize();
            System.out.println("分页sql:"+sql);
            metaObject.setValue("delegate.boundSql.sql", sql);
        }
        // 默认不进行分页逻辑
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
