package com.paimon.QLBanVePaimon.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.requestModel.LoginModel;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;

    public Object getAdminToken(LoginModel loginModel) throws Exception {
        var user = managerRepository.getByUsername(loginModel.getUsername());
        if (user == null) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");
        }
        var hasedPass = Helper.hash256(user.getId() + loginModel.getPass());
        if (user.getPass() != hasedPass) {
            throw new Exception("Username hoặc mật khẩu bị sai. Kiểm tra lại");

        } else {
            return loadUserByUsername(loginModel.getUsername());
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var user = managerRepository.getByUsername(username);
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

            return new User(user.getUsername(), user.getPass(), roles);
        } catch (Exception e) {
            return null;
        }
    }
}
