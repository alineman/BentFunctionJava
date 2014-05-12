package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import ru.CryptoPro.JCP.ASN.PKIXCMP._PKIXCMPValues;

/**
 * Created by ivan on 10.05.14.
 */
@Log4j
public class ANFTest {
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

}
