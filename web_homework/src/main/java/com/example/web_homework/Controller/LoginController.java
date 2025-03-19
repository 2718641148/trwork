package com.example.web_homework.Controller;

import com.example.web_homework.Classes.LoginRequest;
import com.example.web_homework.Classes.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.web_homework.Service.LoginService;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@Controller
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Response SignIn(@RequestBody LoginRequest loginRequest) {
        return loginService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
    }

}
