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
public class TT02 {
    public static void main(String[] args) {
        Money y1 = Money.of(CurrencyUnit.of("CNY"), 100.14);
        Money y2 = Money.of(CurrencyUnit.of("CNY"), 100.35);
        Money y3 = Money.of(CurrencyUnit.of("CNY"), 100.66);

        // y1 - y2
        Money minus = y1.minus(y2);
        System.out.println("y1-y2="+minus.getAmount());
        // 求绝对值
        Money abs = minus.abs();
        System.out.println("minus="+minus.getAmount()+",minus.abs()="+abs.getAmount());
        //
        Money minus1 = y1.minus(100.01);
        System.out.println("y1-100.01="+minus1.getAmount());
        Money minus2 = y1.minus(BigDecimal.valueOf(100.02));
        System.out.println("y1-100.02="+minus2.getAmount());
        // 精度超出的情况 都要指定舍入模式
        Money minus3 = y1.minus(100.016, RoundingMode.HALF_UP);
        System.out.println("y1-100.100.016="+minus3.getAmount());
        Money minus4 = y1.minus(BigDecimal.valueOf(100.028), RoundingMode.HALF_UP);
        System.out.println("y1-100.100.100.028="+minus3.getAmount());

        // y1 - 10元
        Money minus5 = y1.minusMajor(10);
        System.out.println("y1元-10元="+minus5.getAmount());
        // y1 = 10分
        Money minus6 = y1.minusMinor(10);
        System.out.println("y1元-10分="+minus6.getAmount());

        List<Money> monies = new ArrayList<>();
        monies.add(Money.of(CurrencyUnit.of("CNY"), 10));
        monies.add(Money.of(CurrencyUnit.of("CNY"), 10.4));
        Money minus7 = y1.minus(monies);
        System.out.println("y1-10-10.4="+minus7.getAmount());

    }

}
