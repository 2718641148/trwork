package com.example.web_homework.Dao;

import com.example.web_homework.Classes.DepList;
import org.apache.ibatis.annotations.*;
import com.example.web_homework.Classes.*;

import java.util.List;

@Mapper
public interface DepDao {
    @Insert("INSERT INTO dept (name, create_time) VALUES (#{name}, #{create_time})")
    void InsertDep(String depName, String createTime);
    @Delete("DELETE FROM dept WHERE id=#{id}")
    void DeleteDep(int id);
    @Select("SELECT * from dept")
    List<DepList> GetAllDeps();
    @Select("SELECT * from dept where id=#{id}")
    List<DepList> GetDepsId(int id);
    @Update("UPDATE dept SET name=#{name} where id=#{id}")
    void UpdateDepName(int id, String name);
}
