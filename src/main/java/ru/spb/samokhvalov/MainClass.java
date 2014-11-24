package ru.spb.samokhvalov;


import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.ANF;
import ru.spb.samokhvalov.anf.LinearSubDimension;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class MainClass {
    final static int degree = 4;
    static final long pow = (long) Math.pow(2, degree);
    final static int[] affine4 = {
            0, 255,
            3855, 4080, 13107, 13260,
            15420, 15555, 21845, 21930,
            23130, 23205, 26214, 26265,
            26985, 27030, 38505, 38550,
            39270, 39321, 42330, 42405,
            43605, 43690, 49980, 50115,
            52275, 52428, 61455, 61680,
            65280, 65535};

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
        int subDimension = ((degree % 2) == 0) ? (degree / 2) : ((degree / 2) + 1);
        LinearSubDimension linearSubDimension = new LinearSubDimension(subDimension, degree);
        log.info(linearSubDimension.getAllValues());
        Set keys = linearSubDimension.getNewValues().keySet();
        Iterator itr2 = keys.iterator();
        while (itr2.hasNext()){
            Integer next = (Integer)itr2.next();
//            List<Integer> valuesNormalA = linearSubDimension.getAllValues().get(next);
//            int sum = 0;
            int sum = linearSubDimension.getNewValues().get(next);;
//            for (int ff:valuesNormalA)
//            sum+=ff;
            ANF del = new ANF( sum, degree);
            log.info(next + " " + sum + " " +del.getHumanAnf());
        }
        try {
            PrintWriter pWriter = new PrintWriter("data.csv", "UTF-8");

            for (long i = 0; i < Math.pow(2, pow); i++) {
//            BigInteger i = new BigInteger("0");
//        do {
                ANF temp = new ANF(i, degree);
            boolean isBent = temp.isBent();
//            if (isBent) {
//                for (TableOfTrue j : temp.getTableOfTrue())
//                    log.info(j.getVariableString() + " | " + j.getValue());
//                log.info("\n");
//                for (TableOfTrue t : temp.getTableOfTrue()) {
//                    int value = (t.getValue()) ? 1 : 0;
//                    log.info(t.getVariableString() + " | " + value);
//                    resultNumber += value << i;
                k1.add(converter(temp.getNumber()));
//
//                int[] vvaluues = new int[32];
//                for (int j =0 ; j<affine4.length; j++){
//                    vvaluues[j] = Long.bitCount(affine4[j] ^ i);
//                }
//                Arrays.sort(vvaluues);
//                if (temp.getPow() <= 1 ){
//                    f++;
//                    pWriter.println(i);
//                log.info("$" + i + "_{10}$ & $" + StringUtils.leftPad(Long.toBinaryString(i), 16, "0") + "$ & $" + temp.getHumanAnf() + "$&" + Long.bitCount(i) +"\\\\");
//}
//       log.info(temp.getHumanAnf() + " " + temp.getPow());
//                if (temp.getPow() <= 1) {
//                    log.info(i);
//                    pWriter.println(i);
////                log.info(temp.getHumanAnf() + " " + temp.getPow());
//                    f++;
//                }
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

//             System.out.println(i);
//                int x = 854;
//                for(int i = 0; i<16;i++){
//                    int temp = 1<<i;
//                    System.out.println((x&temp)>0 ? 1 : 0);
//                }

                int normal = linearSubDimension.validateNormality1((int) i);
                if (normal>0) {
//                    log.info(i);
//                    pWriter.println(i + ";" + normal);
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
//            }
//            System.out.println(StringUtils.leftPad(Long.toBinaryString(i), (int) pow, "0"));
        }
//            i = i.add(BigInteger.ONE);
//        } while(i.longValue() < Long.MAX_VALUE);

//        String[] str = new String()[];
//        k1.toArray();
//        Arrays.sort(str);
//        java.util.Collections.sort(k1)  
//            Set<Integer> normal256 = new HashSet<>();
//            for (int m = 0; m < 256; m++) {
//                if (
//                        (Integer.bitCount(m & 60) == 4) ||
//                                (Integer.bitCount(m & 90) == 4) ||
//                                (Integer.bitCount(m & 102) == 4) //||
////                                (Integer.bitCount(m & 195) == 4) ||
////                                (Integer.bitCount(m & 165) == 4) ||
////                                (Integer.bitCount(m & 153) == 4)
//                        )
//                    normal256.add(m);
//            }
//            Iterator<Integer> itr = normal256.iterator();
//            while(itr.hasNext())    {
//                pWriter.write(itr.next() + "\n"); }
            pWriter.close();
            log.info("Total found bent-functions: " + k);
            log.info("Total found normal \"bent\"-functis: " + f);

//        for (String sss: k1){
//            System.out.print(sss + ", ");
//        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String converter(long l) {
//        System.out.println(l);
        long t = 0;
        long pow = (long) MainClass.pow;
        for (int i = 0; i < pow; i++) {
            if (((l >> i) & 1) == 1) {
                t += (long) Math.pow(2, pow - i);
            }
        }
        return Long.toHexString(t);
    }

    private static int wt(long l) {
        String binaryString = Long.toBinaryString(l);
        return binaryString.replace("0", "").length();
    }

//    int dim = 3;
//    int x = 30856;
//    final int mask = (1<<(dim+1))-1;
//    System.out.println("mask: " + Integer.toBinaryString(mask));
//    Integer.toBinaryString(mask);
////for(int i = 0; i<16;i++){
////    int temp = 1<<i;
////    System.out.println((x&temp)>0 ? 1 : 0);
////}
//    for (int i =1; i<dim; i++){
//        for (int j =i+1; j <=dim; j++){
////        System.out.println("i: "+ i + " j " + j);
//            int tempmask = (1<<(dim-i)) + (1<<(dim-j));
//            int
//            System.out.println(Integer.toBinaryString(tempmask^mask));
//
//        }
//    }
////System.out.println(0b1010^0b1111);


}
