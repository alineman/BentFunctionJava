package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

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
                if (i != j && (j & (1 << (dimension - nu(i, dimension)))) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static long mappingVectorValue(final List<Long> basis, long number){
        long result = 0;
        int size = basis.size();
        for (int i =0; i < size; i++){
            if (((1<<i) & number) != 0){
                result = result | (1 << (basis.get(i) - 1));
            }
        }
        return result;
    }

    public static List<Long> makeOBasis(final List<Long> basis, long dimensions){
        List<Long> result = new ArrayList<Long>((int) (dimensions - basis.size()));
        for(long i = 1; i <= dimensions; i++){
            if (!basis.contains(i))
                result.add(i);
        }
        return result;
    }
}
