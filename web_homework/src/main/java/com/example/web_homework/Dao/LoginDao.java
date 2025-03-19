package com.example.web_homework.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LoginDao {
    @Select("select id from emp where username=#{username} and password=#{password}")
    public List<Integer> SignIn(String username, String password);
    @Select("select id from emp where username=#{username}")
    public List<Integer> Find(String username);
}
