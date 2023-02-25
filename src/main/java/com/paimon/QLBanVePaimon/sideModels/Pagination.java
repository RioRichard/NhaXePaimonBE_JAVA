package com.paimon.QLBanVePaimon.sideModels;

import org.springframework.data.domain.Page;

public class Pagination<T> {

    private int current;
    private int total;
    private int total_element;
    public Pagination(Page<T> page) {
        current = page.getNumber();
        total = page.getTotalPages();
        total_element = page.getNumberOfElements();

    }
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal_element() {
        return total_element;
    }
    public void setTotal_element(int total_element) {
        this.total_element = total_element;
    }

    

}
