package com.paimon.QLBanVePaimon.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.configs.JwtTokenUtil;
import com.paimon.QLBanVePaimon.models.Manager;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.repositories.UsersRepository;
import com.paimon.QLBanVePaimon.requestModel.LoginModel;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    ManagerDetailService managerDetailService;
    @Autowired
    CustomUserDetailService userDetailService;
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
    public Object getMe(HttpServletRequest request, LoginModel loginModel) throws Exception {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        String checkType = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                checkType = jwtTokenUtil.getTypeFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } 
        UserDetails userDetails = null;



        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (checkType.equals("manager")) {
                userDetails = this.managerDetailService.loadUserByUsername(username);

            } else {
                userDetails = this.userDetailService.loadUserByUsername(username);

            }
        }
        var roles = userDetails.getAuthorities().toArray();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isUser", roles.);
        map.put("status", status.value());
        return "ádsa";

    }

}
