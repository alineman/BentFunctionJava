package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import ru.spb.samokhvalov.anf.CalculateWalsh;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User: isamokhvalov
 * Date: 13.06.15
 * Time: 17:56
 */
@Log4j
public class ZhangXiaoAlgoritm {
    public static void main(String[] args) {
        final int n = 8;
        final int k = (n >> 1);
        final int maxCount = 10;
//        List<Long> anf = Arrays.asList(128l+64, 32l + 16, 8l + 4, 2l + 1, 16l);
//        StringANF function = new StringANF(anf, n);
        long zero = System.currentTimeMillis();
        log.info("Start: n = " + n + ", k = " + k + ", maxCount = " + maxCount);
        List<List<Long>> gjb = Canteaut.generateFastGJB(n, k);
        int count = 0;
        long start = System.currentTimeMillis();
        log.info("Time to generate GJB: " + Canteaut.formatTime(start - zero) + ". Size = " + gjb.size());
        Random random = new Random(start);
        long total = 0;
        long totalBent = 0;
        while (totalBent < maxCount) {
            try {
                StringANF function = Canteaut.generateRandomStringANF(n, random);
                log.info("Start: " + new Date(System.currentTimeMillis()) + ". AnfList.size() = " + function.getAnf().size() + ". Finding bent: " + totalBent);
                total++;
                CalculateWalsh walsh = new CalculateWalsh(function);
                if (walsh.isBent()) {
                    totalBent++;
                    validateNormal(function, gjb);
                    log.info(function.getFunction());
                    log.info(function.getAllAnf());
                    log.info(function.getAnf());
                    count++;
                }
            } catch (Exception ignored) {

            }
        }
        log.info("Total time: " + Canteaut.formatTime(System.currentTimeMillis() - start) + ". All verify function: " + total + ". Bent function: " + totalBent + ". Find non-normal: " + count);
    }

    private static void validateNormal(StringANF function, List<List<Long>> gjb) {
        final int dimension = (int) function.getDimension();
        final int k = dimension >> 1;

        for (List<Long> currentGJBToValidate : gjb) {
//            List<Long> currentGJBToValidate1 = Arrays.asList(54l,10l,1l)
            List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, dimension);
            for (long a : addditionalSpace) {
                long walsh = Canteaut.calculateWalsh(a, currentGJBToValidate, function);
                if ((walsh == (1 << k)) || (walsh == -(1 << k))) {
                    log.info("Function is affine on U: " + Canteaut.getBinary(currentGJBToValidate, dimension).toString() + " + a = " + StringUtils.leftPad(Long.toBinaryString(a), (int) dimension, '0'));
//                    log.info(a);
                    throw new RuntimeException("FAIL " + currentGJBToValidate + ", a = " + a);
                } else if (walsh != 0)
                    break;


            }
        }
    }
}
