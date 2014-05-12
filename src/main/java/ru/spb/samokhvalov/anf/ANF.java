package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class ANF {
    private Integer number;
    private long degree;

    private int variables;

    public ANF() {
        System.out.println("Success!");
    }

    public ANF(Integer number){
        this.number = number;
        double deg = Math.ceil(Math.log(number) / Math.log(4));
        this.degree = Math.round(deg);
    }

    public void printHexNumber(){
        log.info("HexViev of number: " + Integer.toHexString(number));
        log.info("degree: " + degree);
    }

    public void printDecNumber(){
        log.info("DecViev of number: " + number);
        log.info("degree: " + degree);
    }

    public Integer getNumber() {
        return number;
    }

    public long getDegree() {
        return degree;
    }


}
