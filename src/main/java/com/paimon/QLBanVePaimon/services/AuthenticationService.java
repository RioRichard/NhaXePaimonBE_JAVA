package com.paimon.QLBanVePaimon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.requestModel.LoginModel;

@Service
public class AuthenticationService {
    
    @Autowired
    private ManagerRepository managerRepository;

    public Object getAdminToken(LoginModel loginModel) throws Exception {
        var user = managerRepository.getByUsername(loginModel.getUsername());
        if (user == null) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");
        }
        var hasedPass = Helper.hash256(user.getId()+loginModel.getPass());
        if (user.getPass() != hasedPass) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");
            
        }
        else{
            return user;
        }
        
    }
}
