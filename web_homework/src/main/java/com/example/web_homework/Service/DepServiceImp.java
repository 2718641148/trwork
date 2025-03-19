package com.example.web_homework.Service;

import com.example.web_homework.Classes.DepList;
import com.example.web_homework.Classes.Response;
import com.example.web_homework.Dao.DepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepServiceImp implements DepService {
    @Autowired
    DepDao depDao;

    @Override
    public Response getAllDeps() {
        Response response = new Response();
        List<DepList> list = depDao.GetAllDeps();
        try {
            response.setData(list);
            response.setCode(1);
            response.setMsg("success");
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }
    @Override
    public Response deleteDep(int depId){
        Response response = new Response();
        try {
            depDao.DeleteDep(depId);
            response.setCode(1);
            response.setMsg("success");
            response.setData(null);
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }
    @Override
    public Response insertDep(String name){
        Response response = new Response();
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            depDao.InsertDep(name,currentDateTime.toString());
            response.setCode(1);
            response.setMsg("success");
            response.setData(null);
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }
    @Override
    public Response getDepById(int id){
        Response response = new Response();
        try {
            DepList depList = depDao.GetDepsId(id).get(0);
            response.setCode(1);
            response.setMsg("success");
            response.setData(depList);
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response updateDep(int depId, String depName) {
        Response response = new Response();
        try {
            depDao.UpdateDepName(depId,depName);
            response.setCode(1);
            response.setMsg("success");
            response.setData(null);
        }
        catch (Exception e) {
            response.setCode(0);
            response.setMsg(e.getMessage());
            response.setData(null);
        }
        return response;
    }
}
