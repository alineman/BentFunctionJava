package ru.spb.samokhvalov.diploma;

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
public class ZhangXiaoFull {
    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
        final int k = (n >> 1);
//        final String functionsFile = "functions.txt";
        final String functionsFile = args[1];

        final boolean invert = Boolean.parseBoolean(args[2]);
        final String gjbFile = "gjb.string." + n + "." + k;
//        final String gjbFile = args[2];
        final int maxLengthString = 1 << (n - 2);


        long zero = System.currentTimeMillis();
        System.out.println("Start: n = " + n + ", k = " + k);
        int count = 0;
        long total = Canteaut.countGJB(n, k);
        long start = System.currentTimeMillis();

        Scanner in = null;
        List<StringANF> rawFunctions = new ArrayList<>();
        try {
            in = new Scanner(new File(functionsFile));
            StringANF tempFunction;
            while (in.hasNextLine()) {
                final String stringFromFile = in.nextLine();
                tempFunction = new StringANF(stringFromFile.length() < maxLengthString ? StringUtils.leftPad(stringFromFile, maxLengthString, '0') : stringFromFile);
                if (invert)
                    tempFunction.invertFunction();
                CalculateWalsh walsh = new CalculateWalsh(tempFunction);
                if (walsh.isBent())
                    rawFunctions.add(tempFunction);
                else
                    System.out.println("Fount not bent: " + tempFunction.getFunction());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when reading file with list of functions");
        } finally {
            if (in != null)
                in.close();
        }
        System.out.println("Time to load functions: " + Canteaut.formatTime(start - zero) + ". Size = " + rawFunctions.size());

        long start0 = System.currentTimeMillis();

        File file = new File(gjbFile);
        Reader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null  && rawFunctions.size() > 0) {
                List<Long> currentGJB = Canteaut.convertStringToList(input);
                if (!Canteaut.validateGJB(currentGJB, n))
                    throw new RuntimeException("error read");
                validateONormal(rawFunctions, currentGJB, n);
                count++;
                if (count % 10000 == 0)
                    System.out.println("Complete: " + (((float) count) / total * 100) + "%");
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

        for (StringANF stringANF : rawFunctions) {
            StringANF beta = new StringANF(stringANF.getFunction());
            beta.invertFunction();
            System.out.println(stringANF.getFunction() + " # " + beta.getFunction());
        }
        System.out.println("Total time: " + Canteaut.formatTime(System.currentTimeMillis() - start0) + ". Remain of function: " + rawFunctions.size());
    }

    private static void validateNormal(StringANF function, List<List<Long>> gjb, int dimension) {
        final int k = dimension >> 1;

        for (List<Long> currentGJBToValidate : gjb) {
            List<Long> addditionalSpace = Canteaut.makeAdditionalSpace(currentGJBToValidate, dimension);
            for (long a : addditionalSpace) {
                long walsh = Canteaut.calculateWalsh(a, currentGJBToValidate, function);
                if ((walsh == (1 << k)) || (walsh == -(1 << k))) {
//                    System.out.println("Function is constant on " + Canteaut.makeOutput(currentGJBToValidate, a, dimension));
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
                        itr.remove();
                        throw new IllegalArgumentException(function.getFunction());
                    } else if (walsh != 0)
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Reduction of function: " + functions.size() + "\n" + e.getMessage());
            }
        }
    }
}
