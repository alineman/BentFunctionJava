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
        List<Long> anf = Arrays.asList(48l, 213l, 215l, 151l, 120l, 98l, 135l, 150l, 199l, 16l, 5l, 186l, 95l, 172l, 72l, 77l, 41l, 36l, 56l, 78l, 96l, 243l, 118l, 86l, 114l, 79l, 74l, 231l, 67l, 225l, 177l, 249l, 92l, 45l, 180l, 9l, 202l, 17l, 189l, 224l, 57l, 76l, 54l, 19l, 83l, 193l, 222l, 4l, 104l, 132l, 1l, 27l, 70l, 59l, 87l, 187l, 183l, 107l, 6l, 53l, 55l, 200l, 206l, 49l, 170l, 129l, 123l, 147l, 146l, 138l, 102l, 209l, 81l, 84l, 108l, 99l, 244l, 164l, 62l, 116l, 252l, 233l, 24l, 161l, 35l, 223l, 232l, 66l, 148l, 115l, 251l, 124l, 216l, 220l, 106l, 174l, 248l, 192l, 155l, 188l, 137l, 37l, 43l, 30l, 246l, 154l, 125l, 111l, 13l, 237l, 178l, 33l, 32l, 119l, 93l, 153l, 195l, 103l, 238l, 130l, 219l, 113l, 134l, 85l, 176l, 14l, 179l, 31l, 90l, 80l, 68l, 26l, 141l, 152l, 185l, 60l, 173l, 47l, 97l, 75l, 182l, 194l, 165l, 203l, 158l, 7l, 100l, 168l, 175l, 139l, 227l, 126l, 234l, 71l);
        StringANF temp = new StringANF(anf, 8l);
//        StringANF temp = new StringANF("d6127431580720835209752f5a6d2eb0");
//        List<Long> combine = Arrays.asList(498l, 10l, 4l, 1l, 32l);
        List<Long> combine = Arrays.asList(42l, 17l, 5l);
        List<Long> altCombine = Canteaut.makeAdditionalSpace(combine, 6);
        log.info(temp.getFunction());
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
        for (long a : altCombine) {
            for (int i = 0; i < 8; i++) {
//            System.out.print(temp.getValue(combine.get(i)));
                final long elementOfSpace = Canteaut.getElementOfSpace(combine, i);
                System.out.print(temp.getValue(
                        elementOfSpace ^ a
                ));
//            log.info(temp.getValue(Canteaut.getElementOfSpace(combine, (i ^ 82l))) + " " + temp.getValue(Canteaut.getElementOfSpace(combine, i)));
            }
            System.out.println();
        }
        log.info(Canteaut.getBinary(altCombine, 6));

    }


    @Test
    public void findStepen() {
        List<Long> anf = Arrays.asList(48l, 213l, 215l, 151l, 120l, 98l, 135l, 150l, 199l, 16l, 5l, 186l, 95l, 172l, 72l, 77l, 41l, 36l, 56l, 78l, 96l, 243l, 118l, 86l, 114l, 79l, 74l, 231l, 67l, 225l, 177l, 249l, 92l, 45l, 180l, 9l, 202l, 17l, 189l, 224l, 57l, 76l, 54l, 19l, 83l, 193l, 222l, 4l, 104l, 132l, 1l, 27l, 70l, 59l, 87l, 187l, 183l, 107l, 6l, 53l, 55l, 200l, 206l, 49l, 170l, 129l, 123l, 147l, 146l, 138l, 102l, 209l, 81l, 84l, 108l, 99l, 244l, 164l, 62l, 116l, 252l, 233l, 24l, 161l, 35l, 223l, 232l, 66l, 148l, 115l, 251l, 124l, 216l, 220l, 106l, 174l, 248l, 192l, 155l, 188l, 137l, 37l, 43l, 30l, 246l, 154l, 125l, 111l, 13l, 237l, 178l, 33l, 32l, 119l, 93l, 153l, 195l, 103l, 238l, 130l, 219l, 113l, 134l, 85l, 176l, 14l, 179l, 31l, 90l, 80l, 68l, 26l, 141l, 152l, 185l, 60l, 173l, 47l, 97l, 75l, 182l, 194l, 165l, 203l, 158l, 7l, 100l, 168l, 175l, 139l, 227l, 126l, 234l, 71l);
//        StringANF temp = new StringANF(anf, 8l);
        for (Long l : anf)
            log.info(wt(l));
        log.info("sad");

    }

    private static int wt(long l) {
        String binaryString = Long.toBinaryString(l);
        return binaryString.replace("0", "").length();
    }
}
