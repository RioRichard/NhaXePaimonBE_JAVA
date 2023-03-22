package com.paimon.QLBanVePaimon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.configs.JwtTokenUtil;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.repositories.UsersRepository;
import com.paimon.QLBanVePaimon.requestModel.LoginModel;

@Service
public class AuthenticationService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    ManagerDetailService managerDetailService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String getAdminToken(LoginModel loginModel) throws Exception {
        var user = managerRepository.getByUsername(loginModel.getUsername());

        if (user == null) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");
        }
        var hasedPass = Helper.hash256(loginModel.getPass());
        if (!user.getPass().equals(hasedPass)) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");

        } else {
            final String username = user.getUsername();
            final UserDetails userDetails = managerDetailService.loadUserByUsername(username);
            return jwtTokenUtil.generateToken(userDetails,"manager");
        }

    }

    public String getUserToken(LoginModel loginModel) throws Exception {
        var user = usersRepository.getByUsername(loginModel.getUsername());

        if (user == null) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");
        }
        var hasedPass = Helper.hash256(loginModel.getPass());
        if (!user.getPassword().equals(hasedPass)) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");

        } else {
            final String username = user.getUsername();
            final UserDetails userDetails = managerDetailService.loadUserByUsername(username);
            return jwtTokenUtil.generateToken(userDetails,"user");
        }

    }

}
