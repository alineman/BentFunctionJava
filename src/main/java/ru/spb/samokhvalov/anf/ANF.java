package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class ANF {
    private long number;
    private long degree;
    private List<TableOfTrue> tableOfTrue;
    private int variables;
    private int[] walshW;
    private boolean isBent = false;

    public ANF(long number, int variables) {
        this.number = number;
        this.variables = variables;
    }

    public ANF() {
        log.info("Success!");
    }

    @Deprecated
    public ANF(Integer number) {
        this.number = number;
        double deg = Math.ceil(Math.log(number) / Math.log(4));
        this.degree = Math.round(deg);
    }

    public void printHexNumber() {
        log.info("HexViev of number: " + Long.toHexString(number));
        log.info("degree: " + degree);
    }

    public void printDecNumber() {
        log.info("DecViev of number: " + number);
        log.info("degree: " + degree);
    }

    public long getNumber() {
        return number;
    }

    public long getDegree() {
        return degree;
    }

    public void fillTableOfTrue() {
        Long max = Long.rotateLeft(1, variables) - 1;
        tableOfTrue = new ArrayList<>();
        for (long i = 0; i <= max; i++) {
            tableOfTrue.add(new TableOfTrue(i, (number & (Long.rotateLeft(1, new Long(i).intValue()))) != 0, variables));
        }
    }

    public List<TableOfTrue> getTableOfTrue() {
        if (tableOfTrue == null)
            fillTableOfTrue();
        return tableOfTrue;
    }

    public void fillWalshW() {
        int size = 1 << variables;
        walshW = new int[size];
        for (int i = 0; i < size; i++)
            walshW[i] = ((number & (1 << i)) == 0 ? 1 : -1);
        walshW = calculateFastHadamarTransform(walshW);
    }


    public int[] getWalshW() {
        if (walshW == null)
            fillWalshW();
        return walshW;
    }

    private int[] calculateFastHadamarTransform(int[] input) {
        if (input.length == 1)
            return input;
        int half = input.length >> 1;
        int[] temp1 = new int[half];
        int[] temp2 = new int[half];
        for (int i = 0; i < half; i++) {
            int half1 = input[i];
            int half2 = input[i + half];
            temp1[i] = half1 + half2;
            temp2[i] = half1 - half2;

        }
        temp1 = calculateFastHadamarTransform(temp1);
        temp2 = calculateFastHadamarTransform(temp2);
        int[] result = new int[input.length];
        for (int i = 0; i < half; i++) {
            result[i] = temp1[i];
            result[i + half] = temp2[i];
        }

        return result;
    }

    public boolean validateIsBent(){
        if (walshW == null)
            fillWalshW();
        isBent = true;
        int max = Math.abs(walshW[0]);
        for (int i: walshW) {
            if (max != Math.abs(i)) {
                isBent = false;
                break;
            }
        }

        return isBent;
    }

    public boolean isBent() {
        if (walshW == null)
            return validateIsBent();
        return isBent;
    }
}
