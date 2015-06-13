package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.CalculateWalsh;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

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
        final int n = 10;
        final int k = (n >> 1);
        final int maxCount = 10;
//        List<Long> anf = Arrays.asList(32l + 16, 8l + 4, 2l + 1, 8l, 16l);
//        StringANF function = new StringANF(anf, n);
        List<List<Long>> gjb = Canteaut.generateGJB(n, k);
        int count = 0;
        long start = System.currentTimeMillis();
        Random random = new Random(start);

        while (count < maxCount) {
            try {
                StringANF function = Canteaut.generateRandomStringANF(n, random);
                CalculateWalsh walsh = new CalculateWalsh(function);
                if (walsh.isBent()) {
                    validateNormal(function, gjb);
                    log.info(function.getFunction());
                    log.info(function.getAllAnf());
                    log.info(function.getAnf());
                    count++;
                }
            } catch (Exception ignored) {

            }
        }
        log.info("Total time: " + (System.currentTimeMillis() - start));
    }

    private static void validateNormal(StringANF function, List<List<Long>> gjb) {
        final int dimension = (int) function.getDimension();
        final int k = dimension >> 1;

        for (List<Long> currentGJBToValidate : gjb) {
            List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, dimension);
            for (long a : addditionalSpace) {
                long walsh = Canteaut.calculateWalsh(a, currentGJBToValidate, function);
                if ((walsh == (1 << k)) || (walsh == -(1 << k))) {
//                    log.info("Function is affine on U: " + Canteaut.getBinary(currentGJBToValidate, dimension).toString() + " + a = " + StringUtils.leftPad(Long.toBinaryString(a), (int) dimension, '0'));
//                    log.info(a);
                    throw new RuntimeException("END");
                } else if (walsh != 0)
                    break;


            }
        }
    }
}
