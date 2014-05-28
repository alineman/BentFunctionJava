package ru.spb.samokhvalov.anf;

import org.apache.commons.lang3.StringUtils;

/**
 * User: isamokhvalov
 * Date: 14.05.14
 * Time: 21:10
 */
public class TableOfTrue {
    private long variable;
    private boolean value;
    private int size=0;

    public TableOfTrue(TableOfTrue tableOfTrue){
        this.variable = tableOfTrue.getVariable();
        this.value = tableOfTrue.getValue();
        this.size = tableOfTrue.getSize();
    }

    public TableOfTrue(long variables, boolean value, int n) {
        this.variable = variables;
        this.value = value;
        this.size = n;
    }
    @Deprecated
    public TableOfTrue(long variables, boolean value) {
        this.variable = variables;
        this.value = value;
    }
    public Long getVariable() {
        return variable;
    }

    public String getVariableString(){
        return StringUtils.leftPad(Long.toBinaryString(variable), size, '0');
    }

    public int getSize() {
        return size;
    }

    public boolean getValue() {
        return value;
    }
}
