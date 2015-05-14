package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 13.05.15
 * Time: 16:52
 */
@Log4j
public class ScrClassTest {

    @Test
    public void testConstructor() {
        List<Long> uBasis = Arrays.asList(2l, 1l);
        ScrClass scrClass = new ScrClass(uBasis, "ff", 1, 3);
        Assert.assertArrayEquals(Arrays.asList(0l, 1l).toArray(), scrClass.getBody().toArray());

        scrClass = new ScrClass(uBasis, "cccc", 1, 4);
        Assert.assertArrayEquals(Arrays.asList(0l, 1l).toArray(), scrClass.getBody().toArray());


        uBasis = Arrays.asList(8l, 1l);
        scrClass = new ScrClass(uBasis, "0c0c", 1, 4);
        log.info(scrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(4l).toArray(), scrClass.getBody().toArray());

        scrClass = new ScrClass(uBasis, "3030", 1, 4);
        log.info(scrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(2l).toArray(), scrClass.getBody().toArray());
    }

    @Test
    public void simpleTest() {
        List<Long> uBasis = Arrays.asList(4l);
        ScrClass scrClass = new ScrClass(uBasis, "ffff", 1, 4);
        log.info(scrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(0l, 1l, 4l, 5l, 8l, 9l, 12l, 13l).toArray(), scrClass.getBody().toArray());
    }

    @Test
    public void validateComplexConstructor() {
        List<Long> uBasis = Arrays.asList(8l);
        ScrClass scrClass = new ScrClass(uBasis, "0c0c", 1, 4);
        log.info(scrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(4l, 12l).toArray(), scrClass.getBody().toArray());

        ScrClass newScrClass = new ScrClass(scrClass, 1l);
        log.info(newScrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(4l).toArray(), newScrClass.getBody().toArray());

    }

    @Test
    public void testClear() {
        List<Long> uBasis = Arrays.asList(8l);
        ScrClass scrClass = new ScrClass(uBasis, "0c0c", 1, 4);
        Assert.assertTrue(scrClass.getCapacity() > 0);
        Assert.assertFalse(scrClass.isEmpty());

        scrClass.clear();
        Assert.assertTrue(scrClass.getCapacity() == 0);
        Assert.assertTrue(scrClass.isEmpty());

    }

    @Test
    public void testArray() {
        List<Long> test = Arrays.asList(0l, 1l, 4l, 5l, 8l, 9l, 12l, 13l);
        for (long i : test)
            for (long j : test)
                if (i < j)
                    log.info("i: " + i + " j: " + j);

    }

    @Test
    public void sdf() {
//        List<Long> uBasis = Arrays.asList(10l, 7l);
//        ScrClass scrClass = new ScrClass(uBasis, "6996", 0, 4);
//        log.info(scrClass.getBody());
        for (long i = 15; i > 0; i--)
            for (long j = i; j > 0; j--) {
                final List<Long> vectors = Arrays.asList(i, j);
                if (Canteaut.validateGJB(vectors, 4)) {
                    log.info(vectors);
                    ScrClass scrClass = new ScrClass(vectors, "6996", 0, 4);
                    if (!scrClass.isEmpty())
                        log.info("c: 0"+ scrClass.getBody());
                    scrClass = new ScrClass(vectors, "6996", 1, 4);
                    if (!scrClass.isEmpty())
                        log.info("c: 1"+ scrClass.getBody());
                }
            }
    }
}
