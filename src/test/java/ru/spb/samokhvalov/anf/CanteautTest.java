package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

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
        Assert.assertEquals(6, Canteaut.nu(0, 5));
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
        example = Arrays.asList(34l, 17l, 11l, 6l);
        Assert.assertTrue(Canteaut.validateGJB(example, 8));
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
        Assert.assertEquals(200787, Canteaut.countGJB(8, 4)); //0.5s
        Assert.assertEquals(3309747, Canteaut.countGJB(9, 4)); //20s
        Assert.assertEquals(3309747, Canteaut.countGJB(9, 5)); //20s
        Assert.assertEquals(788035, Canteaut.countGJB(9, 6));
        Assert.assertEquals(43435, Canteaut.countGJB(9, 7));
        Assert.assertEquals(53743987, Canteaut.countGJB(10, 4));
        Assert.assertEquals(109221651, Canteaut.countGJB(10, 5)); //104Mb*5 = 512Mb ==1.8Gb
        log.info(Canteaut.countGJB(9, 5));
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

    @Test
    public void testFillVariable() {
        List<Long> example = Arrays.asList(4l, 5l, 6l);
        Assert.assertArrayEquals(Arrays.asList(0l, 4l, 2l, 6l, 1l, 5l, 3l, 7l).toArray(), Canteaut.fillVariable(example, 6).toArray());
        example = Arrays.asList(3l, 2l, 1l);
        log.info(Canteaut.getBinary(Canteaut.fillVariable(example, 3), 3));
        Assert.assertArrayEquals(Arrays.asList(0l, 1l, 2l, 3l, 4l, 5l, 6l, 7l).toArray(), Canteaut.fillVariable(example, 3).toArray());

    }

    @Test
    public void testSummaryVectors() {
        List<Long> example = Arrays.asList(4l, 5l, 6l);
        log.info(Canteaut.summaryVectors(example, 7, 3));

    }

    @Test
    public void testCombineVectors() {
        List<Long> example = Arrays.asList(2l, 3l);
        Assert.assertEquals(0, Canteaut.combineVectors(example, 0, 3));
        Assert.assertEquals(2, Canteaut.combineVectors(example, 1, 3));
        Assert.assertEquals(1, Canteaut.combineVectors(example, 2, 3));
        Assert.assertEquals(3, Canteaut.combineVectors(example, 3, 3));
    }

    @Test
    public void testMakeAdditionalSpace() {
        List<Long> example = Arrays.asList(2l, 1l);
        Assert.assertArrayEquals(Arrays.asList(0l, 4l).toArray(), Canteaut.makeAdditionalSpace(example, 3).toArray());

        example = Arrays.asList(22l, 10l, 1l);

        Assert.assertArrayEquals(Arrays.asList(0l, 4l, 2l, 6l).toArray(), Canteaut.makeAdditionalSpace(example, 5).toArray());
    }

    @Test(expected = RuntimeException.class)
    public void testBadAdditionalSpace() {
        List<Long> example = Arrays.asList(3l, 1l);
        Canteaut.makeAdditionalSpace(example, 3);
    }

    @Test
    public void testGetElementOfSpace() {
        List<Long> example = Arrays.asList(2l, 1l);
        Assert.assertEquals(0, Canteaut.getElementOfSpace(example, 0));
        Assert.assertEquals(2, Canteaut.getElementOfSpace(example, 1));
        Assert.assertEquals(1, Canteaut.getElementOfSpace(example, 2));
        Assert.assertEquals(3, Canteaut.getElementOfSpace(example, 3));

    }


    @Test
    public void testUnionVectors() {
        Assert.assertEquals(3l, Canteaut.unionVectors(Arrays.asList(2l, 1l)));
        Assert.assertEquals(3l, Canteaut.unionVectors(Arrays.asList(3l, 3l)));
        Assert.assertEquals(11l, Canteaut.unionVectors(Arrays.asList(2l, 1l, 10l, 3l)));
        Assert.assertEquals(127l, Canteaut.unionVectors(Arrays.asList(64l, 1l, 2l, 4l, 8l, 16l, 32l)));

    }

    @Test
    public void testAddELement() {
        Map<Long, List<Long>> mapZero = new HashMap<>();
        Canteaut.addELement(mapZero, 1l, 10l);
        Assert.assertEquals(1, mapZero.size());

        Canteaut.addELement(mapZero, 1l, 11l);
        Assert.assertEquals(1, mapZero.size());

        Canteaut.addELement(mapZero, 2l, 11l);
        Assert.assertEquals(2, mapZero.size());

    }


    @Test
    @Ignore
    public void validateNormalFunction() {
        List<List<Long>> gjb = Canteaut.generateGJB(8, 4);
        log.info("GJB size: " + gjb.size());
//        List<Long> anf = Arrays.asList(250l, 23l, 42l, 61l, 213l, 237l, 135l, 114l, 53l, 142l, 74l, 19l, 97l, 96l, 9l, 145l, 37l, 254l, 171l, 244l, 27l, 49l, 51l, 8l, 214l, 215l, 91l, 133l, 168l, 128l, 87l, 184l, 10l, 131l, 235l, 7l, 228l, 161l, 232l, 99l, 88l, 89l, 236l, 40l, 255l, 67l, 162l, 98l, 32l, 66l, 113l, 198l, 82l, 167l, 65l, 14l, 6l, 201l, 192l, 124l, 147l, 109l, 249l, 137l, 231l, 119l, 204l, 129l, 69l, 164l, 84l, 194l, 1l, 252l, 16l, 2l, 0l, 71l, 166l, 17l, 199l, 207l, 55l, 120l, 29l, 83l, 122l, 188l, 202l, 241l, 56l, 154l, 141l, 233l, 174l, 223l, 177l, 210l, 179l, 180l, 224l, 159l, 243l, 73l, 108l, 112l, 47l, 50l, 217l, 234l, 38l, 191l, 155l, 178l, 150l, 153l, 26l, 222l, 251l, 227l, 36l, 111l, 68l, 79l, 132l, 220l, 117l, 11l, 62l, 127l, 33l, 34l, 247l, 106l, 181l);
//        StringANF function = new StringANF(anf, 8l);
        StringANF function = new StringANF("031303103013311565756675a9b95441cfdecfeefcdea899a9b899b77474d869");
        for (List<Long> currentGJB : gjb) {
            log.info("\n" + currentGJB);
            for (long i = 0; i < (1 << 4); i++)
                System.out.print(function.getValue(Canteaut.getElementOfSpace(currentGJB, i)));
        }
    }

    @Test
    @Ignore
    public void validateNormalFunction1() {
        int n = 9;
        int m = 5;
        long total = 0;
        long beforeGenerate = System.currentTimeMillis();

//        List<Long> anf = Arrays.asList(512l+256+128+64+32, 16l+8+4+2+1, 512l+256, 128l+64, 16l+8, 4l+2, 1l);

//        List<Long> anf = new ArrayList<>();
//        for (long k : anf1){
//            anf.add((Long) k << 2);
//        }
//        anf.add(3l);
//        StringANF functionANF = new StringANF(anf, n);
        StringANF functionANF = new StringANF("9428408900785128EED2A86E8C37ED588CA48931A706A26889A79938F48039D4145F548D0086BA55BEF1E9086232DEDE8FD473DEBBE0EC89DA8336B43BA1C05F");
        log.info(functionANF.getFunction());
//        log.info(anf);
        List<List<Long>> gjb = Canteaut.generateFastGJB(n, m);
        log.info("GJB size: " + gjb.size() + ". Time to generate: " + Canteaut.formatTime(System.currentTimeMillis() - beforeGenerate));
        long start = System.currentTimeMillis();
        try {
            for (List<Long> currentGJBToValidate : gjb) {
//                log.info(Canteaut.getBinary(currentGJBToValidate, n));
                List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, n);
//            log.info(currentGJBToValidate);
                for (long a : addditionalSpace) {
                    total++;
//                for (long a = 0; a < (1 << n); a++) {
                    if (Canteaut.validateConstant(currentGJBToValidate, a, functionANF)) {
                        log.info("FAIL " + currentGJBToValidate + ", a = " + a);
                        throw new RuntimeException(currentGJBToValidate.toString());
                    }
//                    boolean exit = false;
//                    long test = functionANF.getValue(a);
//                    for (long i = 0; i < (1 << m); i++) {
////                        log.info(Canteaut.calculateWalsh(a, currentGJBToValidate, functionANF));
//                        final long elementOfSpace = Canteaut.getElementOfSpace(currentGJBToValidate, i) ^ a;
//                        total++;
//                        System.out.print(functionANF.getValue(elementOfSpace));
//                        if (test != functionANF.getValue(elementOfSpace)) {
//                            exit = true;
//                            break;
//                        }
//                        if (!exit) {
//                            log.info("FAIL " + currentGJBToValidate + ", a = " + a);
//                            throw new RuntimeException(currentGJBToValidate.toString());
//                        }
//                    }
//                System.out.println();
                }
            }
        } catch (Exception ignored) {
            log.warn(ignored);
        }
        log.info("Total: " + total);
        log.info("Time: " + Canteaut.formatTime(System.currentTimeMillis() - start));

    }

    @Test
    public void testCalculateWalsh() {
        StringANF function = new StringANF(Arrays.asList(10l, 5l), 4);
        Assert.assertEquals(4, Canteaut.calculateWalsh(0, Arrays.asList(1l, 2l, 4l, 8l), function));

    }

    @Test
    public void validateConstant() {
        int n = 4;
        StringANF function = new StringANF("e7b6");
        Assert.assertFalse(Canteaut.validateConstant(Arrays.asList(1l, 2l), 0, function));

        function = new StringANF("e78f");
        Assert.assertTrue(Canteaut.validateConstant(Arrays.asList(1l, 2l), 12, function));


    }

    @Test
    public void testMakeSimpleBasisGJB() {
        int[] k = {1, 3};
        log.debug(Canteaut.makeSimpleBasisGJB(k, 4));
        Assert.assertArrayEquals(Arrays.asList(8l, 2l).toArray(), Canteaut.makeSimpleBasisGJB(k, 4).toArray());
        Assert.assertArrayEquals(Arrays.asList(4l, 1l).toArray(), Canteaut.makeSimpleBasisGJB(k, 3).toArray());
        int[] k1 = {1, 4, 5};
        Assert.assertArrayEquals(Arrays.asList(16l, 2l, 1l).toArray(), Canteaut.makeSimpleBasisGJB(k1, 5).toArray());
    }

    @Test
    public void testGetDimensionForGJB() {
        int[] k = {1, 3};
        Assert.assertEquals(1, Canteaut.getDimensionForGJB(k, 3));

        Assert.assertEquals(3, Canteaut.getDimensionForGJB(k, 4));
        int[] k1 = {1, 2, 4};
        Assert.assertEquals(2, Canteaut.getDimensionForGJB(k1, 4));
        Assert.assertEquals(5, Canteaut.getDimensionForGJB(k1, 5));
        k1[1] = 3;
        Assert.assertEquals(4, Canteaut.getDimensionForGJB(k1, 5));

    }

    @Test
    public void testGetAdditionalSpaces() {
        int[] k = {1, 3};
        Assert.assertEquals("[[2], []]", Canteaut.getAdditionalSpaces(k, 3).toString());

        Assert.assertEquals("[[2, 4], [4]]", Canteaut.getAdditionalSpaces(k, 4).toString());
        int[] k1 = {1, 2, 4};
        Assert.assertEquals("[[3], [3], []]", Canteaut.getAdditionalSpaces(k1, 4).toString());
        Assert.assertEquals("[[3, 5], [3, 5], [5]]", Canteaut.getAdditionalSpaces(k1, 5).toString());
        k1[1] = 3;
        Assert.assertEquals("[[2, 5], [5], [5]]", Canteaut.getAdditionalSpaces(k1, 5).toString());
    }

    @Test
    public void testGnerateFastGJB() {
        log.info(Canteaut.generateFastGJB(4, 2));
        log.info(Canteaut.generateFastGJB(6, 2));
        for (int k = 2; k <= 4; k++) {
            long startOld = System.currentTimeMillis();
//            List<List<Long>> old = Canteaut.generateGJB(2*k, k);
            long totalold = System.currentTimeMillis() - startOld;
//            old.remove(0);
            long startNew = System.currentTimeMillis();
            List<List<Long>> updated = Canteaut.generateFastGJB(2 * k, k);
            long totalNew = System.currentTimeMillis() - startNew;
            log.info("Dimension: " + (2 * k) + ". Count: " + Canteaut.countGJB(2 * k, k) + ". Old: " + totalold + ", new: " + totalNew);
//            for (List<Long> element : updated)
//                if (!old.contains(element))
//                    throw new RuntimeException("Not contains in old");
//            for (List<Long> element : old)
//                if (!updated.contains(element))
//                    throw new RuntimeException("Not contains in new");
        }

    }


    @Test
    public void testMakeOutput() {
        log.info(Canteaut.makeOutput(Arrays.asList(8l, 2l, 5l), 10l, 4));

    }

    @Test
    public void testSaveToFile() {
        long start = System.currentTimeMillis();
        final int n = 9;
        final int t0 = 5;

        String fileName = "/tmp/gjb.test." + n + "." + t0;
        Canteaut.generateFastGJB(n, t0, fileName);
        long end1 = System.currentTimeMillis();
        Canteaut.generateFastGJB(n, t0);
        long end2 = System.currentTimeMillis();
        log.info("Save to file: " + Canteaut.formatTime(end1 - start) + ". Simple generate: " + Canteaut.formatTime(end2 - end1));

        File file = new File(fileName);
        Reader fileReader = null;
        BufferedReader bufferedReader = null;
        List<List<Long>> result = new ArrayList<>();
        long startRead = System.currentTimeMillis();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null){
                List<Long> temp = Canteaut.convertStringToList(input);
                if (!Canteaut.validateGJB(temp, n))
                    throw new RuntimeException("error read");
                result.add(temp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (bufferedReader != null && fileReader != null)
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        Assert.assertEquals(Canteaut.countGJB(n, t0), result.size());
        log.info("Time to read from file: " + Canteaut.formatTime(System.currentTimeMillis() - startRead));
        File deleted = new File(fileName);
        if (deleted.exists())
            try {
                Files.delete(deleted.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void testSerializeArray() {
        List<Long> temp = new ArrayList<>();
        for (long i = 0; i < 25; i++)
            temp.add(i);
        temp.toArray();
        List<List<Long>> result = new ArrayList<>();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/home/isamokhvalov/gjb.test");
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(fis);
//            for (int i = 0; i < 200787; i++) {
            while (fis.available() > 0) {
                ArrayList<Long> clubs = (ArrayList<Long>) ois.readObject();
                if (!Canteaut.validateGJB(clubs, 8))
                    throw new RuntimeException("error read");
                result.add(clubs);
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(result.size());
    }

    @Test
    public void testConvertToString() {
        List<Long> test = Arrays.asList(1023l,11l,15l,65l);
        final String message = Canteaut.convertListToString(test);
        Assert.assertEquals("3ff,b,f,41,", message);
        log.info(message);
        Assert.assertArrayEquals(test.toArray(), Canteaut.convertStringToList(message).toArray());
    }

    @Test
    public void testMakeConstructor(){
        String x16 = Canteaut.makeStringAnf(new ArrayList<Long>(), 7);
        Assert.assertEquals("00000000000000000000000000000000", x16);
        Assert.assertEquals("0000000000000000", Canteaut.makeStringAnf(new ArrayList<Long>(), 6));
        Assert.assertEquals("8000000000000000", Canteaut.makeStringAnf(Arrays.asList(0l), 6));
        Assert.assertEquals("F000000000000000", Canteaut.makeStringAnf(Arrays.asList(0l,1l,2l,3l), 6).toUpperCase());
        Assert.assertEquals("00F0000000000000", Canteaut.makeStringAnf(Arrays.asList(8l,9l,10l,11l), 6).toUpperCase());
        Assert.assertEquals("C000000000000000", Canteaut.makeStringAnf(Arrays.asList(0l, 1l), 6).toUpperCase());
        Assert.assertEquals("C000000000000001", Canteaut.makeStringAnf(Arrays.asList(0l, 1l, 63l), 6).toUpperCase());
        Assert.assertEquals("C008000000000001", Canteaut.makeStringAnf(Arrays.asList(0l, 1l, 12l, 63l), 6).toUpperCase());
    }

    @Test
    public void makeAffine(){
        List<Long> anf = Arrays.asList(112l, 40l, 9l, 107l, 77l);
//        List<Long> anf = Arrays.asList(1l,64l,8l);
        StringANF function = new StringANF(anf, 7);
        long a = 8;
        List<Long> subspace = Arrays.asList(33l, 16l, 4l, 3l);
        List<Long> newValues = new ArrayList<>();
        for (int i =0; i< (1 << 4); i++){
            long position = a ^ Canteaut.getElementOfSpace(subspace, i);
            long tempValue = function.getValue(position);
            log.info(position + " " + tempValue);
            if (tempValue == 1)
                newValues.add(position);

        }

//        StringANF newFunction = new StringANF(Canteaut.makeStringAnf(newValues, 7));
        StringANF newFunction = new StringANF("0011001100220022");
        log.info(function.getFunction());
        log.info(newFunction.getFunction());
        log.info(function.getAllAnf());
        log.info(newFunction.getAllAnf());

        StringANF newFunction2 = new StringANF(Canteaut.makeStringAnf(Arrays.asList(1l,3l,5l,7l,9l,11l,13l,15l), 4));
        log.info(newFunction2.getFunction());
        log.info(newFunction2.getAllAnf());

//        for (long i =0; i < (1<<6); i++){
//            log.info(StringUtils.leftPad(Long.toBinaryString(i), 6, '0') + "|" + newFunction.getValue(i));
//        }
    }


}
