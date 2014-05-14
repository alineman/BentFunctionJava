package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by ivan on 10.05.14.
 */
@Log4j
public class ANFTest {
    private static final long number1 = 2678982;
    private static final int variables1 = 5;
    private static final long number2 = 30856;
    private static final int variables2 = 4;

    @Test
    public void simpleConstructor(){
        new ANF();
    }

    @Test
    public void hexConstructor(){
        ANF variable = new ANF(0xff);
        variable.printHexNumber();
    }

    @Test
    public void validateDegree() {
        log.info(Math.floor(1.5));
        log.info(Math.ceil(1.5));
        for (int i = 0; i <= 255; i++) {
            ANF testValue = new ANF(i);
            log.info("i: " + i + " degree: " + Math.pow(2,testValue.getDegree()));
        }

    }

    @Test
    public void testFillTableOfTrue(){
        ANF anf = new ANF(number1,variables1);
        long resultNumber=0;
        anf.fillTableOfTrue();
        int i = 0;
        for (TableOfTrue t: anf.getTableOfTrue()){
            int value = (t.getValue()) ? 1 : 0;
            log.info(t.getVariableString() + " | " + value);
            resultNumber += value << i;
            i++;
        }
        log.info(resultNumber);
        assert number1 == resultNumber;

         anf = new ANF(number2,variables2);
        resultNumber=0;
        anf.fillTableOfTrue();
        i = 0;
        for (TableOfTrue t: anf.getTableOfTrue()){
            int value = (t.getValue()) ? 1 : 0;
            log.info(t.getVariableString() + " | " + value);
            resultNumber += value << i;
            i++;
        }
        log.info(resultNumber);
        assert number2 == resultNumber;

    }

}
