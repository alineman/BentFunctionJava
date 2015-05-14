package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 21:21
 */
@Log4j
public class StringANF {
    private String function;
    private long dimension;

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
        Long highPosition = (position - 1) / 4;
        Long longAt = Long.parseLong(String.valueOf(function.charAt(highPosition.intValue())), 16);
        long lowPosition = (position - 1) % 4;
        int response = (longAt & (1 << (3 - lowPosition))) != 0 ? 1 : 0;
        log.debug("symbol: " + longAt + " position: " + position + " value: " + response);
        return response;
    }

}
