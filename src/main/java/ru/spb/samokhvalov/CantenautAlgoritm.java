package ru.spb.samokhvalov;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.ScrClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 13.05.15
 * Time: 20:43
 */
@Log4j
public class CantenautAlgoritm {
//        final static String function = "6996";
//    final static String function = "030356ca";
//    final static String function = "111e111e111eeee0111e111e111eeee0111e111e111eeee0111e111e111eeee0";
//    final static String function = "4576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab50514576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051";
    final static String function = "bc366c66699c06e23d736d222f8fbf116679299366871044663d29e021848ad53c2769726982cf24f29d94ddeb6fd46bcc9dd776c3a92d4a02e8191c4165276a";
//Total time: 93 sec
    public static void main(String... args) {
        final int n = 9;
        final int m = 4;
        final int t0 = 2;
//    final String function = "6996";
//        final String function = "030356c9";
//                "faff" +
//                "1f0f";f2df" +
//                "4f0fffff1f0f42df" +
//                "900f8fff1f0ff2df4f0fffff1f0fa2dfad43";
        List<List<Long>> gjb = Canteaut.generateGJB(n, t0);
        long total = gjb.size();
        double k =0;
        long start = System.currentTimeMillis();
        log.info(gjb.size());
        for (List<Long> currentGJB : gjb) {
            long ut0 = currentGJB.get(t0 - 1);
            long vt0 = Canteaut.nu(ut0, n);
            k = k+1;
            if ((getJ(currentGJB, n) > vt0) && ((getJ(currentGJB, n) - vt0) <= m - vt0 + 1 + t0)) {
//                log.info(currentGJB);
                ScrClass zero = new ScrClass(currentGJB, function, 0, n);
                ScrClass one = new ScrClass(currentGJB, function, 1, n);
                log.info("Complete: " + (k / total)*100);
                combine(zero, one, currentGJB, t0, m, n);
            }

        }

        log.info(gjb.size());
        final double l = (System.currentTimeMillis() - start)/1000;
        log.info("Total time: " + l + " sec");
        log.info("Total time: " + (l/60) + " min");

    }

    public static long getJ(List<Long> basis, long n) {
        long result = 1;
        for (Long i : basis)
            if (Canteaut.getRight(i, n) > result)
                result = Canteaut.getRight(i, n);
        return result;
    }


    public static void combine(ScrClass zero, ScrClass one, List<Long> basis, long k, long m, long n) {
//        log.info("call combine");
//        if (k < m - 1) {
        if (k < m ) {
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
                exit = true;
                log.info("Break");

            }

            if (!exit) {
                long u = 0;
                for (long i : basis) {
                    u = u | i;
                }
                log.info("hat{u} = " + u);
//                List<Long> tempBasis = zero.getBody();
//                tempBasis.addAll(one.getBody());
                long u_k = basis.get(basis.size() - 1);
//                combineSameField(u_k, n, u, tempBasis, zero);
                combineSameField(u_k, n, u, zero.getBody(), zero);
                combineSameField(u_k, n, u, one.getBody(), one);
//                combineSameField(u_k, n, u, tempBasis, one);
                for (long u_k1 = u_k - 1; u_k1 >= 0; u_k1--) {
                    List<Long> updateBasis = new ArrayList<>(basis);
                    updateBasis.add(u_k1);
                    if (Canteaut.validateGJB(updateBasis, n))
                        combine(zero, one, updateBasis, k + 1, m, n);
                }

            }
        } else {
            List<Long> unionBody = one.getBody();
//            unionBody.addAll(zero.getBody());
            for (Long i : zero.getBody())
                if (!unionBody.contains(i))
                    unionBody.add(i);
//            log.info("Print");
            for (long a : unionBody)
                for (long b : unionBody)
                    if (a < b) {
//                        log.info("f is affine on " + Canteaut.getBinary(Arrays.asList((long) a), (int) n) + " + " + Canteaut.getBinary(basis, (int) n) + ", " + Canteaut.getBinary(Arrays.asList((long) (a ^ b)), (int) n));
//                        log.info("f is affine on " + a + " + " + basis + ", " + (a ^ b));
//                        log.info("a: " + a + " b: " + b);
                        List<Long> example = new ArrayList<>(basis);
                        example.add((long) (a ^ b));
                        final List<Long> vectors = Canteaut.fillU(example);
//                        long[] sorted = new long[vectors.size()];
//                        for (int i =0; i < vectors.size(); i++)
//                            sorted[i] = vectors.get(i);
//                        Arrays.sort(sorted);
//                        List<Long> testsss = new ArrayList<>();
//                        for (int i =0; i < vectors.size(); i++)
//                            testsss.add(sorted[i]);
//                        if (Canteaut.validateGJB(example, 9)){
                        log.info("f is affine on " + Canteaut.getBinary(Arrays.asList((long) a), (int) n) + " + " + Canteaut.getBinary(example, (int) n));
//                        log.info("f is affine on " + a + " + " + example);
//                        throw new RuntimeException();         }
//                        StringANF func =  new StringANF(function);
//                        for (long i : vectors){
//                            System.out.print(func.getValue(i ^ a));
//                        }
//                        log.info("");
                    }
        }
    }

    private static void combineSameField(long u_k, long n, long u, List<Long> tempBasis, ScrClass scrClass) {
        List<Long> basicGJ = new ArrayList<>();
        List<Long> body = new ArrayList<>();
        for (long a : tempBasis)
            for (long b : tempBasis)
                if (a < b) {
//                    log.info((n - Canteaut.nu(a ^ b, n) + 1));
                    if (
                            (u & (1 << (n - Canteaut.nu(a ^ b, n)))) == 0 &&
                                    ((a ^ b) < u_k)
                            ) {
                        List<Long> validate = new ArrayList<>(scrClass.getBasisGJ());
                        validate.add((a ^ b));
                        if (Canteaut.validateGJB(validate, n)){
                            basicGJ.add(a ^ b);
                            body.add(a);
                        }
//                            scrClass = new ScrClass(scrClass, (a ^ b));
                    }
                }
        scrClass.getBasisGJ().addAll(basicGJ);
        scrClass.getBody().addAll(body);
    }

    public static ScrClass CByIndex(int index, ScrClass zero, ScrClass one) {
        if (index == 0)
            return zero;
        return one;
    }
}
