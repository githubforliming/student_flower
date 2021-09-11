package entity;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-30 21:47
 */
public class Mobile {
    private Long pId;
    private String mobile;
    private String isp;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "pId=" + pId +
                ", mobile='" + mobile + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}
