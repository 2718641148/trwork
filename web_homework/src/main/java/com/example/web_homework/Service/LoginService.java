package com.example.web_homework.Service;

import com.example.web_homework.Classes.Response;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public Response signIn(String username, String password);
}