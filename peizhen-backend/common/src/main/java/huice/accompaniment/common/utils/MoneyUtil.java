package huice.accompaniment.common.utils;

import java.math.BigDecimal;

/**
 * @author yangyida
 * @since  2024-6-20l
 * 金钱转换工具类
 */
public class MoneyUtil {

    private static final BigDecimal HUNDRED = new BigDecimal(100);

    /**
     * 将金额从分转换为元
     *
     * @param amountInCents 以分为单位的金额
     * @return 以元为单位的金额
     */
    public static BigDecimal centsToYuan(int amountInCents) {
        return new BigDecimal(amountInCents).divide(HUNDRED, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将金额从元转换为分
     *
     * @param amountInYuan 以元为单位的金额
     * @return 以分为单位的金额
     */
    public static int yuanToCents(BigDecimal amountInYuan) {
        return amountInYuan.multiply(HUNDRED).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }
}
