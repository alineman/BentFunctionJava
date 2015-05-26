package ru.spb.samokhvalov.anf.diploma;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import ru.spb.samokhvalov.diploma.SimpleScr;

import java.util.Arrays;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 26.05.15
 * Time: 14:00
 */
@Log4j
public class SimpleScrTest {


    @Test
    public void testSimpleConstructor() {
        List<Long> uBasis = Arrays.asList(2l, 1l);
        SimpleScr simpleScr = new SimpleScr(uBasis, "ff", 3);

        Assert.assertArrayEquals(Arrays.asList(0l, 4l).toArray(), simpleScr.getOBasisVariable().toArray());
        simpleScr = new SimpleScr(uBasis, "ff", 4);
        Assert.assertArrayEquals(Arrays.asList(0l, 8l, 4l, 12l).toArray(), simpleScr.getOBasisVariable().toArray());

        uBasis = Arrays.asList(9l, 4l);
        simpleScr = new SimpleScr(uBasis, "ff", 4);
        Assert.assertArrayEquals(Arrays.asList(0l, 2l, 1l, 3l).toArray(), simpleScr.getOBasisVariable().toArray());
    }
}
