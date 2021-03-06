package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        Assert.assertEquals(1, example.getOldValue(0));
        Assert.assertEquals(1, example.getOldValue(1));
        Assert.assertEquals(1, example.getOldValue(2));
        Assert.assertEquals(1, example.getOldValue(3));

        Assert.assertEquals(1, example.getOldValue(4));
        Assert.assertEquals(0, example.getOldValue(5));
        Assert.assertEquals(1, example.getOldValue(6));
        Assert.assertEquals(0, example.getOldValue(7));

        Assert.assertEquals(1, example.getOldValue(8));
        Assert.assertEquals(0, example.getOldValue(9));
        Assert.assertEquals(0, example.getOldValue(10));
        Assert.assertEquals(0, example.getOldValue(11));

        Assert.assertEquals(0, example.getOldValue(12));
        Assert.assertEquals(1, example.getOldValue(13));
        Assert.assertEquals(0, example.getOldValue(14));
        Assert.assertEquals(0, example.getOldValue(15));

        Assert.assertEquals(1, example.getFastValue(0));
        Assert.assertEquals(1, example.getFastValue(1));
        Assert.assertEquals(1, example.getFastValue(2));
        Assert.assertEquals(1, example.getFastValue(3));

        Assert.assertEquals(1, example.getFastValue(4));
        Assert.assertEquals(0, example.getFastValue(5));
        Assert.assertEquals(1, example.getFastValue(6));
        Assert.assertEquals(0, example.getFastValue(7));

        Assert.assertEquals(1, example.getFastValue(8));
        Assert.assertEquals(0, example.getFastValue(9));
        Assert.assertEquals(0, example.getFastValue(10));
        Assert.assertEquals(0, example.getFastValue(11));

        Assert.assertEquals(0, example.getFastValue(12));
        Assert.assertEquals(1, example.getFastValue(13));
        Assert.assertEquals(0, example.getFastValue(14));
        Assert.assertEquals(0, example.getFastValue(15));

        example = new StringANF("ad02e643");
        Assert.assertEquals(1, example.getValue(0));
        Assert.assertEquals(0, example.getValue(1));
        Assert.assertEquals(1, example.getValue(2));
        Assert.assertEquals(0, example.getValue(3));

        Assert.assertEquals(1, example.getValue(4));
        Assert.assertEquals(1, example.getValue(5));
        Assert.assertEquals(0, example.getValue(6));
        Assert.assertEquals(1, example.getValue(7));

        Assert.assertEquals(0, example.getValue(8));
        Assert.assertEquals(0, example.getValue(9));
        Assert.assertEquals(0, example.getValue(10));
        Assert.assertEquals(0, example.getValue(11));

        Assert.assertEquals(0, example.getValue(12));
        Assert.assertEquals(0, example.getValue(13));
        Assert.assertEquals(1, example.getValue(14));
        Assert.assertEquals(0, example.getValue(15));

        Assert.assertEquals(1, example.getValue(16));
        Assert.assertEquals(1, example.getValue(17));
        Assert.assertEquals(1, example.getValue(18));
        Assert.assertEquals(0, example.getValue(19));

        Assert.assertEquals(0, example.getValue(20));
        Assert.assertEquals(1, example.getValue(21));
        Assert.assertEquals(1, example.getValue(22));
        Assert.assertEquals(0, example.getValue(23));

        Assert.assertEquals(0, example.getValue(24));
        Assert.assertEquals(1, example.getValue(25));
        Assert.assertEquals(0, example.getValue(26));
        Assert.assertEquals(0, example.getValue(27));

        Assert.assertEquals(0, example.getValue(28));
        Assert.assertEquals(0, example.getValue(29));
        Assert.assertEquals(1, example.getValue(30));
        Assert.assertEquals(1, example.getValue(31));

    }


    @Test
    public void countLinear() {
        StringANF anf = new StringANF("6996");
        for (int i = 0; i < 16; i++)
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
    public void testAnotherConsrtuction() {
        List<String> functions = Arrays.asList(
                "8000",
                "4000",
                "2000",
                "1000",
                "0800",
                "0400",
                "0200",
                "0100",
                "0080",
                "0040",
                "0020",
                "0010",
                "0008",
                "0004",
                "0002",
                "0001"
        );
        for (String function : functions) {
            StringANF func = new StringANF(function);
            for (int i = 0; i < 16; i++)
                System.out.print(func.getValue(i));
            System.out.println();
        }

    }

    @Test
    public void testGetTexAnf() {
        List<Long> anf = Arrays.asList(1l, 3l, 4l, 8l);
        StringANF temp = new StringANF(anf, 4l);
        log.info(temp.getTeXAnf());
        log.info(temp.getAllAnf());
        log.info(temp.getFunction());
    }

    @Test
    public void forResults() {
//        List<Long> anf = Arrays.asList(48l, 213l, 215l, 151l, 120l, 98l, 135l, 150l, 199l, 16l, 5l, 186l, 95l, 172l, 72l, 77l, 41l, 36l, 56l, 78l, 96l, 243l, 118l, 86l, 114l, 79l, 74l, 231l, 67l, 225l, 177l, 249l, 92l, 45l, 180l, 9l, 202l, 17l, 189l, 224l, 57l, 76l, 54l, 19l, 83l, 193l, 222l, 4l, 104l, 132l, 1l, 27l, 70l, 59l, 87l, 187l, 183l, 107l, 6l, 53l, 55l, 200l, 206l, 49l, 170l, 129l, 123l, 147l, 146l, 138l, 102l, 209l, 81l, 84l, 108l, 99l, 244l, 164l, 62l, 116l, 252l, 233l, 24l, 161l, 35l, 223l, 232l, 66l, 148l, 115l, 251l, 124l, 216l, 220l, 106l, 174l, 248l, 192l, 155l, 188l, 137l, 37l, 43l, 30l, 246l, 154l, 125l, 111l, 13l, 237l, 178l, 33l, 32l, 119l, 93l, 153l, 195l, 103l, 238l, 130l, 219l, 113l, 134l, 85l, 176l, 14l, 179l, 31l, 90l, 80l, 68l, 26l, 141l, 152l, 185l, 60l, 173l, 47l, 97l, 75l, 182l, 194l, 165l, 203l, 158l, 7l, 100l, 168l, 175l, 139l, 227l, 126l, 234l, 71l);
//        StringANF temp = new StringANF(anf, 8l);
//        StringANF temp = new StringANF("26949BA40382FB68409AF1B97AF8C6EF");
        StringANF temp = new StringANF("00008020000002080000802000000208");

//        StringANF temp = new StringANF("111e111e111eeee1");
//        List<Long> combine = Arrays.asList(498l, 10l, 4l, 1l, 32l);
        List<Long> combine = Arrays.asList(64l, 38l, 19l, 10l);
        List<Long> altCombine = Canteaut.makeAdditionalSpace(combine, 7);
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
//        for (long a : altCombine) {
//            System.out.print(" a: " + a + " ");
//            for (int i = 0; i < 8; i++) {
////            System.out.print(temp.getValue(combine.get(i)));
//                final long elementOfSpace = Canteaut.getElementOfSpace(combine, i);
//                System.out.print(temp.getValue(
//                        elementOfSpace ^ a
//                ));
////            log.info(temp.getValue(Canteaut.getElementOfSpace(combine, (i ^ 82l))) + " " + temp.getValue(Canteaut.getElementOfSpace(combine, i)));
//            }
//            System.out.println();
//        }
//        log.info(Canteaut.getBinary(altCombine, 6));

        log.info("new");

        for (int i = 0; i < 16; i++)
            log.info((3 ^ Canteaut.getElementOfSpace(combine, i)) + " " + temp.getValue(3 ^ Canteaut.getElementOfSpace(combine, i)));
        for (int i = 0; i < (1 << 7); i++)
            if (temp.getValue(i) != 0)
                log.info(i);

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

    @Test
    public void testMake() {
        List<Long> anf = Arrays.asList(8l);
        StringANF temp = new StringANF(anf, 4l);
        log.info(temp.getTeXAnf());
        log.info(temp.getAllAnf());
        log.info(temp.getFunction());


    }

    @Test
    public void testInvertValue() {
        StringANF test = new StringANF("FA84");
        test.invertFunction();
        Assert.assertEquals("215F", test.getFunction());
        test.invertFunction();
        Assert.assertEquals("FA84", test.getFunction());

    }

    @Test
    public void testNewValue() {
        for (int i = 4; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                StringANF function = Canteaut.generateRandomStringANF(i, new Random());
                for (int position = 0; position < (1 << i); position++)
                    Assert.assertEquals(function.getOldValue(position), function.getFastValue(position));
            }
        }
    }

    @Test
    public void testTimeComparationGetValue() {
        long getOld = 0;
        long getNew = 0;
        for (int i = 10; i < 15; i++) {
            StringANF function = Canteaut.generateRandomStringANF(i, new Random());
            for (int k = 0; k < 10; k++) {
                long start = System.currentTimeMillis();
                for (int position = 0; position < (1 << i); position++) {
                    function.getOldValue(position);
                }
                getOld += (System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                for (int position = 0; position < (1 << i); position++) {
                    function.getFastValue(position);
                }
                getNew += (System.currentTimeMillis() - start);
            }
        }

        log.info("Old time: " + Canteaut.formatTime(getOld) + ". New time: " + Canteaut.formatTime(getNew));
    }

    @Test
    public void compareANF() {
        String func = "21A20A79D6FA135903C41746680F6DF5BB76D146854232C660255CDA98277993056BE4E36803F19AB16B90BA83532593367F55DFF41DDC608B45BED5B32DCD9F1C3AF2DBAF7C0ABF9B5C7627D2108B4976771ABEA6F7B2D0AD7E544422AEA38597C57FBB4EBC84761053FB48F0E3C64C688B3EE1B452F340437D7FE4FCC8D1D6";
        StringANF k = new StringANF(func);
        List<Long> anf = k.getAnf();
        StringANF temp = new StringANF(anf, 10l);
        Assert.assertEquals(func, temp.getFunction().toUpperCase());


    }

    @Test
    public void crate95from74() {
        List<Integer> anfI = Arrays.asList(1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21, 27, 28, 30, 34, 35, 36, 40, 41, 42, 44, 45, 46, 47, 48, 49, 51, 53, 54, 55, 56, 58, 59, 61, 62, 64, 65, 68, 69, 70, 71, 74, 75, 76, 77, 78, 79, 80, 82, 83, 84, 85, 88, 89, 93, 96, 99, 100, 101, 102, 104, 105, 106, 107, 108, 110, 111, 112, 113, 114, 115, 118, 120, 121, 124, 125, 126);
        List<Long> anf = new ArrayList<>();
        for (int i : anfI) {
            anf.add((long) i << 2);
        }
        anf.add(3l);
        log.info(anf);
        StringANF example = new StringANF("054A651753B6B43C907B7878AEEAD397");
        StringANF function94 = new StringANF(anf, 9l);
        log.info(function94.getFunction());
        log.info(function94.getAnf());
        List<Long> values = new ArrayList<>();
        for (long i = 0; i < (1 << 9); i++) {
            if (((example.getValue(i >> 2)) ^ (( i & 3) == 3 ? 1 : 0)) == 1)
                values.add(i);
        }
        final String message = Canteaut.makeStringAnf(values, 9);
        log.info(message);
        StringANF updated = new StringANF(message);
        log.info(updated.getFunction().toUpperCase());
        log.info(updated.getAnf().size());
    }

    @Test
    public void createDouble94() {
        StringANF f1 = new StringANF("E11E883BEEB8E145936AC9E639C335A2");
        StringANF f2 = new StringANF("75A009F7E5A119F6F60918B30BE6F449");
        List<Long> values = new ArrayList<>();
        for (long i =0; i < (1<<9); i++){
            long x = i >> 2;
            long y = (i & 2) !=0 ? 1 : 0;
            long z = (i & 1) !=0 ? 1 : 0;
            long value = f1.getValue(x) ^ (y & z) ^ ((y ^ z)&(f1.getValue(x) ^ f2.getValue(x)));
            if (value !=0)
                values.add(i);
        }

        StringANF result = new StringANF(Canteaut.makeStringAnf(values, 9));
        log.info(result.getFunction().toUpperCase());
        log.info(result.getAnf());
        log.info(result.getAnf().size());
    }

}
