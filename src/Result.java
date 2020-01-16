import java.math.BigDecimal;

/**
 * @Package: PACKAGE_NAME
 * @ClassName: Result
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: pug
 * @Date: 2020-01-15 20:56
 * @Version: 1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2016 leyou.com. All Rights Reserved</p>
 */
public class Result {
    private Integer code;
    private BigDecimal money;

    public Result(Integer code, BigDecimal money) {
        this.code = code;
        this.money = money;
    }

    public static Result ok(Integer code,BigDecimal money){
        return new Result(code,money);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
