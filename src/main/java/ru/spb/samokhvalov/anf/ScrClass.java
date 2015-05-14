package ru.spb.samokhvalov.anf;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * User: isamokhvalov
 * Date: 11.05.15
 * Time: 21:19
 */
@Log4j
public class ScrClass {
    private List<Long> basisGJ;
    private long c;
    private StringANF function;
    private int dimension;

    private List<Long> basis = new ArrayList<Long>();
    private List<Long> basisVariable = new ArrayList<Long>();

    private List<Long> oBasis;
    private List<Long> oBasisVariable = new ArrayList<Long>();

    private List<Long> body = new ArrayList<Long>();
    ;

    public ScrClass(List<Long> basisGJ, String function, int c, int n) {
        if (!Canteaut.validateGJB(basisGJ, n))
            throw new RuntimeException("Error basis");
        this.basisGJ = basisGJ;
        this.function = new StringANF(function);
        this.c = c;
        this.dimension = n;

        init();
        fillBody();
    }

    public ScrClass(final ScrClass old, long added){
//        this.basisGJ = old.getBasisGJ();
        this.basisGJ = new ArrayList<>(old.getBasisGJ());
        this.basisGJ.add(added);

        this.function = old.getFunction();
        this.c = old.getC();
        this.dimension = old.getDimension();
        if (!Canteaut.validateGJB(basisGJ, this.dimension))
            throw new RuntimeException("Error basis");

        init();
        fillBody();

    }

    private void init() {
        for (long i : basisGJ)
            basis.add(Canteaut.nu(i, dimension));

        for (long i = 0; i < (1 << basis.size()); i++)
            basisVariable.add(Canteaut.mappingVectorValue(basis, i));

        oBasis = Canteaut.makeOBasis(basis, dimension);

        for (long i = 0; i < (1 << oBasis.size()); i++)
            oBasisVariable.add(Canteaut.mappingVectorValue(oBasis, i));
    }

    private void fillBody() {
        for (long a : oBasisVariable) {
            boolean add = true;
            for (long u : basisVariable) {
                final long position = (a ^ u) + 1;
                log.debug(" a: " + a + " u: " + u + " a ^ u: " + position);
                if (function.getValue(position) != c) {
                    add = false;
                    log.debug("break");
                    break;
                }
            }
            if (add) {
                log.debug("Add a: " + a);
                body.add(a);
            }
        }
    }

    public int getCapacity(){
        return body.size();
    }

    public boolean isEmpty(){
        return getCapacity() == 0;
    }

    public void clear(){
        body = new ArrayList<>();
    }

    public String getAllValue() {
        StringBuilder result = new StringBuilder();
        for (long i = 1; i <= (1 << dimension); i++)
            result.append(function.getValue(i));
        return result.toString();
    }


    public List<Long> getBasisVariable() {
        return basisVariable;
    }

    public List<Long> getBasisGJ() {
        return basisGJ;
    }

    public long getC() {
        return c;
    }

    public int getDimension() {
        return dimension;
    }

    public List<Long> getBasis() {
        return basis;
    }

    public List<Long> getoBasis() {
        return oBasis;
    }

    public List<Long> getoBasisVariable() {
        return oBasisVariable;
    }

    public List<Long> getBody() {
        return body;
    }

    public StringANF getFunction() {
        return function;
    }
}
