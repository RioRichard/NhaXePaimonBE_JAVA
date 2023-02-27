package com.paimon.QLBanVePaimon.sideModels;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> generateMessage(String message, HttpStatus status, String objectName,
            Object responseObject) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        if (responseObject != null) {
            Map<String, Object> dataValue = new HashMap<String, Object>();
            if (objectName == null || objectName.isEmpty() || objectName.trim().isEmpty()) {
                objectName = responseObject.getClass().getName().toLowerCase();

            }

            if (responseObject.getClass().getSimpleName().equals("ListData")) {
                try {
                    var getMethodPagination = responseObject.getClass().getDeclaredMethod("getPagination",
                            (Class<?>[]) null);
                    var getMethodData = responseObject.getClass().getDeclaredMethod("getDataList", (Class<?>[]) null);

                    dataValue.put("pagination", getMethodPagination.invoke(responseObject));
                    dataValue.put(objectName, getMethodData.invoke(responseObject));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else {
                dataValue.put(objectName, responseObject);
            }
            map.put("data", dataValue);
        } else
            map.put("data", null);

        return new ResponseEntity<Object>(map, status);

    }

   

}
