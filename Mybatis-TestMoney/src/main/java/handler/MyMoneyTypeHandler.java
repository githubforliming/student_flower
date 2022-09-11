package handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-20 10:37
 */
public class MyMoneyTypeHandler extends BaseTypeHandler<Money> {

    // 入参从Money 转换成 Long 分
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("插入/更新是Money转换Long,转换前："+parameter+",转换后："+parameter.getAmountMinorLong()+"分");
        ps.setLong(i, parameter.getAmountMinorLong());
    }

    //返回时Long 转 Money  这个是根据列名称
    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Money res = Money.ofMinor(CurrencyUnit.of("CNY"), rs.getLong(columnName));
        System.out.println("查询时转换,转换前"+rs.getLong(columnName)+"分,转换后："+res);
        return res;
    }
    //返回时Long 转 Money  这个是根据列顺序
    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Money res = Money.ofMinor(CurrencyUnit.of("CNY"), rs.getLong(columnIndex));
        System.out.println("查询时转换,转换前"+rs.getLong(columnIndex)+"分,转换后："+res);
        return res;
    }
    //返回时Long 转 Money  这个是callableStatement 方式返回 根据列顺序
    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Money res = Money.ofMinor(CurrencyUnit.of("CNY"), cs.getLong(columnIndex));
        System.out.println("查询时转换,转换前"+cs.getLong(columnIndex)+"分,转换后："+res);
        return res;
    }
}
