package com.example.web_homework.Service;

import org.springframework.stereotype.Service;
import com.example.web_homework.Classes.Response;
@Service
public interface DepService {
    public Response getAllDeps();
    public Response deleteDep(int depId);
    public Response insertDep(String depName);
    public Response getDepById(int depId);
    public Response updateDep(int depId, String depName);

}
