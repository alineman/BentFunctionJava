package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 21:30
 */
@Log4j
public class StringANFTest {

    @Test
    public void testConstructor(){
        new StringANF("FFFF");
    }

    @Test
    public void testGetValue(){
        StringANF example = new StringANF("FFFF");
        log.info(example.getValue(10l));
    }
}
