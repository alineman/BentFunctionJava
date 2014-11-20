package ru.spb.samokhvalov.anf;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

@Log4j
public class LinearSubDimensionTest extends TestCase {

    private static LinearSubDimension example = new LinearSubDimension(2, 4);
    private static LinearSubDimension example3 = new LinearSubDimension(2, 3);

    @Test
    public void testGetCountSubDimensions() throws Exception {
//        assert ((new LinearSubDimension(5,5)).getCountSubDimensions() == 1);
        assert ((new LinearSubDimension(2, 4)).getCountSubDimensions() == 6);
//        assert ((new LinearSubDimension(2,3)).getCountSubDimensions() == 3);
//        assert ((new LinearSubDimension(1,4)).getCountSubDimensions() == 4);
//        assert ((new LinearSubDimension(6,10)).getCountSubDimensions() == 210);
//        log.info((new LinearSubDimension(2,4)).getCountSubDimensions());
    }

    @Test
    public void testGetAllValues() throws Exception {
        assert (example.getAllValues().size() == 6);
    }

    @Test
    public void testGetValue() throws Exception {

    }

    @Test
    public void testValidateNormal() throws Exception {
//        example.validateNormality(30856);
        for (int i = 0; i < 256; i++) {
            int count = example3.validateNormality(i);
            if (count > 0)
                log.info(i + ":" + example3.validateNormality(i));
        }

        log.debug(example3.validateNormality(170));
    }
}