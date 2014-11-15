package ru.spb.samokhvalov;


import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import ru.spb.samokhvalov.anf.ANF;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class MainClass {
        final static int degree = 4;
        static final long pow = (long) Math.pow(2, degree);
//        static final BigInteger pow = new BigInteger("2").pow(degree);

    public static void main(String... args) {
//        Integer bentFunction = 0;
//
//        try {
//            String input = args[0];
//            bentFunction = Integer.parseInt(input, 16);
//        } catch (IndexOutOfBoundsException | NumberFormatException e) {
//            e.printStackTrace();
//        }
//        log.info("Input variable is a " + bentFunction);
//        ANF variable = new ANF(bentFunction);
//        variable.printHexNumber();
//        variable.printDecNumber();
        int k = 0;
//        int l = 0;
        long f = 0;
        List<String> k1 = new ArrayList<>();

        try {
            PrintWriter pWriter = new PrintWriter("data.csv", "UTF-8");

        for (long i = 0; i < Math.pow(2, pow); i++) {
//            BigInteger i = new BigInteger("0");
//        do {
            ANF temp = new ANF(i, degree);
            boolean isBent = temp.isBent();
            if (isBent) {
//                for (TableOfTrue j : temp.getTableOfTrue())
//                    log.info(j.getVariableString() + " | " + j.getValue());
//                log.info("\n");
//                for (TableOfTrue t : temp.getTableOfTrue()) {
//                    int value = (t.getValue()) ? 1 : 0;
//                    log.info(t.getVariableString() + " | " + value);
//                    resultNumber += value << i;
                k1.add(converter(temp.getNumber()));

//            int nonlinear = temp.getNonlinear();
//            if (nonlinear == 2)
//            if(isBent)  {
//            if ( wt(i) == 10 ||  wt(i) == 6 ) {
//                f++;
//                if (!isBent)
//                    System.out.printf("%16s\n", Long.toBinaryString(i));
//                System.out.println(nonlinear + " " + Long.toBinaryString(i) + " " + wt(i) + " " +temp.getHumanAnf());
//                System.out.println(nonlinear + " " + " " +temp.getHumanAnf());
//                System.out.println(i, (long) Long.MAX_VALUE * Math.pow(2,pow));
//            }
//                int [] wals = temp.getWalshW();
//            Arrays.sort(wals);
//                pWriter.print(i + ";");
//                String binaryView = StringUtils.leftPad(Long.toBinaryString(i), (int) pow, "0");

             System.out.println(i);
                boolean normal = temp.isNormal();
                if (!normal) {
                    pWriter.println(i);
                    f++;
                }
//                System.out.println("i: " + i + " " + binaryView + " " + normal);
//                String[] split = binaryView.split("");
//                for (int l = 1; l < split.length; l++){
//                    System.out.printf("%20d", pow);
//                    System.out.printf("%10d\n", i);
//                    pWriter.print(split[l] + ";");
//                }
//                pWriter.println(i);
//                System.out.print("\n");

//                System.out.print(Long.toHexString(temp.getNumber()) + ", ");
//                for (String s : temp.getAnf()) {
//                    log.info(s);
//                }
//                log.info("\n");
                k++;
            }
//            System.out.println(StringUtils.leftPad(Long.toBinaryString(i), (int) pow, "0"));
        }
//            i = i.add(BigInteger.ONE);
//        } while(i.longValue() < Long.MAX_VALUE);

//        String[] str = new String()[];
//        k1.toArray();
//        Arrays.sort(str);
//        java.util.Collections.sort(k1)  

            pWriter.close();
        log.info("Total found bent-functions: " + k);
        log.info("Total found \"bent\"-functis: " + f);

//        for (String sss: k1){
//            System.out.print(sss + ", ");
//        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String converter(long l){
//        System.out.println(l);
        long t =0;
        long pow = (long) MainClass.pow;
        for (int i = 0; i< pow; i++){
            if (((l>>i) & 1) == 1){
                t+=(long) Math.pow(2,pow-i);
            }
        }
        return Long.toHexString(t);
    }

    private static int wt(long l){
        String binaryString = Long.toBinaryString(l);
        return binaryString.replace("0","").length();
    }
}
