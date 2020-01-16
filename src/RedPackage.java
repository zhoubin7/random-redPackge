import java.math.BigDecimal;


public class RedPackage {
    // remainSize 剩余的红包数量
    // remainMoney 剩余的钱
     Integer remainSize;
     BigDecimal remainMoney;

    public RedPackage(Integer remainSize, BigDecimal remainMoney) {
        this.remainSize = remainSize;
        this.remainMoney = remainMoney;
    }

    public Integer getRemainSize() {
        return remainSize;
    }

    public void setRemainSize(Integer remainSize) {
        this.remainSize = remainSize;
    }

    public BigDecimal getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }
}
