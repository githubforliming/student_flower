package vip.freeedu;

/**
 * @author 木子的昼夜编程
 * 女娲造人
 */
public class NvWa {
    // 记录总共造了几个人
    private static int count;
    // 记录自己造的人数
    private  int myCount;
    // 造人方法 每次造一个人 就让总记录+1
    public void createPerson(){
        new Person();
        count++;
        myCount++;
    }

    public static void main(String[] args) {

        NvWa nvWa01 = new NvWa();
        // 开始造人
        for (int i = 0; i < 10; i++) {
            nvWa01.createPerson();
        }
        NvWa nvWa02 = new NvWa();
        // 开始造人
        for (int i = 0; i < 10; i++) {
            nvWa02.createPerson();
        }
        System.out.println("女娲1号造人："+nvWa01.myCount);
        System.out.println("女娲2号造人："+nvWa02.myCount);
        System.out.println("总人数："+ NvWa.count);

        System.out.println("总人数："+ nvWa01.count);
    }
}
// 人
class Person{
    {
        System.out.println("造人+1");
    }
}
