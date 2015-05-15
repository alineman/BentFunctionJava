package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 19:36
 */
@Log4j
public class CanteautTest {

    @Test
    public void testNu() {
        Assert.assertEquals(3, Canteaut.nu(4, 5));
        Assert.assertEquals(2, Canteaut.nu(4, 4));
        Assert.assertEquals(1, Canteaut.nu(4, 3));
        Assert.assertEquals(1, Canteaut.nu(31, 5));
        Assert.assertEquals(0, Canteaut.nu(0, 5));
    }

    @Test
    public void testSortGJB() {
        List<Long> example = Arrays.asList(10l, 11l, 12l);
        Assert.assertFalse(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(13l, 11l, 12l);
        Assert.assertFalse(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(15l, 13l, 1l);
        Assert.assertFalse(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(4l, 2l, 1l);
        Assert.assertTrue(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(13l, 2l);
        Assert.assertTrue(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(8l, 7l);
        Assert.assertTrue(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(21l, 8l, 2l);
        Assert.assertTrue(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(21l, 10l, 2l);
        Assert.assertFalse(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(21l, 10l);
        Assert.assertTrue(Canteaut.validateGJB(example, 4));
        example = Arrays.asList(21l, 8l, 1l);
        Assert.assertFalse(Canteaut.validateGJB(example, 4));
    }

    @Ignore
    @Test
    public void testMappingVectors() {
        List<Long> example = Arrays.asList(4l, 5l, 6l);
        Assert.assertEquals(56, Canteaut.mappingVectorValue(example, 7, 6));
        Assert.assertEquals(16, Canteaut.mappingVectorValue(example, 2, 4));
        example = Arrays.asList(3l, 1l);
        Assert.assertEquals(5, Canteaut.mappingVectorValue(example, 3, 4));
        example = Arrays.asList(5l, 1l);
        Assert.assertEquals(17, Canteaut.mappingVectorValue(example, 3, 6));
    }

    @Test
    public void testMakeOBasis() {
        List<Long> example = Arrays.asList(4l, 5l, 6l);
        Assert.assertArrayEquals(Arrays.asList(1l, 2l, 3l).toArray(), Canteaut.makeOBasis(example, 6).toArray());
        Assert.assertArrayEquals(Arrays.asList(1l, 2l, 3l, 7l).toArray(), Canteaut.makeOBasis(example, 7).toArray());
        Assert.assertArrayEquals(Arrays.asList(1l, 2l, 3l).toArray(), Canteaut.makeOBasis(example, 5).toArray());
        example = Arrays.asList(1l, 3l, 5l);
        Assert.assertArrayEquals(Arrays.asList(2l, 4l).toArray(), Canteaut.makeOBasis(example, 5).toArray());
        Assert.assertArrayEquals(Arrays.asList(2l, 4l).toArray(), Canteaut.makeOBasis(example, 4).toArray());
        Assert.assertArrayEquals(Arrays.asList(2l, 4l, 6l).toArray(), Canteaut.makeOBasis(example, 6).toArray());
    }

    @Test
    public void testCountGJB() {
        Assert.assertEquals(35, Canteaut.countGJB(4, 2));
        Assert.assertEquals(15, Canteaut.countGJB(4, 3));
        Assert.assertEquals(11811, Canteaut.countGJB(7, 4));
        Assert.assertEquals(1395, Canteaut.countGJB(6, 3));
        Assert.assertEquals(155, Canteaut.countGJB(5, 3));
    }

    @Test
    public void testGetRight() {
        Assert.assertEquals(4, Canteaut.getRight(15, 4));
        Assert.assertEquals(5, Canteaut.getRight(15, 5));
        Assert.assertEquals(3, Canteaut.getRight(14, 4));
        Assert.assertEquals(3, Canteaut.getRight(2, 4));
    }

    @Test
    public void fill32() {
        for (long i = 0; i < (1 << 5); i++)
            log.info(StringUtils.leftPad(Long.toBinaryString(i), 5, '0'));
    }
}
