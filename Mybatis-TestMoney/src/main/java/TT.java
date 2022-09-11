import org.joda.money.BigMoneyProvider;
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
public class TT {
    public static void main(String[] args) {

        /// 单位:元  精度最多小数点后2位  位数多了会报错：Scale of amount 100.022 is greater than the scale of the currency CNY
        Money cny0 = Money.of(CurrencyUnit.of("CNY"), 100.04);
        Money cny1 = Money.of(CurrencyUnit.of("CNY"), BigDecimal.valueOf(100.04));
        // Money 转 Money
        Money cny2 = Money.of(cny0);

        // 单位:元 可设置舍入模式  其实他内部就是用了Bigdecimal的几种类型 这时候就可以随便写精度了
        Money cny3 = Money.of(CurrencyUnit.of("CNY"), 100.049323, RoundingMode.HALF_UP);
        Money cny4 = Money.of(CurrencyUnit.of("CNY"), BigDecimal.valueOf(100.042344), RoundingMode.HALF_UP);
        // Money 转 Money也可以舍入
        Money cny5 = Money.of(cny0, RoundingMode.HALF_UP);

        // 以分为单位 获取Money 100 --> 1元
        Money cny6 = Money.ofMinor(CurrencyUnit.of("CNY"), 100L);
        // 以元为单位 获取Money  100.03 --> 100.03元
        Money cny = Money.ofMajor(CurrencyUnit.of("CNY"), 100);


        // cny0 == 100.04元
        // 以元为单位返回（如果有RoundingMode参数 会执行舍入操作）
        System.out.println("getAmount:"+cny0.getAmount());
        // 以最小单位（分）  返回Bigdecimal
        System.out.println("getAmountMinor:"+cny0.getAmountMinor());
        // 以最小单位（分）  返回Long
        System.out.println("getAmountMinorLong:"+cny0.getAmountMinorLong());
        // 以最小单位（分）  返回int
        System.out.println("getAmountMinorInt:"+cny0.getAmountMinorInt());

        // 以小数点后为0的方式获取Bigdecimal  也就是不要角分   单位：元
        System.out.println("getAmountMajor:"+cny0.getAmountMajor());
        // 以小数点后为0的方式获取Long 也就是不要角分 单位：元
        System.out.println("getAmountMajorLong:"+cny0.getAmountMajorLong());
        // 以小数点后为0的方式获取int  也就是不要角分  单位：元
        System.out.println("getAmountMajorInt:"+cny0.getAmountMajorInt());
        // 获取金额较小的部分 也就是抛去元 的剩余钱 单位：分
        //getAmountMajor()元+getMinorPart()分 = 总钱数
        System.out.println("getMinorPart:"+cny0.getMinorPart());

        // 获取货币类型
        System.out.println("getCurrencyUnit:"+cny0.getCurrencyUnit());

        // 运算

        Money y1 = Money.of(CurrencyUnit.of("CNY"), 100.14);
        Money y2 = Money.of(CurrencyUnit.of("CNY"), 100.35);
        Money y3 = Money.of(CurrencyUnit.of("CNY"), 100.66);
        Money y4 = Money.of(CurrencyUnit.of("CNY"), 100.14);

        // 比较y1 y4金额
        // y1 > y4 返回 1
        // y1 == y4 返回 0
        // y1 < y4 返回 -1
        // 可以看出来 这就是BigDecimal的常规操作
        System.out.println("y1.compareTo(y4)="+y1.compareTo(y4));

        // 金额相加
        Money plus = y1.plus(y2);
        System.out.println("100.14+100.35="+plus.getAmount());
        Money plus1 = y1.plus(100.09);
        System.out.println("100.14+100.09="+plus1.getAmount());
        Money plus2 = y1.plus(BigDecimal.valueOf(10.34));
        System.out.println("100.14+10.34="+plus2.getAmount());
        // 指定舍入模式
        Money plus3 = y1.plus(100.098, RoundingMode.HALF_UP);
        Money plus4 = y1.plus(BigDecimal.valueOf(100.098), RoundingMode.HALF_UP);
        System.out.println("四舍五入：100.14+100.098="+plus3.getAmount());
        System.out.println("四舍五入：100.14+100.098="+plus4.getAmount());

        // 加指定元
        Money plus5 = y1.plusMajor(100);
        System.out.println("100.14元+100元="+plus5.getAmount());
        // 加指定分
        Money plus6 = y1.plusMinor(100);
        System.out.println("100.14元+100分="+plus6.getAmount());

        List<Money> moneyList = new ArrayList<>();
        moneyList.add(y2);
        moneyList.add(y3);
        Money plus7 = y1.plus(moneyList);
        System.out.println("100.14+100.35+100.66="+plus7.getAmount());


    }

}
