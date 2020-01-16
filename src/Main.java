import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Main{

    public static void main(String[] args) {
        try{
            System.out.println("任务执行开始。。。");
            ExecutorService es = Executors.newFixedThreadPool(100);
            long start = System.currentTimeMillis();
            List<BigDecimal> list = new ArrayList<>(1000000);
            for (int j = 0; j < 10000000; j++) {
                MyRun myRun = new MyRun();
                es.submit(myRun);
//                Future<BigDecimal> sb = es.submit(myRun);
//                BigDecimal bigDecimal = sb.get();
//                list.add(bigDecimal);
            }
            long end = System.currentTimeMillis();
            System.out.println("任务执行结束。。。耗时："+ (end-start) +"ms");
//        System.out.println(Collections.max(list));
//        System.out.println(Collections.min(list));
            es.shutdownNow();
        }catch (Exception e){
            e.printStackTrace();
        }
        //会员列表
//        System.out.println("任务开始");
//        long s = System.currentTimeMillis();
//        //循环100次，
//        List<BigDecimal> list = new ArrayList<>();
//        for (int j = 0; j < 1000000; j++) {
//            //红包总钱数
//            BigDecimal mon = new BigDecimal("1000");
//            //红包领取范围
//            BigDecimal small = new BigDecimal("5");
//            BigDecimal big = new BigDecimal("10");
//            List<Integer> userList = new ArrayList<>();
//            for (int i = 1; i <= 100; i++) {
//                userList.add(i);
//            }
//            RedPackage redPackage = new RedPackage(userList.size(), mon);
//            Map<Integer,BigDecimal> map = new HashMap<>();
//            for (int i = 0; i <userList.size() ; i++) {
//                if (redPackage.remainMoney.compareTo(BigDecimal.ZERO) ==0 || redPackage.remainMoney.compareTo(BigDecimal.ZERO) == -1){
//                    System.out.println("没钱了1");
//                    break;
//                }
//                Result res = getRandomMoney2(userList.get(i), redPackage, small, big);
//                map.put(userList.get(i),res.getMoney());
//                if (res.getCode() == 0){
//                    break;
//                }
//            }
//            BigDecimal reduce = map.values().parallelStream().reduce(BigDecimal.ZERO, BigDecimal::add);
//            list.add(reduce);
//        }
//        long e = System.currentTimeMillis();
//        System.out.println("任务结束，耗时："+(e-s)+"ms");
//        System.out.println(Collections.max(list));
//        System.out.println(Collections.min(list));

//        for (int i = 0; i <userList.size() ; i++) {
//            getRandomMoney(userList.get(i),redPackage);
//            System.out.println("剩余红包个数："+redPackage.getRemainSize());
//            System.out.println("红包池剩余的钱："+redPackage.remainMoney);
//        }
    }

    public static Result getRandomMoney2(Integer userId, RedPackage redPackage,BigDecimal small,BigDecimal big) {
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

//    public static BigDecimal getRandomMoney(Integer userId, RedPackage redPackage) {
//
//        if (redPackage.remainSize == 1) {
//            redPackage.remainSize--;
//            System.out.println("用户"+userId+"："+redPackage.remainMoney);
//            return redPackage.remainMoney;
//        }
//        Random r = new Random();
//        BigDecimal min   = new BigDecimal("0.01");
//        BigDecimal max   = redPackage.remainMoney.divide(new BigDecimal(redPackage.remainSize),0,BigDecimal.ROUND_DOWN).multiply(new BigDecimal("2"));
//        System.out.println("平均值："+max);
//        double v = r.nextDouble();
//        BigDecimal money = new BigDecimal(v).multiply(max);
//        if (money.compareTo(min) == -1 && money.compareTo(min) == 0){
//            money = new BigDecimal("0.01");
//        }
//        money = money.setScale(2,BigDecimal.ROUND_DOWN);
//        System.out.println("用户"+userId+"："+money);
//        redPackage.remainSize--;
//        redPackage.remainMoney = redPackage.remainMoney.subtract(money);
//        return money;
//    }
}
