package com.paimon.QLBanVePaimon.sideModels;

public class Response<T> {
    private String status;
    private T data;
    private String message;

    

    public Response(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }


    public Response() {
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    

    
}
