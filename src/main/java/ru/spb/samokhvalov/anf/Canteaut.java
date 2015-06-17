package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import ru.spb.samokhvalov.diploma.SimpleScr;

import java.util.*;

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
        return dimension + 1;
//        return 0;
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

    @Deprecated
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

    public static List<List<Long>> generateFastGJB(int n, int t0) {
        CombinationGenerator generator = new CombinationGenerator(n, t0);
        List<List<Long>> result = new ArrayList<>();
        while (generator.hasMore()) {
            int[] var1 = generator.getNext();
            int[] var = new int[var1.length];
            for (int f = 0; f < var1.length; f++)
                var[f] = var1[f] + 1;

            List<Long> basisGJB = makeSimpleBasisGJB(var, n);
            List<List<Long>> temp = getAdditionalSpaces(var, n);
            final long dimensionForGJB = getDimensionForGJB(var, n);
            for (long i = 0; i < (1 << dimensionForGJB); i++) {
                List<Long> newBasisGJB = new ArrayList<>();
                int j = 1;
                int k = 0;
//                while (j <= dimensionForGJB) {
                for (List<Long> variables : temp) {
                    long element = 0;
                    for (long add : variables) {
                        if (((1 << (dimensionForGJB - j)) & i) != 0)
                            element += (1 << (n - add));
                        j++;
                    }
                    newBasisGJB.add(element ^ basisGJB.get(k));
                    k++;
                }
//                }
                log.debug(Canteaut.getBinary(newBasisGJB, n));
                if (validateGJB(newBasisGJB, n))
                    result.add(newBasisGJB);
                else {
                    throw new RuntimeException("Founded basis is not GJB");
                }
            }
        }
        final long needCount = Canteaut.countGJB(n, t0);
        if (result.size() != needCount)
            throw new RuntimeException("Canteaut.countGJB bad: need size: " + needCount + ", but actual: " + result.size());
        return result;
    }

    public static List<Long> makeSimpleBasisGJB(final int[] nu, long dimension) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < nu.length; i++)
            result.add((long) (1 << (dimension - nu[i])));
        return result;
    }

    public static long getDimensionForGJB(final int[] nu, long dimension) {
        long result = 0;
        final int length = nu.length;
        for (int i = 0; i < (length - 1); i++)
            result += (nu[i + 1] - nu[i] - 1) * (i + 1);
        result += (dimension - nu[length - 1]) * length;
        return result;
    }

    public static List<List<Long>> getAdditionalSpaces(final int[] nu, long dimension) {
        final int length = nu.length;
        List<Integer> tempNu = new ArrayList<>();
        for (int i : nu) {
            tempNu.add(i);
        }
        List<List<Long>> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Long> temp = new ArrayList<>();
            for (long j = nu[i]; j <= dimension; j++) {
                if (!tempNu.contains((int) j))
                    temp.add(j);
            }
            result.add(temp);
        }

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

    public static long getElementOfSpace(final List<Long> basis, long number) {
        long result = 0;
        for (int i = 0; i <= basis.size(); i++)
            if (((1 << i) & number) != 0)
                result = result ^ basis.get(i);

        return result;
    }

    public static long unionVectors(List<Long> vectors) {
        long result = 0;
        for (long k : vectors)
            result = result | k;
        return result;
    }

    public static void addELement(Map<Long, List<Long>> longListMap, long key, long value) {
        if (!longListMap.containsKey(key))
            longListMap.put(key, new ArrayList<Long>());
        longListMap.get(key).add(value);
    }

    public static void fillUpperDimension(SimpleScr set, Map<Long, List<Long>> longListMap, long u, long n) {
        long u_k = set.getLastU();
        for (long a : set.getValue())
            for (long b : set.getValue())
                if (a < b) {
                    final long value = a ^ b;
                    if ((value < u_k) && ((u & (1 << (n - Canteaut.nu(value, n)))) == 0)) {
                        Canteaut.addELement(longListMap, value, a);
                    }

                }


    }

    public static long calculateWalsh(long a, List<Long> basis, StringANF function) {
        long result = 0;
        for (long i = 0; i < (1 << basis.size()); i++)
            result += (function.getValue(getElementOfSpace(basis, i) ^ a) == 0) ? 1 : -1;

        return result;
    }


    public static StringANF generateRandomStringANF(int n, Random random) {
        long anfLong = random.nextInt((1 << n) - 1);
        List<Long> anfList = new ArrayList<>();
        while (anfList.size() < anfLong) {
            long anfElement = random.nextInt((1 << n) - 1);
            if (!anfList.contains(anfElement))
                anfList.add(anfElement);
        }
        return new StringANF(anfList, n);
    }

//    private static long invertNumber(Long l){
//
//    }

    public static boolean validateConstant(List<Long> basis, long a, StringANF function) {
        boolean result = true;
        final int n = basis.size();
        final long test = function.getValue(a);
        for (int i = 0; i < (1 << n); i++)
            if (function.getValue(Canteaut.getElementOfSpace(basis, i) ^ a) != test)
                return false;
        return true;
    }

    public static String formatTime(long millis){
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
    }

    public static String makeOutput(List<Long> basis, long a, long dimension){
        return " U = " + getBinary(basis, (int) dimension).toString() + " + a = " + StringUtils.leftPad(Long.toBinaryString(a), (int) dimension, '0');
    }
}
