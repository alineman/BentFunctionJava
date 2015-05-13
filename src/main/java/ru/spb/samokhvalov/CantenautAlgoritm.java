package ru.spb.samokhvalov;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.CombinationGenerator;
import ru.spb.samokhvalov.anf.ScrClass;

import java.util.ArrayList;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 13.05.15
 * Time: 20:43
 */
@Log4j
public class CantenautAlgoritm {

    public static void main(String... args) {
        final int n = 5;
        final int m = 3;
        final int t0 = 2;
        final String function = "ffffff";

        CombinationGenerator generator = new CombinationGenerator(n ,m);
        while (generator.hasMore()){
            int[] var = generator.getNext();

        }


    }

    public static void combine(ScrClass zero, ScrClass one, List<Long> basis, long k, long m, long n) {
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
//                break; // Exit
                log.info("Break");

            }

            long u = 0;
            for (long i : basis) {
                u = u | i;
            }
            log.info("hat{u} = " + u);
            List<Long> tempBasis = zero.getBody();
            tempBasis.addAll(one.getBody());
            long u_k = basis.get(basis.size() - 1);
            combineSameField(u_k, n, u, tempBasis, zero);
            combineSameField(u_k, n, u, tempBasis, one);
            for (long u_k1 = u_k - 1; u_k1 >= 0; u_k1--) {
                List<Long> updateBasis = new ArrayList<>(basis);
                updateBasis.add(u_k1);
                combine(zero, one, updateBasis, k + 1, m, n);
            }


        } else {
            List<Long> unionBody = one.getBody();
            unionBody.addAll(zero.getBody());
            for (long a : unionBody)
                for (long b : unionBody)
                    if (a < b)
                        log.info("f is affine on " + a + " + " + basis + " " + (a ^ b));
        }
    }

    private static void combineSameField(long u_k, long n, long u, List<Long> tempBasis, ScrClass scrClass) {
        for (long a : tempBasis)
            for (long b : tempBasis)
                if (a < b) {
                    if (
                            (u & (1 << (n - Canteaut.nu(a ^ b, n) + 1))) == 0 &&
                                    ((a ^ b) < u_k)
                            ) {
                        scrClass = new ScrClass(scrClass, (a ^ b));
                    }
                }
    }

    public static ScrClass CByIndex(int index, ScrClass zero, ScrClass one) {
        if (index == 0)
            return zero;
        return one;
    }
}
