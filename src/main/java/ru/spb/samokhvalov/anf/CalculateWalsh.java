package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

/**
 * User: isamokhvalov
 * Date: 13.06.15
 * Time: 18:33
 */
@Log4j
public class CalculateWalsh {
    private int[] walshW;
    private long variables;
    Boolean bent;

    StringANF function;


    public CalculateWalsh(StringANF function) {
        this.function = function;
        this.variables = function.getDimension();
        fillWalshW();
    }

    public void fillWalshW() {
        int size = 1 << variables;
        walshW = new int[size];
        for (int i = 0; i < size; i++)
            walshW[i] = (function.getValue(i) == 0 ? 1 : -1);
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

    public Boolean isBent() {
        if (bent == null)
            validateBent();
        return bent;
    }

    private void validateBent(){
        long maxWalsh = (1 << (variables >> 1 ));
        for (int w:walshW)
            if ((w != maxWalsh) && (w != -maxWalsh)){
            bent = false;
            break;
        }

        if (bent == null)
            bent = true;
    }


}
