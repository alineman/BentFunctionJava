package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 19:31
 */
@Log4j
public class Canteaut {
    public static long nu(long value, long dimension) {
        for (long i = (dimension - 1); i >= 0; i--)
            if ((value & (1 << i)) != 0)
                return dimension - i;
        return 0;
    }

    public static boolean validateGJB(List<Long> vectors, long dimension) {
        long max = vectors.get(0);
        for (int k = 0; k < vectors.size() - 1; k++) {
            if (vectors.get(k + 1) >= vectors.get(k))
                return false;

        }
        for (long i : vectors) {
            for (long j : vectors) {
                if ((i != j && (j & (1 << (dimension - nu(i, dimension)))) != 0) || (j == 0)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static List<List<Long>> generateGJB(int n, int t0) {
        List<List<Long>> result = new ArrayList<>();
        CombinationGenerator generator = new CombinationGenerator(1 << n, t0);
        List<Long> testGjb;
        while (generator.hasMore()) {
            int[] var = generator.getNext();
            testGjb = new ArrayList<>();
            for (int i : var) {
//                System.out.print(" " + i);
//                testGjb.add((long) (1 << n) - 1 - i);
                testGjb.add((long) ((1 << n) - 1) ^ i);
            }
//            log.info("\n" + testGjb);
            if (Canteaut.validateGJB(testGjb, n))
                result.add(testGjb);

        }
        if (result.size() != Canteaut.countGJB(n, t0))
            throw new RuntimeException("Canteaut.countGJB bad");
        return result;
    }


    @Deprecated
    public static long mappingVectorValue(final List<Long> basis, long number, long dimension) {
        long result = 0;
        int size = basis.size();
        for (int i = 0; i < size; i++) {
            if (((1 << i) & number) != 0) {
                result = result | (1 << (dimension - (basis.get(i) - 1)));
            }
        }
        return result;
    }

    public static List<Long> makeOBasis(final List<Long> basis, long dimensions) {
        List<Long> result = new ArrayList<Long>((int) (dimensions - basis.size()));
        for (long i = 1; i <= dimensions; i++) {
            if (!basis.contains(i))
                result.add(i);
        }
        return result;
    }

    public static List<String> getBinary(List<Long> vectors, int n) {
        List<String> result = new ArrayList<>();
        for (long i : vectors)
            result.add(StringUtils.leftPad(Long.toBinaryString(i), n, '0'));
        return result;
    }

    public static List<Long> fillU(final List<Long> basis) {
        List<Long> result = new ArrayList<>();
        long dimension = 1 << basis.size();
        for (long i = 0; i < dimension; i++) {
            result.add(summaryVectors(basis, i, basis.size()));
        }
        return result;
    }

    public static List<Long> fillVariable(final List<Long> variables, long dimension) {
        List<Long> result = new ArrayList<>();

        long total = 1 << variables.size();
        for (long i = 0; i < total; i++)
            result.add(combineVectors(variables, i, dimension));

        return result;
    }

    public static long combineVectors(final List<Long> basis, long number, long dimension) {
        long result = 0;
        for (int i = 0; i < basis.size(); i++)
            if ((number & (1 << i)) != 0)
                result = result | (1 << (dimension - basis.get(i)));
        return result;
    }

    public static long summaryVectors(final List<Long> vectors, final long index, long n) {
        long result = 0;
        for (int i = 0; i < n; i++)
            if (((1 << i) & index) != 0)
                result = result ^ vectors.get(i);
        return result;
    }

    public static long countGJB(long n, long t0) {
        long upper = 1;
        long down = 1;
        for (long i = 0; i < t0; i++) {
            upper = upper * ((1 << (n - i)) - 1);
            down = down * ((1 << (t0 - i)) - 1);
        }
        return upper / down;
    }


    public static List<Long> makeAdditionalSpace(List<Long> gjBasis, int dimension) {
        if (!Canteaut.validateGJB(gjBasis, dimension))
            throw new RuntimeException("Error basis");
        List<Long> tempBasis = new ArrayList<>();

        for (long k : gjBasis)
            tempBasis.add(Canteaut.nu(k, dimension));

        return Canteaut.fillVariable(Canteaut.makeOBasis(tempBasis, dimension), dimension);
    }

    public static long getRight(long number, long dimension) {
        long result = 0;
        for (long j = 1; j <= dimension; j++) {
            int temp = 1 << (dimension - j);
            if (((temp & number) != 0) && (j > result))
                result = j;
        }
        return result;
    }

    public static long getElementOfSpace(final List<Long> basis, long number){
        long result = 0;
        for (int i = 0; i<= basis.size(); i++)
            if (((1 << i) & number) != 0)
                result = result ^ basis.get(i);

            return result;
    }

    public static long unionVectors(List<Long> vectors){
        long result = 0;
        for (long k : vectors)
            result = result | k;
        return result;
    }

    public static void addELement(Map<Long, List<Long>> longListMap, long key, long value){
        if (!longListMap.containsKey(key))
            longListMap.put(key, new ArrayList<Long>());
        longListMap.get(key).add(value);
    }

}
