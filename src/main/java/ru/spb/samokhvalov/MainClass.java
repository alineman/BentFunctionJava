package ru.spb.samokhvalov;


import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.ANF;

/**
 * Created by ivan on 09.05.14.
 */
@Log4j
public class MainClass {

    public static void main(String... args) {
        Integer bentFunction = 0;

        try {
            String input = args[0];
            bentFunction = Integer.parseInt(input, 16);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
        log.info("Input variable is a " + bentFunction);
        ANF variable = new ANF(bentFunction);
        variable.printHexNumber();
        variable.printDecNumber();
    }
}
