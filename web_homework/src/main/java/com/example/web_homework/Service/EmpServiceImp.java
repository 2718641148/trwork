package com.example.web_homework.Service;

import com.example.web_homework.Classes.EmpRequest;
import com.example.web_homework.Classes.GetEmp_response;
import com.example.web_homework.Classes.Response;
import com.example.web_homework.Dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImp implements EmpService {
    @Autowired
    private EmpDao empDao;


    @Override
    public Response getEmp(Integer page, Integer pageSize, String name,
                           Integer gender, String begin, String end) {
        Response resp = new Response();
        GetEmp_response getEmp = new GetEmp_response();
        try {
            getEmp.rows = empDao.getUsers(name,gender,begin,end,pageSize*page);
            getEmp.setTotal(getEmp.rows.size());
            resp.setData(getEmp);
            resp.setCode(1);
            resp.setMsg("success");
        }
        catch (Exception e) {
            resp.setCode(0);
            resp.setMsg(e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    @Override
    public Response deleteEmp(String ids) {
        Response resp = new Response();
        try {
            List<Integer> intList = Arrays.stream(ids.split(",")).map(String::trim).map(Integer::parseInt)
                    .collect(Collectors.toList());
            empDao.deleteEmp(intList);
            resp.setCode(1);
            resp.setMsg("success");
            resp.setData(null);
        }
        catch (Exception e) {
            resp.setCode(0);
            resp.setMsg(e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    @Override
    public Response insertEmp(EmpRequest empRequest) {
        Response resp = new Response();
        try{
            empDao.insertEmp(empRequest.getUsername(),empRequest.getName(),
                    empRequest.getGender(),empRequest.getImage(),empRequest.getDeptId(),
                    empRequest.getEntrydate(),empRequest.getJob());
            resp.setCode(1);
            resp.setMsg("success");
            resp.setData(null);
        }
        catch (Exception e) {
            resp.setCode(0);
            resp.setMsg(e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    @Override
    public Response getEmpById(int depId) {
        Response resp = new Response();
        try {
            resp.setData(empDao.selectEmpById(depId));
            resp.setCode(1);
            resp.setMsg("success");
        }
        catch (Exception e) {
            resp.setCode(0);
            resp.setMsg(e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    @Override
    public Response updateDep(EmpRequest empRequest) {
        Response resp = new Response();
        try {
            empDao.updateEmpById(empRequest.getUsername(),empRequest.getName(),empRequest.getGender()
            ,empRequest.getImage(),empRequest.getDeptId(),empRequest.getEntrydate(),empRequest.getJob());
            resp.setCode(1);
            resp.setMsg("success");
            resp.setData(null);
        }
        catch (Exception e) {
            resp.setCode(0);
            resp.setMsg(e.getMessage());
            resp.setData(null);
        }
        return resp;
    }
}
