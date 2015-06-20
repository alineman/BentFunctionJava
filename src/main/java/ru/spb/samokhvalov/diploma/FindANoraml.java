package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.util.*;

/**
 * User: isamokhvalov
 * Date: 04.06.15
 * Time: 22:47
 */
public class FindANoraml {

    public static void main(String[] args) {
        final int n = 7;
        final int m = 4;
        final int t0 = 2;
        final int maxCount = 2;
        System.out.println("Start: n = " + n + ", m = " + m + ", t0 = " + t0);
        List<List<Long>> gjb = Canteaut.generateFastGJB(n, t0);
        System.out.println("Calculate start gjb");
        long zeroTime = System.currentTimeMillis();
        List<List<Long>> gjbValidate = Canteaut.generateFastGJB(n, m);
        final long start = System.currentTimeMillis();
        System.out.println("Calculate verify gjb : " + Canteaut.formatTime(start - zeroTime));
        Random random = new Random(start);

        int count = 0;
        long total = 0;
        while (count < maxCount) {
            StringANF functionANF = Canteaut.generateRandomStringANF(n, random);
            System.out.println("Start: " + new Date(System.currentTimeMillis()) + ". AnfList.size() = " + functionANF.getAnf().size());
            total++;
            try {
//                for (List<Long> currentGJB : gjb) {
//                    long ut0 = currentGJB.get(t0 - 1);
//                    long vt0 = Canteaut.nu(ut0, n);
//                    if ((getJ(currentGJB, n) > vt0) && ((getJ(currentGJB, n) - vt0) <= m - vt0 + 1 + t0)) {
//                        List<Long> additionalSpace = Canteaut.makeAdditionalSpace(currentGJB, n);
//                        SimpleScr zeroC = new SimpleScr(currentGJB, functionANF.getFunction(), n);
//                        SimpleScr oneC = new SimpleScr(currentGJB, functionANF.getFunction(), n);
//                        for (long a : additionalSpace) {
//                            long lambda = 1 << t0;
//                            boolean zero = true;
//                            boolean one = true;
//                            for (long i = 0; i < lambda; i++) {
//                                long l = a ^ Canteaut.getElementOfSpace(currentGJB, i);
//                                if (functionANF.getValue(l) == 1)
//                                    zero = false;
//                                else
//                                    one = false;
//                            }
//                            if (zero)
//                                zeroC.getValue().add(a);
//                            if (one)
//                                oneC.getValue().add(a);
//                        }
//                        combine(zeroC, oneC, currentGJB, t0, m, n, functionANF.getFunction());
//                    }
//
//                }
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
                System.out.println(functionANF.getAnf());
                System.out.println("Found " + count + " of " + maxCount);
            } catch (RuntimeException e) {
//                System.out.println(e);
            }
        }
//        System.out.println("Максимально возможное: " + Canteaut.countGJB(n, m));
//        final double l = (System.currentTimeMillis() - start) / (double) 1000;
        final long millis = (System.currentTimeMillis() - start);

        System.out.println("Total time: " + Canteaut.formatTime(millis));
        System.out.println("Total validate functions: " + total);
//        System.out.println("Total time: " + (l / 60) + " min");

    }


    public static void combine(SimpleScr zero, SimpleScr one, List<Long> basis, long k, long m, long n, String function) {
        if (k < m - 1) {
//        if (k < m) {
            if (
                    (zero.getCapacity() < (1 << (m - k - 1)))
                            || ((zero.getCapacity() < (1 << (m - k))) && (one.getCapacity() < (1 << (m - k - 1))))
                    ) {
                zero.clear();
            }
            if (
                    (one.getCapacity() < (1 << (m - k - 1)))
                            || ((one.getCapacity() < (1 << (m - k))) && (zero.getCapacity() < (1 << (m - k - 1))))
                    ) {
                one.clear();
            }

            boolean exit = false;
            if (zero.isEmpty() && one.isEmpty()) {
//                return;
                exit = true;
            }
            if (!exit) {
                long u = Canteaut.unionVectors(basis);

                Map<Long, List<Long>> mapZero = new HashMap<>();
                Map<Long, List<Long>> mapOne = new HashMap<>();
                Canteaut.fillUpperDimension(zero, mapZero, u, n);
                Canteaut.fillUpperDimension(one, mapOne, u, n);
//            for ( Long u_k1 :mapZero.keySet() )  {
                for (Long u_k1 = zero.getLastU() - 1; u_k1 >= 0; u_k1--) {
                    List<Long> newBasisZero = new ArrayList<>(basis);
                    newBasisZero.add(u_k1);
//                if (Canteaut.validateGJB(newBasisZero, n)) {
                    List<Long> newBasisOne = new ArrayList<>(newBasisZero);
                    SimpleScr zero1 = new SimpleScr(newBasisZero, function, (int) n);
                    SimpleScr one1 = new SimpleScr(newBasisOne, function, (int) n);
                    zero1.getValue().addAll(mapZero.get(u_k1) == null ? new ArrayList<Long>() : mapZero.get(u_k1));
                    one1.getValue().addAll(mapOne.get(u_k1) == null ? new ArrayList<Long>() : mapOne.get(u_k1));
                    combine(zero1, one1, newBasisZero, k + 1, m, n, function);
//                }
                }
            }

        } else {
            Set<Long> values = new HashSet<>(zero.getValue());
            for (Long element : one.getValue())
                values.add(element);

            for (Long a : values)
                for (Long b : values)
                    if (a < b) {
                        List<Long> toPrint = new ArrayList<>(basis);
                        toPrint.add(a ^ b);
//                        if (Canteaut.validateGJB(toPrint, n))
//                        System.out.println(basis);
//                        System.out.println(zero.getValue());
                        throw new RuntimeException("Is affine");
//                            System.out.println("f is affine on " + Canteaut.getBinary(Arrays.asList((long) a), (int) n) + " + " + Canteaut.getBinary(toPrint, (int) n));
                    }
        }
    }


    public static long getJ(List<Long> basis, long n) {
        long result = 1;
        for (Long i : basis)
            if (Canteaut.getRight(i, n) > result)
                result = Canteaut.getRight(i, n);
        return result;
    }


}
