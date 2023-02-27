package com.paimon.QLBanVePaimon.requestModel;

public class PatchRequest<T> {
    
    private String[] propChanging;
    private T data;
    public PatchRequest(String[] propChanging, T data) {
        this.propChanging = propChanging;
        this.data = data;
    }
    public String[] getPropChanging() {
        return propChanging;
    }
    public void setPropChanging(String[] propChanging) {
        this.propChanging = propChanging;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
    

}
