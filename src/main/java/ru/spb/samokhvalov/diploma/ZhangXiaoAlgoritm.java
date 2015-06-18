package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import ru.spb.samokhvalov.anf.CalculateWalsh;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.io.*;
import java.util.*;

/**
 * User: isamokhvalov
 * Date: 13.06.15
 * Time: 17:56
 */
@Log4j
public class ZhangXiaoAlgoritm {
    public static void main(String[] args) {
        final int n = 6;
        final int k = (n >> 1);
        long zero = System.currentTimeMillis();
        log.info("Start: n = " + n + ", k = " + k);

        int count = 0;
        long total = Canteaut.countGJB(n,k);
        long totalBent = 0;
        long start = System.currentTimeMillis();

        Scanner in = null;
        List<StringANF> rawFunctions = new ArrayList<>();
        try {
            in = new Scanner(new File("/home/isamokhvalov/2.log"));
            StringANF tempFunction;
            while (in.hasNextLine()) {
                final String stringFromFile = in.nextLine();
                tempFunction = new StringANF(stringFromFile.length() < 256 ? StringUtils.leftPad(stringFromFile, 256, '0') : stringFromFile);
                tempFunction.invertFunction();
                CalculateWalsh walsh = new CalculateWalsh(tempFunction);
                if (walsh.isBent())
                    rawFunctions.add(tempFunction);
                else
                    log.warn("Fount not bent: " + tempFunction.getFunction());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when reading file with list of functions");
        } finally {
            if (in != null)
                in.close();
        }
        log.info("Time to load functions: " + Canteaut.formatTime(start - zero) + ". Size = " + rawFunctions.size());

        long start0 = System.currentTimeMillis();

        File file = new File("/home/isamokhvalov/gjb10-5");
//        File file = new File("/home/isamokhvalov/gjb.string.8.4");
        Reader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                List<Long> currentGJB = Canteaut.convertStringToList(input);
                if (!Canteaut.validateGJB(currentGJB, n))
                    throw new RuntimeException("error read");
                validateONormal(rawFunctions, currentGJB, n);
                count++;
                if (count % 10000 == 0)
                    log.info(" Complete: " + (((float) count) / total * 100) + "%");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null && fileReader != null)
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        for (StringANF stringANF:rawFunctions){
            StringANF beta = new StringANF(stringANF.getFunction());
            beta.invertFunction();
            log.info(stringANF.getFunction() + " # " + beta.getFunction());
        }
        log.info("Total time: " + Canteaut.formatTime(System.currentTimeMillis() - start0) + ". Remain of function: " + rawFunctions.size());
    }

    private static void validateNormal(StringANF function, List<List<Long>> gjb) {
        final int dimension = (int) function.getDimension();
        final int k = dimension >> 1;

        for (List<Long> currentGJBToValidate : gjb) {
            List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, dimension);
            for (long a : addditionalSpace) {
                long walsh = Canteaut.calculateWalsh(a, currentGJBToValidate, function);
                if ((walsh == (1 << k)) || (walsh == -(1 << k))) {
//                    log.info("Function is constant on " + Canteaut.makeOutput(currentGJBToValidate, a, dimension));
                    throw new RuntimeException("FAIL " + currentGJBToValidate + ", a = " + a);
                } else if (walsh != 0)
                    break;


            }
        }
    }

    private static void validateONormal(List<StringANF> functions, List<Long> currentGJBToValidate, int dimension) {
        final int k = dimension >> 1;
        Iterator<StringANF> itr = functions.iterator();
        List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, dimension);
        while (itr.hasNext()) {
            StringANF function = itr.next();
            try {
                for (long a : addditionalSpace) {
                    long walsh = Canteaut.calculateWalsh(a, currentGJBToValidate, function);
                    if ((walsh == (1 << k)) || (walsh == -(1 << k))) {
//                    log.info("Function is constant on " + Canteaut.makeOutput(currentGJBToValidate, a, dimension));
//                    throw new RuntimeException("FAIL " + currentGJBToValidate + ", a = " + a);
                        itr.remove();
                        throw new IllegalArgumentException("Is normal! ");
                    } else if (walsh != 0)
                        break;
                }
            } catch (IllegalArgumentException e) {
                log.info("Reduction of function: " + functions.size());
            }
        }
    }
}
