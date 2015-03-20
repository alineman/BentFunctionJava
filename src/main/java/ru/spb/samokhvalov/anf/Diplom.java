package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * User: isamokhvalov
 * Date: 14.03.15
 * Time: 20:39
 */
@Log4j
public class Diplom {

    final static int degree = 4;
    static final long pow = (long) Math.pow(2, degree);

    public static void main(String[] args) {
        ANF anf = null;
        LinearSubDimension subDimension = new LinearSubDimension(degree / 2, degree);
        Map<Integer, List<Integer>> allValues = subDimension.getAllValues();
        log.info("Hello!");
        long count = 0;
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter("data.csv", "UTF-8");
        for (int i = 0; i < Math.pow(2, pow); i++) {
//            if ((i & 3) == 3) {
//                log.info(i);
//                count++;
//            }
            final int i3  = 0b0000_0000_0000_1111;
            final int i5  = 0b0000_0000_0011_0011;
            final int i6  = 0b0000_0000_0011_0101;
            final int i9  = 0b0000_0011_0000_0011;
            final int i10 = 0b0000_0101_0000_0101;
            final int i12 = 0b0001_0001_0001_0001;
            int k = i & i12;
//            anf = new ANF(i, degree);
            anf = new ANF(k, degree/2);
//            if (anf.getPow() == 4l) {
//            if (anf.getNonlinear() == 0 ) {
//            if (anf.isBent()) {

            if (anf.getPow() == 2 ) {
//                pWriter.println(anf.getInvertNumber());
                pWriter.println(i);
//                pWriter.println(anf.getAnfAsNumber());
//                log.info(i + " " + anf.getAnfAsNumber() + " " + anf.getInvertNumber() + " " + anf.getPow());
                log.info(i);
                count++;
            }
        }

        pWriter.close();
        log.info("Total count: " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
