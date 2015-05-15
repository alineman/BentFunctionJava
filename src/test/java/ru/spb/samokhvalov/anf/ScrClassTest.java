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
        Assert.assertArrayEquals(Arrays.asList(0l, 4l).toArray(), scrClass.getBody().toArray());

//        scrClass = new ScrClass(uBasis, "cccc", 1, 4);
//        Assert.assertArrayEquals(Arrays.asList(0l, 1l).toArray(), scrClass.getBody().toArray());


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
        Assert.assertArrayEquals(Arrays.asList(0l, 8l, 2l, 10l, 1l, 9l, 3l, 11l).toArray(), scrClass.getBody().toArray());
    }

    @Test
    public void validateComplexConstructor() {
        List<Long> uBasis = Arrays.asList(8l);
        ScrClass scrClass = new ScrClass(uBasis, "0c0c", 1, 4);
        log.info(scrClass.getBody());
        Assert.assertArrayEquals(Arrays.asList(4l, 5l).toArray(), scrClass.getBody().toArray());

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
        StringANF anf = new StringANF("6996");

        int k = 0;
        for (long i = 15; i > 1; i--)
            for (long j = i; j > 0; j--) {
                final List<Long> vectors = Arrays.asList(i, j);
                if (Canteaut.validateGJB(vectors, 4)) {
                    List<Long> uVectors = Canteaut.fillU(vectors);
//                    log.info(Canteaut.getBinary(uVectors, 4) + " " + Canteaut.getBinary(vectors, 4));
//                    log.info(uVectors + " " + Canteaut.getBinary(vectors, 4));
                    boolean constant = true;
                    long ex = anf.getValue(uVectors.get(0)+1);
                    for (long l : uVectors)
                        if (ex != anf.getValue(l +1 ))
                            constant = false;
                    if (constant) {
                        log.info("c: " + ex + " " + Canteaut.getBinary(uVectors, 4) + " " + Canteaut.getBinary(vectors, 4));

                    }
                    k++;
//                    ScrClass scrClass = new ScrClass(vectors, "6996", 0, 4);
//                    if (!scrClass.isEmpty())
//                        log.info("c: 0"+ scrClass.getBody());
//                    scrClass = new ScrClass(vectors, "6996", 1, 4);
//                    if (!scrClass.isEmpty())
//                        log.info("c: 1"+ scrClass.getBody());
                }
            }
        log.info("Total GJB is " + k);
    }

    @Test
    public void validateScrClass() {
        List<Long> uBasis = Arrays.asList(10l, 5l);
        ScrClass scrClass = new ScrClass(uBasis, "6996", 0, 4);
        log.info(scrClass.getBody());
        scrClass = new ScrClass(uBasis, "6996", 1, 4);
        log.info(scrClass.getBody());

    }

    @Test
    public void testCountGJB() {
        int k =0;
        final int dimension = 4;
        final int max = (1 << dimension) -1 ;
        for (long i = max; i > 0; i--)
            for (long j = max; j > 0; j--)
                if (Canteaut.validateGJB(Arrays.asList(i, j), dimension)) {
//                    log.info("i: " + i + ", j: " + j);
                    k++;
                }
        log.info("Total GJB: " + k);
    }

    @Test
    public void computeExample() {
//        List<Long> uBasis = Arrays.asList(10l, 7l);
//        ScrClass scrClass = new ScrClass(uBasis, "6996", 0, 4);
//        log.info(scrClass.getBody());
        StringANF anf = new StringANF("6996");

        int k = 0;
        for (long i = 15; i > 1; i--)
            for (long j = i; j > 0; j--) {
                final List<Long> vectors = Arrays.asList(i, j);
                if (Canteaut.validateGJB(vectors, 4)) {
                    ScrClass example0 = new ScrClass(vectors,"6996", 0, 4);
                    ScrClass example1 = new ScrClass(vectors,"6996", 0, 4);
                    if (!example0.isEmpty())
                        log.info("U: "+ Canteaut.getBinary(vectors, 4) + ", |C|: " + Canteaut.getBinary(example0.getBody(), 4));
//                    List<Long> uVectors = Canteaut.fillU(vectors);
//                    log.info(Canteaut.getBinary(uVectors, 4) + " " + Canteaut.getBinary(vectors, 4));
//                    log.info(uVectors + " " + Canteaut.getBinary(vectors, 4));
//                    boolean constant = true;
//                    long ex = anf.getValue(uVectors.get(0)+1);
//                    for (long l : uVectors)
//                        if (ex != anf.getValue(l +1 ))
//                            constant = false;
//                    if (constant) {
//                        log.info("c: " + ex + " " + Canteaut.getBinary(uVectors, 4) + " " + Canteaut.getBinary(vectors, 4));

//                    }
                    k++;
//                    ScrClass scrClass = new ScrClass(vectors, "6996", 0, 4);
//                    if (!scrClass.isEmpty())
//                        log.info("c: 0"+ scrClass.getBody());
//                    scrClass = new ScrClass(vectors, "6996", 1, 4);
//                    if (!scrClass.isEmpty())
//                        log.info("c: 1"+ scrClass.getBody());
                }
            }
        log.info("Total GJB is " + k);
    }

    @Test
    public void testFillU() {
        List<Long> example = Arrays.asList(9l,3l,1l);
        log.info(Canteaut.fillU(example));
        log.info(Canteaut.getBinary(Canteaut.fillU(example), 5));
        example = Arrays.asList(2l);
        log.info(Canteaut.fillU(example));


    }

}
