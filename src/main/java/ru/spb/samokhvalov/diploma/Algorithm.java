package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.util.*;

/**
 * User: isamokhvalov
 * Date: 26.05.15
 * Time: 13:40
 */
@Log4j
public class Algorithm {
//    final static String function = "45764576aff02457ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab50514576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051";
    final static String function = "9b10218d0b4dc25ae70b7101ffe687392bf3d65fd0282382994e7c190a473679";
//    final static String function = "4576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051";

    //112047
    public static void main(String[] args) {
        final int n = 8;
        final int m = 4;
        final int t0 = 2;
        List<Long> anf = Arrays.asList(250l, 23l, 42l, 61l, 213l, 237l, 135l, 114l, 53l, 142l, 74l, 19l, 97l, 96l, 9l, 145l, 37l, 254l, 171l, 244l, 27l, 49l, 51l, 8l, 214l, 215l, 91l, 133l, 168l, 128l, 87l, 184l, 10l, 131l, 235l, 7l, 228l, 161l, 232l, 99l, 88l, 89l, 236l, 40l, 255l, 67l, 162l, 98l, 32l, 66l, 113l, 198l, 82l, 167l, 65l, 14l, 6l, 201l, 192l, 124l, 147l, 109l, 249l, 137l, 231l, 119l, 204l, 129l, 69l, 164l, 84l, 194l, 1l, 252l, 16l, 2l, 0l, 71l, 166l, 17l, 199l, 207l, 55l, 120l, 29l, 83l, 122l, 188l, 202l, 241l, 56l, 154l, 141l, 233l, 174l, 223l, 177l, 210l, 179l, 180l, 224l, 159l, 243l, 73l, 108l, 112l, 47l, 50l, 217l, 234l, 38l, 191l, 155l, 178l, 150l, 153l, 26l, 222l, 251l, 227l, 36l, 111l, 68l, 79l, 132l, 220l, 117l, 11l, 62l, 127l, 33l, 34l, 247l, 106l, 181l);
        final StringANF functionANF = new StringANF(anf, 8l);
        List<List<Long>> gjb = Canteaut.generateGJB(n, t0);

        log.info("Start");
        long start = System.currentTimeMillis();
        long total = gjb.size();
        double k = 0;
        for (List<Long> currentGJB : gjb) {
            long ut0 = currentGJB.get(t0 - 1);
            long vt0 = Canteaut.nu(ut0, n);
            if ((getJ(currentGJB, n) > vt0) && ((getJ(currentGJB, n) - vt0) <= m - vt0 + 1 + t0)) {
                List<Long> additionalSpace = Canteaut.makeAdditionalSpace(currentGJB, n);
                SimpleScr zeroC = new SimpleScr(currentGJB, function, n);
                SimpleScr oneC = new SimpleScr(currentGJB, function, n);
                for (long a : additionalSpace) {
                    long lambda = 1 << t0;
                    boolean zero = true;
                    boolean one = true;
                    for (long i = 0; i < lambda; i++) {
                        long l = a ^ Canteaut.getElementOfSpace(currentGJB, i);
                        if (functionANF.getValue(l) == 1)
                            zero = false;
                        else
                            one = false;
                    }
                    if (zero)
                        zeroC.getValue().add(a);
                    if (one)
                        oneC.getValue().add(a);
                }
                k++;
//                log.info("Complete: " + (k / total) * 100);
                combine(zeroC, oneC, currentGJB, t0, m, n);


            }

        }

        log.info(gjb.size());
        log.info("Максимально возможное: " + Canteaut.countGJB(n, m));
        final double l = (System.currentTimeMillis() - start) / 1000;
        log.info("Total time: " + l + " sec");
        log.info("Total time: " + (l / 60) + " min");

    }

    public static void combine(SimpleScr zero, SimpleScr one, List<Long> basis, long k, long m, long n) {
//        if (k < m - 1) {
        if (k < m) {
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
                    combine(zero1, one1, newBasisZero, k + 1, m, n);
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
                        log.info(basis);
                        log.info(zero.getValue());
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
