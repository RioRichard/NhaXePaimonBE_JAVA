package com.paimon.QLBanVePaimon.sideModels;

import java.util.List;

import org.springframework.data.domain.Page;

public class ListData<T> {

    private Pagination<T> pagination;

    private List<T> dataList;

    public Pagination<T> getPagination() {
        return pagination;
    }

    public void setPagination(Pagination<T> pagination) {
        this.pagination = pagination;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public ListData(Page<T> data) {
       
        this.pagination = new Pagination<T>(data);
        this.dataList = data.getContent();
        
    }

}
