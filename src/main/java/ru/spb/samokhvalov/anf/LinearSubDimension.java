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
    private Map<Integer, Integer> newValues;

    private Integer dimension;

    public LinearSubDimension(final Integer subDimensions, final Integer dimension) {
        log.debug("subDimension: " + subDimensions + " dimension: " + dimension);
        if (subDimensions > dimension)
            throw new RuntimeException("SubDimension must be greater then dimension");

        setDimension(dimension);
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
            log.debug("\nkey: " + StringUtils.leftPad(Integer.toBinaryString(key), dimension, "0"));

            for (int i = 0; i < (1 << dimension); i++) {
//                int added = -1;
//                int[] valueOfOne = new int[var.length];

//                for (int j=0; j< var.length; j++){
//
//                    int i1 = 1 << (dimension - var[j] - 1);
////                    valueOfOne[i1] =
//                    log.info(Integer.toBinaryString(i & i1));
//                    log.debug("degrees: " + i1);
//                }
                int iKey = i & key;
                if (((Integer.bitCount(iKey) % 2) != 0) && ((Integer.bitCount(iKey) > 0)))
                    valuesSet.add(1 << i);
                log.debug("i:" + i + " " + StringUtils.leftPad(Integer.toBinaryString(iKey), dimension, "0"));
            }
            values.put(key, new ArrayList<>(valuesSet));

//            int newKey = ((1<<dimension)-1) ^ key;
//            for (int i = 0; i < ((1 << dimension) - 1); i++) {
//                valuesSet.add(i ^ newKey);
//                log.debug(StringUtils.leftPad(Integer.toBinaryString(i ^ newKey), dimension, "0"));
//            }
//            values.put(newKey, new ArrayList<>(valuesSet));

//            List<Integer> valuesList = new ArrayList<>(valuesSet.size());
//            Iterator iterator = valuesSet.iterator();
//            while (iterator.hasNext()){
//                valuesList.add((Integer) iterator.next());
//            }

        }
        generateNewValues();

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
        log.debug("function: " + function);
        while (iterator.hasNext() && !find) {
            find = true;
            Integer next = (Integer) iterator.next();
            log.debug("var of null " + Integer.toBinaryString(next));
            List<Integer> probe = values.get(next);
            int first = probe.get(0);
            for (Integer val : probe) {
                int intValue = function.intValue();
                log.debug("first: " + first + " val: " + val + " intvalue: " + (intValue & val));
                if (first != (intValue & val))
                    find = false;
            }
            if (find)
                result = next;
        }
        return result;
    }

    public Integer validateNormality1(Integer function) {
        Set<Integer> keySet = newValues.keySet();
        Iterator iterator = keySet.iterator();
        boolean find = false;
        Integer result = 0;
        log.debug("function: " + function + " " + Integer.toBinaryString(function));
        while (iterator.hasNext() && !find) {
            find = false;
            Integer next = (Integer) iterator.next();
            log.debug("var of null " + Integer.toBinaryString(next));
            Integer probe = newValues.get(next);
            int probeFunction = probe & function;
//            log.info(Integer.bitCount(probeFunction));
//            if ((probeFunction == 0) || (Integer.bitCount(probeFunction) == Integer.bitCount(probe))) {
            if ((probeFunction == probe) || (probeFunction == 0)) {
//            if (Integer.bitCount(probeFunction) == 0)
                find = true;
                log.info("probe: " + probe + " "+Integer.toBinaryString(probeFunction));
            }
//            int first = probe.get(0);
//            for (Integer val : probe) {
//                int intValue = function.intValue();
//                log.debug("first: " + first + " val: " + val + " intvalue: " + (intValue & val));
//                if (first != (intValue & val))
//                    find = false;
//            }
            if (find)
                result = next;
        }
        return result;
    }

    private void generateNewValues() {
        if (newValues == null)
            newValues = new HashMap<>();
        Set<Integer> keySet = values.keySet();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            List<Integer> value = values.get(next);
            int add = 0;
            for (Integer i : value)
                add += i;
            newValues.put(next, add);
            newValues.put(-next, ((1 << (1 << dimension)) - 1) ^ add);

        }


    }

    public Integer getCountSubDimensions() {
        return countSubDimensions;
    }

    public Map<Integer, List<Integer>> getAllValues() {
        return values;
    }

    public Map<Integer, Integer> getNewValues() {
        return newValues;
    }

    public List<Integer> getValue(Integer variable) {
        return values.get(variable);
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

}
