package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 13.06.15
 * Time: 18:36
 */
@Log4j
public class CalculateWalshTest {


    @Test
    public void test1() {
        int n = 4;
        List<Long> anf = Arrays.asList(32l + 16, 8l + 4, 2l + 1, 8l, 16l);
        StringANF function = new StringANF(anf, n);
        CalculateWalsh cW = new CalculateWalsh(function);
        log.info(cW.isBent());

    }

}
