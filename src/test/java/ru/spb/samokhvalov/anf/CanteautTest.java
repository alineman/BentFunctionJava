package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
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

    @Test
    public void testMappingVectors() {
        List<Long> example = Arrays.asList(4l, 5l, 6l);
        Assert.assertEquals(56,Canteaut.mappingVectorValue(example, 7));
        Assert.assertEquals(16,Canteaut.mappingVectorValue(example, 2));
        example = Arrays.asList(3l,1l);
        Assert.assertEquals(5,Canteaut.mappingVectorValue(example, 3));
        example = Arrays.asList(5l,1l);
        Assert.assertEquals(17,Canteaut.mappingVectorValue(example, 3));
    }
}
