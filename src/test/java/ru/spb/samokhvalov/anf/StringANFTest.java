package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testANFConstructor() {
        List<Long> anf = Arrays.asList(63l, 48l, 12l, 3l);
        StringANF temp = new StringANF(anf, 8l);
//        log.info(temp.getFunction());

        anf = Arrays.asList(100l, 487l, 152l, 138l, 384l, 10l, 149l, 1l, 7l, 35l, 100l, 200l, 3l, 64l, 431l);
        temp = new StringANF(anf, 9l);
        log.info(temp.getFunction());
        log.info(temp.getAllAnf());
        for (long l : anf)
            log.info(Long.toBinaryString(l));

        anf = Arrays.asList(1l, 2l, 4l, 8l, 16l, 32l, 64l);
        temp = new StringANF(anf, 7l);
        log.info(temp.getFunction());
        log.info(temp.getAllAnf());

    }

    @Test
    public void testGetTexAnf() {
        List<Long> anf = Arrays.asList(1l, 3l, 4l, 8l);
        StringANF temp = new StringANF(anf, 4l);
        log.info(temp.getTeXAnf());
        log.info(temp.getAllAnf());
    }

    @Test
    public void forResults() {
        List<Long> anf = Arrays.asList(1l, 2l, 4l, 8l, 16l, 32l, 64l);
//        StringANF temp = new StringANF(anf, 7l);
        StringANF temp = new StringANF("45764576aff02457ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab50514576457654675467ba89ba89ab98ab98454540bf545451aeba45bfbfab54aeae4576457654675467ba89ba89ab98ab98bababf40abaaae5045ba404055ab5051");
//        List<Long> combine = Arrays.asList(498l, 10l, 4l, 1l, 32l);
        List<Long> combine = Arrays.asList(264l, 88l, 32l, 4l, 2l);
//        List<Long> combine = Arrays.asList(66l, 34l, 18l, 10l, 6l);
//        List<Long> combine = Arrays.asList(0l,
//                3l,
//                13l,
//                14l,
//                37l,
//                38l,
//                40l,
//                43l,
//                81l,
//                82l,
//                92l,
//                95l,
//                116l,
//                119l,
//                121l,
//                122l,
//                388l,
//                391l,
//                393l,
//                394l,
//                417l,
//                418l,
//                428l,
//                431l,
//                469l,
//                470l,
//                472l,
//                475l,
//                496l,
//                499l,
//                509l,
//                510l
//        );

//        for (long k : combine)
//        log.info( k ^ 80l);
        for (int i = 0; i < 32; i++) {
//            System.out.print(temp.getValue(combine.get(i)));
            System.out.print(temp.getValue(
                   657 ^ Canteaut.getElementOfSpace(combine, i)
            ));
//            log.info(temp.getValue(Canteaut.getElementOfSpace(combine, (i ^ 82l))) + " " + temp.getValue(Canteaut.getElementOfSpace(combine, i)));
        }

    }

}
