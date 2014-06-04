package ru.spb.samokhvalov;


import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.ANF;
import ru.spb.samokhvalov.anf.TableOfTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class MainClass {

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
        int l = 0;
        List<String> k1 = new ArrayList<>();
        for (int i = 0; i < 65536; i++) {
            ANF temp = new ANF(i, 4);
            if (temp.isBent()) {
//                for (TableOfTrue j : temp.getTableOfTrue())
//                    log.info(j.getVariableString() + " | " + j.getValue());
//                log.info("\n");
//                for (TableOfTrue t : temp.getTableOfTrue()) {
//                    int value = (t.getValue()) ? 1 : 0;
//                    log.info(t.getVariableString() + " | " + value);
//                    resultNumber += value << i;
                l++;
                k1.add(converter(temp.getNumber()));
//                System.out.print(Long.toHexString(temp.getNumber()) + ", ");
//                for (String s : temp.getAnf()) {
//                    log.info(s);
//                }
//                log.info("\n");
                k++;
            }
        }

//        String[] str = new String()[];
//        k1.toArray();
//        Arrays.sort(str);
        java.util.Collections.sort(k1);

        log.info("Total found bent-functions: " + k);

        for (String sss: k1){
            System.out.print(sss + ", ");
        }
    }

    private static String converter(long l){
//        System.out.println(l);
        long t =0;
        for (int i = 0; i<16; i++){
            if (((l>>i) & 1) == 1){
                t+=Math.pow(2,15-i);
            }
        }
        return Long.toHexString(t);
    }
}
