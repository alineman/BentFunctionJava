package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ivan on 10.05.14.
 */
@Log4j
public class ANFTest {
    private ANF anfNumber1;
    private static final long number1 = 2678982;
    private static final int variables1 = 5;
    private int[] walshNumber1 = {
            14, 6, 6, 6,
            6, -2, -2, -2,
            -6, 2, 2, 2,
            -6, 2, 2, 2,
            -10, -2, 6, 6,
            6, -2, -10, 6,
            2, -6, 2, 2,
            -6, 2, -6, 10
    };

    private ANF anfNumber2;
    private static final long number2 = 30856;
    private static final int variables2 = 4;
    private int[] walshNumber2 = {
            4, 4, 4, -4,
            4, 4, 4, -4,
            4, 4, 4, -4,
            -4, -4, -4, 4
    };

    private static final String oplus = "\u2295";
    private static final String cdot = "\u22c5";
    @Before
    public void prepareVariables() {
        anfNumber2 = new ANF(number2, variables2);
        anfNumber1 = new ANF(number1, variables1);
        sortArray(walshNumber1);
        sortArray(walshNumber2);

    }

    @Test
    public void simpleConstructor() {
        new ANF();
    }

    @Test
    public void hexConstructor() {
        ANF variable = new ANF(0xff);
        variable.printHexNumber();
    }

    @Test
    public void validateDegree() {
        log.info(Math.floor(1.5));
        log.info(Math.ceil(1.5));
        for (int i = 0; i <= 255; i++) {
            ANF testValue = new ANF(i);
            log.info("i: " + i + " degree: " + Math.pow(2, testValue.getDegree()));
        }

    }

    @Test
    public void testFillTableOfTrue() {
        long resultNumber = 0;
        anfNumber1.fillTableOfTrue();
        int i = 0;
        for (TableOfTrue t : anfNumber1.getTableOfTrue()) {
            int value = (t.getValue()) ? 1 : 0;
            log.info(t.getVariableString() + " | " + value);
            resultNumber += value << i;
            i++;
        }
        log.info(resultNumber);
        assert number1 == resultNumber;

        resultNumber = 0;
        anfNumber2.fillTableOfTrue();
        i = 0;
        for (TableOfTrue t : anfNumber2.getTableOfTrue()) {
            int value = (t.getValue()) ? 1 : 0;
            log.info(t.getVariableString() + " | " + value);
            resultNumber += value << i;
            i++;
        }
        log.info(resultNumber);
        assert number2 == resultNumber;

    }

    @Test
    public void testFillWalshW() {
        int length1 = anfNumber1.getWalshW().length;
        int length2 = anfNumber2.getWalshW().length;
        int[] walshW1 = new int[length1];
        int[] walshW2 = new int[length2];
        System.arraycopy(anfNumber1.getWalshW(), 0, walshW1, 0, length1);
        System.arraycopy(anfNumber2.getWalshW(), 0, walshW2, 0, length2);
        sortArray(walshW1);
        sortArray(walshW2);
        for (int i = 0; i < length1; i++) {
            assert walshW1[i] == walshNumber1[i];
        }
        for (int i = 0; i < length2; i++) {
            assert walshW2[i] == walshNumber2[i];
        }
    }

    @Test
    public void testValidateIsBent(){
        assert !anfNumber1.isBent();
        assert  anfNumber2.isBent();
        ANF wrongAnf = new ANF(60811, 4);
        assert wrongAnf.isBent();

//        ANF simpleAnf = new ANF((60811l<<16)+30856l,8);
        ANF simpleAnf = new ANF(235,4); // 1837826048
        log.info(Integer.toBinaryString(60811));
        log.info(Integer.toBinaryString(30856));
//        assert simpleAnf.isBent();
    }

    private void sortArray(int[] input) {
        for (int i = input.length - 1; i >= 2; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
