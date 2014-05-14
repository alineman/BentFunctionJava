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

    public ANF(long number, int variables) {
        this.number = number;
        this.variables = variables;
    }

    public ANF() {
        log.info("Success!");
    }

    public ANF(Integer number){
        this.number = number;
        double deg = Math.ceil(Math.log(number) / Math.log(4));
        this.degree = Math.round(deg);
    }

    public void printHexNumber(){
        log.info("HexViev of number: " + Long.toHexString(number));
        log.info("degree: " + degree);
    }

    public void printDecNumber(){
        log.info("DecViev of number: " + number);
        log.info("degree: " + degree);
    }

    public long getNumber() {
        return number;
    }

    public long getDegree() {
        return degree;
    }

    public void fillTableOfTrue(){
        Long max = Long.rotateLeft(1, variables) - 1;
        tableOfTrue = new ArrayList<>();
        for (long i = 0; i <= max; i++){
            tableOfTrue.add(new TableOfTrue(i, (number & (Long.rotateLeft(1, new Long(i).intValue()))) != 0, variables));
        }
    }

    public List<TableOfTrue> getTableOfTrue() {
        return tableOfTrue;
    }
}
