package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * User: isamokhvalov
 * Date: 20.11.14
 * Time: 11:39
 */
@Log4j
public class LinearSubDimension {

    private Map<Integer, List<Integer>> values;
    private Integer countSubDimensions;

    public LinearSubDimension(final Integer subDimensions, final Integer dimension) {
        log.debug("subDimension: " + subDimensions + " dimension: " + dimension);
        if (subDimensions > dimension)
            throw new RuntimeException("SubDimension must be greater then dimension");

        CombinationGenerator generator = new CombinationGenerator(dimension, subDimensions);
        countSubDimensions = generator.getTotal().intValue();
        log.info("countSubDimensions: " + countSubDimensions);
        values = new HashMap<>();
        while (generator.hasMore()) {
            int[] var = generator.getNext();
            int key = 0;
            HashSet<Integer> valuesSet = new HashSet<>(1 << dimension);
            for (int i = 0; i < var.length; i++) {
                key = key | (1 << (dimension - var[i] - 1));
            }
            log.debug(StringUtils.leftPad(Integer.toBinaryString(key), dimension, "0"));
            for (int i = 0; i < ((1 << dimension) - 1); i++) {
                valuesSet.add(i & key);
                log.info(StringUtils.leftPad(Integer.toBinaryString(i & key), dimension, "0"));
            }
            values.put(key, new ArrayList<>(valuesSet));
//            List<Integer> valuesList = new ArrayList<>(valuesSet.size());
//            Iterator iterator = valuesSet.iterator();
//            while (iterator.hasNext()){
//                valuesList.add((Integer) iterator.next());
//            }

        }

//        final int mask = (1 << (dimension)) - 1;
//        int[][] tempArray = new int[dimension + 1][dimension + 1];
//        tempArray[0][0] = 1;
//        for (int i = 1; i <= dimension; i++) {
//            tempArray[i][0] = 1;
//            for (int j = 1; j <= dimension; j++)
//                tempArray[i][j] = tempArray[i - 1][j - 1] + tempArray[i - 1][j];
//        }
//        countSubDimensions = tempArray[dimension][subDimensions];

//        for (int i = 1; i < dimension; i++) {
//            for (int j = i + 1; j <= dimension; j++) {
//                int tempmask = (1 << (dimension - i)) + (1 << (dimension - j));
//                log.info(StringUtils.leftPad(Integer.toBinaryString(tempmask ^ mask), dimension, "0"));
//            }
//        }
    }

    public Integer validateNormality(Integer function) {
        Set<Integer> keySet = values.keySet();
        Iterator iterator = keySet.iterator();
        boolean find = false;
        Integer result = 0;
        log.info("function: " + function);
        while (iterator.hasNext() && !find) {
            find = true;
            Integer next = (Integer) iterator.next();
            log.info("var of null " + Integer.toBinaryString(next));
            List<Integer> probe = values.get(next);
            int first = probe.get(0);
            for (Integer val : probe) {
                int intValue = function.intValue();
                log.info("first: " + first + " val: " + val + " intvalue: " + (intValue & val));
                if (first != (intValue & val))
                    find = false;
            }
            if (find)
                result = next;
        }
        return result;
    }

    public Integer getCountSubDimensions() {
        return countSubDimensions;
    }

    public Map<Integer, List<Integer>> getAllValues() {
        return values;
    }

    public List<Integer> getValue(Integer variable) {
        return values.get(variable);
    }

}
