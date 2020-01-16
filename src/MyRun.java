import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * @Package: PACKAGE_NAME
 * @ClassName: MyRun
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: pug
 * @Date: 2020-01-15 22:03
 * @Version: 1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2016 leyou.com. All Rights Reserved</p>
 */
public class MyRun implements Callable<BigDecimal> {



    @Override
    public BigDecimal call() {
//        System.out.println(Thread.currentThread().getName()+"启动");
        //会员列表
        List<Integer> userList = new ArrayList<>();
        //红包总钱数
        BigDecimal mon = new BigDecimal("1000");
        //红包领取范围
        BigDecimal small = new BigDecimal("5");
        BigDecimal big = new BigDecimal("10");
        Map<Integer,BigDecimal> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            userList.add(i);
        }
        RedPackage redPackage = new RedPackage(userList.size(), mon);
        for (int i = 0; i <userList.size() ; i++) {
            if (redPackage.remainMoney.compareTo(BigDecimal.ZERO) ==0 || redPackage.remainMoney.compareTo(BigDecimal.ZERO) == -1){
                System.out.println("没钱了1");
                break;
            }
            Result res = getRandomMoney2(userList.get(i), redPackage, small, big);
            map.put(userList.get(i),res.getMoney());
            if (res.getCode() == 0){
                break;
            }
        }
        BigDecimal reduce = map.values().parallelStream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return reduce;
    }
    public static Result getRandomMoney2(Integer userId, RedPackage redPackage, BigDecimal small, BigDecimal big) {
        if (redPackage.remainSize == 1) {
            redPackage.remainSize--;
            if (redPackage.remainMoney.compareTo(big) ==1){
                Random ban = new Random();
                double v = ban.nextDouble();
                BigDecimal money = small.add(big.subtract(small).multiply(new BigDecimal(v))).setScale(2,BigDecimal.ROUND_DOWN);
                return Result.ok(0,money);
            }else{
                return Result.ok(1,redPackage.remainMoney);
            }
        }
        Random r = new Random();
        BigDecimal min   = new BigDecimal("0.01");
        double v = r.nextDouble();
        BigDecimal num = big.subtract(small);
        BigDecimal money = small.add(num.multiply(new BigDecimal(v)));
        if (money.compareTo(min) == -1 && money.compareTo(min) == 0){
            money = new BigDecimal("0.01");
        }
        money = money.setScale(2,BigDecimal.ROUND_DOWN);
        redPackage.remainSize--;
        BigDecimal sb = redPackage.remainMoney.subtract(money);
        if (sb.compareTo(BigDecimal.ZERO) == -1){
            return Result.ok(0,redPackage.remainMoney);
        }else {
            redPackage.remainMoney = sb;
            return Result.ok(1,money);
        }

    }
}
