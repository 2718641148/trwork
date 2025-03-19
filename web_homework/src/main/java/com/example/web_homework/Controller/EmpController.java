package com.example.web_homework.Controller;

import com.example.web_homework.Classes.EmpRequest;
import com.example.web_homework.Classes.Response;
import com.example.web_homework.Oss;
import com.example.web_homework.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@ResponseBody
@Controller
@CrossOrigin
public class EmpController {
    @Autowired
    EmpService empService;
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        Oss oss = new Oss();
        return oss.uploadFile(file);
    }

    @GetMapping("/emps")
    public Response search(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "gender", required = false) Integer gender,
                           @RequestParam(value = "begin", required = false) String begin,
                           @RequestParam(value = "end", required = false) String end,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return empService.getEmp(page, pageSize, name, gender, begin, end);
    }
    @DeleteMapping("/emps/{ids}")
    public Response delete(@PathVariable("ids") String ids) {
        return empService.deleteEmp(ids);
    }
    @PostMapping("/emps")
    public Response add(@RequestBody EmpRequest empRequest){
        return empService.insertEmp(empRequest);
    }
    @GetMapping("/emps/{id}")
    public Response get(@PathVariable("id") Integer id) {
        return empService.getEmpById(id);
    }
    @PutMapping("/emps")
    public Response update(@RequestBody EmpRequest empRequest) {
        return empService.updateDep(empRequest);
    }
}
