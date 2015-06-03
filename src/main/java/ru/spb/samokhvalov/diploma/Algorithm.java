package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: isamokhvalov
 * Date: 26.05.15
 * Time: 13:40
 */
@Log4j
public class Algorithm {
    final static String function = "111e111e111eeee0111e111e111eeee0111e111e111eeee0111e111e111eeee0";


    public static void main(String[] args) {
        final int n = 6;
        final int m = 3;
        final int t0 = 2;

        final StringANF functionANF = new StringANF(function);
        List<List<Long>> gjb = Canteaut.generateGJB(n, t0);


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
                combine(zeroC, oneC, currentGJB, t0, m, n);


            }

        }

        log.info(gjb.size());

    }

    public static void combine(SimpleScr zero, SimpleScr one, List<Long> basis, long k, long m, long n) {
        if (k < m - 1) {
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
            if (zero.isEmpty() || one.isEmpty()) {
                return;
            }
            long u = Canteaut.unionVectors(basis);

            Map<Long, List<Long>> mapZero = new HashMap<>();
            Map<Long, List<Long>> mapOne = new HashMap<>();


        }
    }

    public static void fillUpperDimension(SimpleScr set, Map<Long, List<Long>> longListMap, long u, long c, long n){
        long u_k = set.getLastU();
        for (long a : set.getValue())
            for (long b: set.getValue())
                if ( a < b) {
                    final long value = a ^ b;
                    if (( u & (1 << (n - Canteaut.nu(value, n)))) == 0 && (value < u_k)) {
                        Canteaut.addELement(longListMap,value,a);
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
