package com.example.web_homework.Classes;

import java.util.List;

public class GetEmp_response {
    private Integer total=0;
    public List<EmpList> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
