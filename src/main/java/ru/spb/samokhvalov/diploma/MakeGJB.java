package ru.spb.samokhvalov.diploma;

import ru.spb.samokhvalov.anf.Canteaut;

/**
 * User: isamokhvalov
 * Date: 18.06.15
 * Time: 13:16
 */
public class MakeGJB {
    public static void main(String[] args) {
        final int n = Integer.parseInt(args[0]);
        final int k = Integer.parseInt(args[1]);
        System.out.println("Create GJB: n = " + n + ", k = " + k);
        long start = System.currentTimeMillis();
        String fileName = "/home/isamokhvalov/gjb.test." + n + "." + k;
        Canteaut.generateFastGJB(n, k, fileName);
        System.out.println("Finish. Total time: " + Canteaut.formatTime(System.currentTimeMillis() - start));
    }
}
