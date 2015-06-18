package ru.spb.samokhvalov.diploma;

import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.util.*;

/**
 * User: isamokhvalov
 * Date: 18.06.15
 * Time: 22:42
 */
public class SimpleAlgorithm {

    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
//        final int n = 7;
        final int k = Integer.parseInt(args[1]);
//        final int k = 4;
        final int maxCount = Integer.parseInt(args[2]);
//        final int maxCount = 3;
        List<Integer> anfNormal = new ArrayList<>();
        List<Integer> anfANormal = new ArrayList<>();
        System.out.println("Start: n = " + n + ", m = " + k);
        long zeroTime = System.currentTimeMillis();
        List<List<Long>> gjbValidate = Canteaut.generateFastGJB(n, k);
        final long start = System.currentTimeMillis();
        System.out.println("Calculate verify gjb : " + Canteaut.formatTime(start - zeroTime));
        Random random = new Random(start);

        int count = 0;
        long total = 0;
        while (count < maxCount) {
            StringANF functionANF = Canteaut.generateRandomStringANF(n, random);
//            System.out.println("Start: " + new Date(System.currentTimeMillis()) + ". AnfList.size() = " + functionANF.getAnf().size());
            total++;
            try {
                for (List<Long> currentGJBToValidate : gjbValidate) {
                    List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, n);
//            for (long a = 0; a < (1 << n); a++) {
                    for (long a : addditionalSpace) {
                        if (Canteaut.validateConstant(currentGJBToValidate, a, functionANF)) {
//                            System.out.println("FAIL " + Canteaut.makeOutput(currentGJBToValidate, a, n));
                            throw new RuntimeException(currentGJBToValidate.toString());
                        }

                    }

                }

                count++;
                System.out.println(functionANF.getFunction().toUpperCase());
                anfANormal.add(functionANF.getAnf().size());
//                System.out.println(functionANF.getAnf());
//                System.out.println("Found " + count + " of " + maxCount);
            } catch (RuntimeException e) {
//                System.out.println(e);
                anfNormal.add(functionANF.getAnf().size());
            }
        }
        final long millis = (System.currentTimeMillis() - start);

        System.out.println("Total time: " + Canteaut.formatTime(millis));
        System.out.println("Total find/validate functions: " + count + "/" + total);
        System.out.println("ANormal array: " + anfANormal);
        System.out.println("Normal array: " + anfNormal);

    }

}
