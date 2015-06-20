package ru.spb.samokhvalov.anf;

import java.util.ArrayList;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 21:21
 */
public class StringANF {
    private String function;
    private long dimension;
    List<Long> anf;
    int[] values;

    public StringANF(List<Long> anf, long n) {
        dimension = n;
        this.anf = anf;
        long max = 1 << n;
        List<Integer> tempFunction = new ArrayList<>();
        for (long i = 0; i < max; i++) {
            long res = 0;
            for (long k : anf)
                if ((i & k) == k)
                    res = res ^ 1;
            final int i1 = res != 0 ? 1 : 0;
            tempFunction.add(i1);
//            System.out.println((Long.toBinaryString(i) + " " + i1));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int number = 0; number < max / 4; number++) {
//            long result = tempFunction.get(number*4) +
//                    (tempFunction.get(number*4 + 1) << 1) +
//                    (tempFunction.get(number*4 + 2) << 2) +
//                    (tempFunction.get(number*4 + 3) << 3);
            long result = (tempFunction.get(number * 4) << 3) +
                    (tempFunction.get(number * 4 + 1) << 2) +
                    (tempFunction.get(number * 4 + 2) << 1) +
                    (tempFunction.get(number * 4 + 3));
//            System.out.println(Long.toHexString(result));
            stringBuilder.append(Long.toHexString(result));
        }
        function = stringBuilder.toString();
    }


    public StringANF(String function) {
        this.function = function;
        switch (function.length() * 4) {
            case 2:
                this.dimension = 1;
                break;
            case 4:
                this.dimension = 2;
                break;
            case 8:
                this.dimension = 3;
                break;
            case 16:
                this.dimension = 4;
                break;
            case 32:
                this.dimension = 5;
                break;
            case 64:
                this.dimension = 6;
                break;
            case 128:
                this.dimension = 7;
                break;
            case 256:
                this.dimension = 8;
                break;
            case 512:
                this.dimension = 9;
                break;
            case 1024:
                this.dimension = 10;
                break;
            case 2048:
                this.dimension = 11;
                break;
            case 4096:
                this.dimension = 12;
                break;
            case 8192:
                this.dimension = 13;
                break;
            case 16382:
                this.dimension = 14;
                break;
            case 32768:
                this.dimension = 15;
                break;
            case 65536:
                this.dimension = 16;
                break;
            default:
                throw new RuntimeException("Not supported length");
        }

    }

    public int getValue(long position) {
        return getFastValue(position);
    }

    public String getFunction() {
        return function;
    }

    public List<String> getTeXAnf() {
        List<String> result = new ArrayList<>();
        for (long l : anf) {
            StringBuilder stringBuilder = new StringBuilder();
            for (long k = dimension - 1; k >= 0; k--) {
                if (((1 << k) & l) != 0)
                    stringBuilder.append("x_{" + (dimension - k) + "}");
            }
            result.add(stringBuilder.toString());
        }

        return result;
    }

    public String getAllAnf() {
        StringBuilder result = new StringBuilder();

        for (String element : getTeXAnf()) {
            result.append(element);
            result.append(" \\oplus ");
        }
        return result.toString();
    }


    public List<Long> getAnf() {
        if (anf == null)
            anf = new ArrayList<>();
        return anf;
    }

    public long getDimension() {
        return dimension;
    }

    public void invertFunction() {
        StringBuilder builder = new StringBuilder();
        final int length = function.length();
        for (int i = 0; i < length; i++) {
            final char c = function.charAt(length - i - 1);
            char newC = 'q';
            switch (c) {
                case '0':
                    newC = '0';
                    break;
                case '1':
                    newC = '8';
                    break;
                case '2':
                    newC = '4';
                    break;
                case '3':
                    newC = 'C';
                    break;
                case '4':
                    newC = '2';
                    break;
                case '5':
                    newC = 'A';
                    break;
                case '6':
                    newC = '6';
                    break;
                case '7':
                    newC = 'E';
                    break;
                case '8':
                    newC = '1';
                    break;
                case '9':
                    newC = '9';
                    break;
                case 'a':
                case 'A':
                    newC = '5';
                    break;
                case 'b':
                case 'B':
                    newC = 'D';
                    break;
                case 'c':
                case 'C':
                    newC = '3';
                    break;
                case 'd':
                case 'D':
                    newC = 'B';
                    break;
                case 'e':
                case 'E':
                    newC = '7';
                    break;
                case 'f':
                case 'F':
                    newC = 'F';
                    break;
                default:
                    throw new IllegalArgumentException("bad symbols");
            }
            builder.append(newC);
        }
        this.function = builder.toString();
    }

    private void fillvalue() {
        if (values == null) {
            final int arraySize = 1 << (dimension - 3);
            values = new int[arraySize];
            for (int i = 0; i < arraySize; i++)
                values[i] = (Integer.parseInt(String.valueOf(function.charAt(2*i)), 16) << 4) + Integer.parseInt(String.valueOf(function.charAt(2*i + 1)), 16);
        }

    }

    public int getFastValue(long i) {
        if (values == null)
            fillvalue();
        int high = (int) i >> 3;
//        int high = (int) i / 8;
        int low = (int) i & 7;
//        int low = (int) i % 8;
        return ((values[high] & (1 << (7 - low))) != 0) ? 1 : 0;
    }

    public int getOldValue(long position){
        Long highPosition = (position) / 4;
        Long longAt = Long.parseLong(String.valueOf(function.charAt(highPosition.intValue())), 16);
        long lowPosition = (position) % 4;
        int response = (longAt & (1 << (3 - lowPosition))) != 0 ? 1 : 0;
        return response;

    }
}
