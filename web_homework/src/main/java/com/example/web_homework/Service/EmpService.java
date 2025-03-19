package com.example.web_homework.Service;

import com.example.web_homework.Classes.EmpRequest;
import com.example.web_homework.Classes.Response;
import org.springframework.stereotype.Service;

@Service
public interface EmpService {
    public Response getEmp(Integer page, Integer pageSize,String name, Integer gender, String begin, String end);
    public Response deleteEmp(String ids);
    public Response insertEmp(EmpRequest empRequest);
    public Response getEmpById(int depId);
    public Response updateDep(EmpRequest empRequest);
}
