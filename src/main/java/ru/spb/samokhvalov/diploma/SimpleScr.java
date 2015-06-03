package ru.spb.samokhvalov.diploma;

import lombok.extern.log4j.Log4j;
import ru.spb.samokhvalov.anf.Canteaut;

import java.util.ArrayList;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 26.05.15
 * Time: 13:24
 */
@Log4j
public class SimpleScr {

    String function;
    int dimension;


    public List<Long> getuBasis() {
        return uBasis;
    }

    List<Long> uBasis;
    List<Long> value;
    List<Long> oBasisVariable;

    public SimpleScr(List<Long> uBasis, String function, int dimension) {
        if (!Canteaut.validateGJB(uBasis, dimension))
            throw new RuntimeException("Error basis");

        this.function = function;
        this.dimension = dimension;
        this.uBasis = uBasis;
        List<Long> tempBasis = new ArrayList<>();

        for (long k: uBasis)
            tempBasis.add(Canteaut.nu(k, dimension));
        oBasisVariable = Canteaut.fillVariable(Canteaut.makeOBasis(tempBasis,dimension), dimension);
//        for (int i = 0; i < tempBasis.size(); i++)
//            tempOBasis.add((long) (1 << (dimension - tempBasis.get(i))));

//        log.info("");
    }

    public List<Long> getOBasisVariable() {
        return oBasisVariable;
    }

    public List<Long> getValue() {
        if (value == null)
            value = new ArrayList<>();
        return value;
    }

    public void clear(){
        value = new ArrayList<>();
    }

    public int getCapacity(){
        return value.size();
    }

    public boolean isEmpty(){
        return getCapacity() == 0;
    }

    public long getLastU(){
        return uBasis.get(uBasis.size() - 1);
    }
}
