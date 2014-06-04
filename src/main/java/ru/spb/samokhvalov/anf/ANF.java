package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class ANF {
    private static final String oplus = "\u2295";
    private static final String cdot = "\u22c5";

    private long number;
    private long degree;
    private List<TableOfTrue> tableOfTrue;
    private int variables;
    private int[] walshW;
    private List<String> anf;
    private boolean isBent = false;
    private final String symbols = "ABCDEFGHIKLMNOPQRSTVXYZ";

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
//        log.info("degree: " + degree);
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

    public boolean validateIsBent() {
        if (walshW == null)
            fillWalshW();
        isBent = true;
        int max = 1 << (variables >> 1);
        for (int i : walshW) {
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

    public List<String> getHumanAnf(){
        if (anf == null || anf.size() == 0)
            fillANFTable();
        List<String> result = new ArrayList<>();
        for (String s:anf){
            result.add(humanView(s));
        }
        return result;
    }

    public List<String> getAnf() {
        if (anf == null || anf.size() == 0)
            fillANFTable();
        return anf;
    }

    public void fillANFTable() {
        if (tableOfTrue == null)
            fillTableOfTrue();

//        List<TableOfTrue> temp = new ArrayList<>();
        boolean[] temp = new boolean[(int) Long.rotateLeft(1, variables)];
        int i=0;
        for (TableOfTrue ofTrue : getTableOfTrue()) {
            temp[i++]=ofTrue.getValue();
        }
        boolean[] result = fillAnf(temp);
        anf = new ArrayList<>();
        for(i = 0; i < result.length; i++){
            if (result[i])
                anf.add(getTableOfTrue().get(i).getVariableString());
        }
    }

    private boolean[] fillAnf(boolean[] booleans){
        int size = booleans.length;
        int half = size >> 1;
        if (size ==2)
            return new boolean[]{booleans[0], (booleans[0] ^ booleans[1])};

        boolean[] temp1 = new boolean[half];
        boolean[] temp2 = new boolean[half];

        for (int i = 0; i<half; i++){
            temp1[i] = booleans[i];
            temp2[i] = booleans[i] ^ booleans[i+half];
        }
        temp1 = fillAnf(temp1);
        temp2 = fillAnf(temp2);

        boolean[] result = new boolean[size];

        for(int j = 0; j < half; j++){
            result[j] = temp1[j];
            result[j+half] = temp2[j];
        }

        return result;
    }

    public String humanView(String code){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < code.length(); i++){
            if (code.charAt(i)=='1')
                stringBuilder.append(symbols.charAt(i));
        }
        return stringBuilder.toString();
    }

}
