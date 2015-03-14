package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Map;

/**
 * User: isamokhvalov
 * Date: 14.03.15
 * Time: 20:39
 */
@Log4j
public class Diplom {

    final static int degree = 4;
    static final long pow = (long) Math.pow(2, degree);

    public static void main(String[] args) {
        ANF anf = null;
        LinearSubDimension subDimension = new LinearSubDimension(degree/2, degree);
        Map<Integer, List<Integer>> allValues = subDimension.getAllValues();

        for (int i = 0; i < Math.pow(2, pow); i++) {
            anf = new ANF(i,degree);
//            log.info(i + " " + anf.getPow());
        }
    }
}
