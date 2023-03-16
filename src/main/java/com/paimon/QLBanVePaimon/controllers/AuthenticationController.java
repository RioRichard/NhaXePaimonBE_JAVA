package com.paimon.QLBanVePaimon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paimon.QLBanVePaimon.requestModel.LoginModel;
import com.paimon.QLBanVePaimon.services.AuthenticationService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("authen")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/admin")
    public ResponseEntity<Object> authAdmin(@RequestBody LoginModel loginModel) {
        
        try {
            var data = authenticationService.getAdminToken(loginModel);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "managers", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.UNAUTHORIZED, "managers", null);
            
        }
        

    }
    @PostMapping("")
    public ResponseEntity<Object> authUser(@RequestBody LoginModel loginModel) {
        
        try {
            var data = authenticationService.getAdminToken(loginModel);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "managers", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.UNAUTHORIZED, "managers", null);
            
        }
        

    }
}
