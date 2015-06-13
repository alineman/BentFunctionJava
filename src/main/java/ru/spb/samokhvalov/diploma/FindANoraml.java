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
@Log4j
public class FindANoraml {
//    final static String function = "4576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051";

    //112047
    public static void main(String[] args) {
//        final String function = "45764576aff02457ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab50514576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051";
        final int n = 9;
        final int m = 5;
        final int t0 = 2;
        final int maxCount = 2;
        log.info("Start: n = " + n + ", m = " + m + ", t0 = " + t0);
        List<List<Long>> gjb = Canteaut.generateGJB(n, t0);
        log.info("Calculate start gjb");
        List<List<Long>> gjbValidate = Canteaut.generateGJB(n, m);
        log.info("Calculate verify gjb");
        final long start = System.currentTimeMillis();
        Random random = new Random(start);

        int count = 0;
        long total =0;
        while (count <= maxCount) {
            StringANF functionANF = Canteaut.generateRandomStringANF(n, random);
            log.info("Start: " + System.currentTimeMillis() + ". AnfList.size() = " + functionANF.getAnf().size());

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
//                        k++;
////                log.info("Complete: " + (k / total) * 100);
//                        combine(zeroC, oneC, currentGJB, t0, m, n, functionANF.getFunction());
//
//
//                    }
//
//                }
//                succes = true;
                for (List<Long> currentGJBToValidate : gjbValidate) {
//                    long test = functionANF.getValue(currentGJBToValidate.get(0));
//                    boolean exit = false;
//                    for (long i = 0; i < (1 << m); i++)
//                        if (test != functionANF.getValue(Canteaut.getElementOfSpace(currentGJBToValidate, i))) {
//                            exit = true;
//                            break;
//                        }
//                    if (!exit)
////                        log.info("\n" + currentGJBToValidate);
//                        throw new RuntimeException(currentGJBToValidate.toString());
                    List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, n);
//            log.info(currentGJBToValidate);
                    boolean exit = false;
//            for (long a = 0; a < (1 << n); a++) {
                    for (long a : addditionalSpace) {
                        long test = functionANF.getValue(a);
                        for (long i = 0; i < (1 << m); i++) {
                            final long elementOfSpace = Canteaut.getElementOfSpace(currentGJBToValidate, i) ^ a;
                            total++;
                            if (test != functionANF.getValue(elementOfSpace)) {
                                exit = true;
                                break;
                            }
                        }
                        if (!exit) {
                            log.info("FAIL " + currentGJBToValidate + ", a = " + a);
                            throw new RuntimeException(currentGJBToValidate.toString());
                        }
                    }

                }

                count++;
                log.info(functionANF.getFunction());
                log.info(functionANF.getAnf());
                log.info("Found " + count + " of " + maxCount);
            } catch (RuntimeException e) {
//                log.info(e);
            }
        }
//        log.info("Максимально возможное: " + Canteaut.countGJB(n, m));
        final double l = (System.currentTimeMillis() - start) / (double) 1000;
        log.info("Total time: " + l + " sec");
        log.info("Total time: " + (l / 60) + " min");

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
//                        log.info(basis);
//                        log.info(zero.getValue());
                        throw new RuntimeException("Is affine");
//                            log.info("f is affine on " + Canteaut.getBinary(Arrays.asList((long) a), (int) n) + " + " + Canteaut.getBinary(toPrint, (int) n));
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
