package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import ru.spb.samokhvalov.anf.CalculateWalsh;
import ru.spb.samokhvalov.anf.Canteaut;
import ru.spb.samokhvalov.anf.StringANF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * User: isamokhvalov
 * Date: 13.06.15
 * Time: 17:56
 */
@Log4j
public class ZhangXiaoAlgoritm {
    public static void main(String[] args) {
        final int n = 8;
        final int k = (n >> 1);
        final int maxCount = 1;
        long zero = System.currentTimeMillis();
        log.info("Start: n = " + n + ", k = " + k + ", maxCount = " + maxCount);
//        List<List<Long>> gjb = Canteaut.generateFastGJB(n, k);
        List<List<Long>> gjb = new ArrayList<>();

        int count = 0;
        long start = System.currentTimeMillis();
        log.info("Time to generate GJB: " + Canteaut.formatTime(start - zero) + ". Size = " + gjb.size());
//        Random random = new Random(start);
//        File functions = new File("~/function.txt");
//        functions.
//        InputStream f = new FileInputStream("~/function.txt");
        Scanner in = null;
        long total = 0;
        long totalBent = 0;
        try {
            in = new Scanner(new File("/home/isamokhvalov/function.txt"));

            while (in.hasNextLine()) {
//        while (count < maxCount) {
//        for (String f:Functions.functions){
                try {
//                StringANF function = Canteaut.generateRandomStringANF(n, random);
//                log.debug("Start: " + new Date(System.currentTimeMillis()) + ". AnfList.size() = " + function.getAnf().size() + ". Finding bent: " + totalBent);
                    String f = in.nextLine();
                    StringANF function = new StringANF(f.length() < 256 ? StringUtils.leftPad(f, 256, '0') : f);
                    function.invertFunction();
                    total++;
                    log.info("Start: " + new Date(System.currentTimeMillis()) + ". Total validate: " + total + ". Finding bent: " + totalBent + ". Finding nonnormal: " + count);
                    CalculateWalsh walsh = new CalculateWalsh(function);
                    if (walsh.isBent()) {
                        totalBent++;
                        validateNormal(function, gjb);
                        log.info("Normal: " + function.getFunction() + ". From file: " + f);
//                        log.info(function.getAllAnf());
//                        log.info(function.getAnf());
                        count++;
                    }
                } catch (Exception ignored) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                in.close();

        }


        log.info("Total time: " + Canteaut.formatTime(System.currentTimeMillis() - start) + ". All verify function: " + total + ". Bent function: " + totalBent + ". Find non-normal: " + count);
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
}
