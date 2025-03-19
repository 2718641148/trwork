package com.example.web_homework.Controller;

import com.example.web_homework.Classes.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.web_homework.Service.DepService;
import com.example.web_homework.Classes.DepRequest;
@ResponseBody
@Controller
@CrossOrigin
public class DepController {
    @Autowired
    private DepService depService;
    @GetMapping("/depts")
    public Response deps() {
        return depService.getAllDeps();
    }
    @DeleteMapping("/depts/{id}")
    public Response deleteDep(@PathVariable int id) {
        return depService.deleteDep(id);
    }
    @PostMapping("/depts")
    public Response insertDep(@RequestBody String name){
        return depService.insertDep(name);
    }
    @GetMapping("/depts/{id}")
    public Response getDep(@PathVariable int id) {
        return depService.getDepById(id);
    }
    @PutMapping("/depts")
    public Response updateDep(@RequestBody DepRequest depRequest){
        return depService.updateDep(depRequest.getId(), depRequest.getName());
    }
}
