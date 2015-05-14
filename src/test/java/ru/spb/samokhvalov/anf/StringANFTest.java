package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 21:30
 */
@Log4j
public class StringANFTest {

    @Test
    public void testConstructor() {
        new StringANF("FFFF");
    }

    @Test
    public void testGetValue() {
        StringANF example = new StringANF("FFFF");
//        log.info(example.getValue(10l));
        example = new StringANF("FA84");
        Assert.assertEquals(1, example.getValue(1));
        Assert.assertEquals(1, example.getValue(2));
        Assert.assertEquals(1, example.getValue(3));
        Assert.assertEquals(1, example.getValue(4));

        Assert.assertEquals(1, example.getValue(5));
        Assert.assertEquals(0, example.getValue(6));
        Assert.assertEquals(1, example.getValue(7));
        Assert.assertEquals(0, example.getValue(8));

        Assert.assertEquals(1, example.getValue(9));
        Assert.assertEquals(0, example.getValue(10));
        Assert.assertEquals(0, example.getValue(11));
        Assert.assertEquals(0, example.getValue(12));

        Assert.assertEquals(0, example.getValue(13));
        Assert.assertEquals(1, example.getValue(14));
        Assert.assertEquals(0, example.getValue(15));
        Assert.assertEquals(0, example.getValue(16));

        example = new StringANF("ad02e643");
        Assert.assertEquals(1, example.getValue(1));
        Assert.assertEquals(0, example.getValue(2));
        Assert.assertEquals(1, example.getValue(3));
        Assert.assertEquals(0, example.getValue(4));

        Assert.assertEquals(1, example.getValue(5));
        Assert.assertEquals(1, example.getValue(6));
        Assert.assertEquals(0, example.getValue(7));
        Assert.assertEquals(1, example.getValue(8));

        Assert.assertEquals(0, example.getValue(9));
        Assert.assertEquals(0, example.getValue(10));
        Assert.assertEquals(0, example.getValue(11));
        Assert.assertEquals(0, example.getValue(12));

        Assert.assertEquals(0, example.getValue(13));
        Assert.assertEquals(0, example.getValue(14));
        Assert.assertEquals(1, example.getValue(15));
        Assert.assertEquals(0, example.getValue(16));

        Assert.assertEquals(1, example.getValue(17));
        Assert.assertEquals(1, example.getValue(18));
        Assert.assertEquals(1, example.getValue(19));
        Assert.assertEquals(0, example.getValue(20));

        Assert.assertEquals(0, example.getValue(21));
        Assert.assertEquals(1, example.getValue(22));
        Assert.assertEquals(1, example.getValue(23));
        Assert.assertEquals(0, example.getValue(24));

        Assert.assertEquals(0, example.getValue(25));
        Assert.assertEquals(1, example.getValue(26));
        Assert.assertEquals(0, example.getValue(27));
        Assert.assertEquals(0, example.getValue(28));

        Assert.assertEquals(0, example.getValue(29));
        Assert.assertEquals(0, example.getValue(30));
        Assert.assertEquals(1, example.getValue(31));
        Assert.assertEquals(1, example.getValue(32));

    }


    @Test
    public void countLinear() {
        StringANF anf = new StringANF("6996");
        for (int i = 1; i <= 16; i++)
            System.out.print(anf.getValue(i));
    }

}
