package com.example.web_homework.Service;

import com.example.web_homework.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.web_homework.Dao.*;
import com.example.web_homework.Classes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Response signIn(String username, String password) {
        Response response = new Response();
        try {
            List<Integer> username_list = loginDao.Find(username);
            if (username_list.isEmpty()) {
                response.setCode(0);
                response.setMsg("no user found");
            } else {
                if (username_list.size() == 1) {
                    List<Integer> integerList = loginDao.SignIn(username, password);
                    if (integerList.size() != 1) {
                        response.setCode(0);
                        response.setMsg("password wrong");
                    } else {
                        response.setCode(1);
                        response.setMsg("success");

                        Map<String, Object> claims = new HashMap<>();
                        claims.put("hahaha", integerList.get(0));
                        String token = JwtUtils.generateJwt(claims);
                        response.setData(token);
                    }
                } else {
                    response.setCode(0);
                    response.setMsg("Sign In Failed: more than one user found");
                }
            }
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }

}