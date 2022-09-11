import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-20 10:46
 */
public class TT03 {
    public static void main(String[] args) {
        Money y1 = Money.of(CurrencyUnit.of("CNY"), 100.14);
        Money y2 = Money.of(CurrencyUnit.of("CNY"), 100.35);
        Money y3 = Money.of(CurrencyUnit.of("CNY"), 100.66);
        Money y4 = Money.of(CurrencyUnit.of("CNY"), -100.66);
        Money y5 = Money.of(CurrencyUnit.of("CNY"), 0);
        Money y6 = Money.of(CurrencyUnit.USD, 102);

        // 乘法 在计算订单总价的时候可能会用到 某个商品购买多份
        Money multipliedBy = y1.multipliedBy(12);
        System.out.println("y1*12="+multipliedBy.getAmount());
        Money multipliedBy1 = y1.multipliedBy(12, RoundingMode.HALF_UP);
        Money multipliedBy2 = y1.multipliedBy(BigDecimal.valueOf(12.99223), RoundingMode.HALF_UP);
        System.out.println("四舍五入：y1*12="+multipliedBy.getAmount());
        System.out.println("四舍五入：y1*12.09223="+multipliedBy2.getAmount());
        // 除法  在分摊优惠的时候可能会用到 根据不同的比例分摊
        // 与bigdecimal除法一样 都要指定舍入模式
        Money dividedBy = y1.dividedBy(10, RoundingMode.HALF_UP);
        Money dividedBy1 = y1.dividedBy(10.02, RoundingMode.HALF_UP);
        Money dividedBy2 = y1.dividedBy(BigDecimal.valueOf(19.02), RoundingMode.HALF_UP);
        System.out.println("y1÷10="+dividedBy.getAmount());
        System.out.println("y1÷10.02="+dividedBy1.getAmount());
        System.out.println("y1÷19.02="+dividedBy2.getAmount());

        // 负数 负数或0
        boolean negative = y1.isNegative();
        System.out.println("y1是不是负数："+negative);
        boolean negative1 = y4.isNegative();
        System.out.println("y4是不是负数："+negative1);
        boolean negativeOrZero = y5.isNegativeOrZero();
        System.out.println("y5是负数或者是0："+negativeOrZero);
        // 正数 正数或0
        boolean positive = y5.isPositive();
        System.out.println("y5是正数："+positive);
        boolean positiveOrZero = y5.isPositiveOrZero();
        System.out.println("y5是正数或者0："+positiveOrZero);
        // 大于、小于 、等于0
        boolean greaterThan = y1.isGreaterThan(y2);
        System.out.println("y1>y2:"+greaterThan);
        boolean lessThan = y1.isLessThan(y2);
        System.out.println("y1<y2:"+lessThan);
        boolean zero = y5.isZero();
        System.out.println("y5是0:"+zero);
        // 货币类型
        boolean sameCurrency = y1.isSameCurrency(y2);
        System.out.println("y1与y2货币类型是否一致："+sameCurrency);
        boolean sameCurrency1 = y1.isSameCurrency(y6);
        System.out.println("y1与y6货币类型是否一致："+sameCurrency1);

        // 人民币转美元
        // CurrencyUnit.USD ：转换成那种类型货币
        // BigDecimal.valueOf(6.4655) 汇率
        // RoundingMode.HALF_UP 舍入模式
        Money money = y1.convertedTo(CurrencyUnit.USD, BigDecimal.valueOf(6.4655), RoundingMode.HALF_UP);
        System.out.println("y1转换美元："+money);

        // 正变负  负变正
        Money negated = y1.negated();
        System.out.println(negated);

        // Money 转 BigMoney
        // Money的大部分方法都是通过BigMoney实现的  BigMoney是Money更内核的一个类 是Money的一个属性
        BigMoney bigMoney = y1.toBigMoney();
        System.out.println(bigMoney);
        // 舍入 保留1位小数 （也就是四舍五入分）
        Money rounded = y1.rounded(1, RoundingMode.HALF_UP);
        System.out.println(rounded);

        Money money1 = y1.withAmount(BigDecimal.valueOf(100.05));
        System.out.println("用y1指定的货币类型创建一个100.05额度的金额："+money1);
        Money money2 = y1.withAmount(100.05);
        System.out.println("用y1指定的货币类型创建一个100.05额度的金额："+money2);
        Money money3 = y1.withAmount(BigDecimal.valueOf(100.05), RoundingMode.HALF_UP);
        System.out.println("指定舍入模式,用y1指定的货币类型创建一个100.05额度的金额："+money3);
        Money money4 = y1.withAmount(100.05, RoundingMode.HALF_UP);
        System.out.println("指定舍入模式,用y1指定的货币类型创建一个100.05额度的金额："+money4);


    }

}
