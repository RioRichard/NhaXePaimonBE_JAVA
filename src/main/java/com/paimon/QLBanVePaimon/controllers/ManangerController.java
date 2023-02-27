package com.paimon.QLBanVePaimon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paimon.QLBanVePaimon.AppConstant;
import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Manager;
import com.paimon.QLBanVePaimon.services.ManagerService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;


@RestController
@RequestMapping("managers")
public class ManangerController {
    
    @Autowired
    private ManagerService managerService;
    
    @GetMapping
    public ResponseEntity<Object> getAll(
        @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
        @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {
        
        try {
            Pageable pageable = PageRequest.of(page-1, size);
            var data = managerService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "managers", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "managers", null);
            
        }
        
        
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = managerService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "managers", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "managers", null);

        }
    }
    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Manager manager) {
        var res = managerService.add(manager);
        return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "managers", res);
        
    }

    // @GetMapping("/test")
    // public Object testMethod() {

        


    //     return Helper.hash256("63fcd8afea76fd03d8bc25d7" + "12345");
    // }
    
}
