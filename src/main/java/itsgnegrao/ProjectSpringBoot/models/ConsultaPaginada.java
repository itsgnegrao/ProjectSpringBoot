package itsgnegrao.ProjectSpringBoot.models;

import java.io.Serializable;
import java.util.List;

public class ConsultaPaginada<T> implements Serializable {

    List<T> retornos;

    int subTotal;

    long total;

    long allRows;

    public ConsultaPaginada(List<T> retornos, int subTotal, long total, long allRows) {
        this.retornos = retornos;
        this.subTotal = subTotal;
        this.total = total;
        this.allRows = allRows;
    }

    public ConsultaPaginada() {
    }

    public List<T> getRetornos() {
        return retornos;
    }

    public void setRetornos(List<T> retornos) {
        this.retornos = retornos;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAllRows() {
        return allRows;
    }

    public void setAllRows(long allRows) {
        this.allRows = allRows;
    }
}
