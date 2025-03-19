package com.example.web_homework.Dao;

import com.example.web_homework.Classes.EmpList;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface EmpDao {
    @Select("<script>" +
            "SELECT * FROM emp WHERE 1=1" +
            "<if test='name != null'> AND username = #{name}</if>" +
            "<if test='gender != null'> AND gender = #{gender}</if>" +
            "<if test='begin != null'> AND date &gt;= #{begin}</if>" +
            "<if test='end != null'> AND date &lt;= #{end}</if>" +
            " LIMIT #{num}" +
            "</script>")
    List<EmpList> getUsers(@Param("name") String name,
                           @Param("gender") Integer gender,
                           @Param("begin") String begin,
                           @Param("end") String end,
                           @Param("pageSize") int num);
    @Delete("<script>DELETE from emp WHERE id IN <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    void deleteEmp(@Param("ids") List<Integer> ids);
    @Insert("<script>INSERT INTO emp (username, name, gender" +
            "<if test='image != null'>,image</if> " +
            "<if test='deptId != null'>,deptId</if> " +
            "<if test='entrydate != null'>,entrydate </if>" +
            "<if test='job != null'>,job</if>)" +
            "VALUES (#{username},#{name}" +
            "<if test='gender != null'>,#{gender}</if>" +
            "<if test='image != null'>,#{image}</if>" +
            "<if test='deptId != null'>,#{deptId}</if>" +
            "<if test='entrydate != null'>,#{entrydate}</if>" +
            "<if test='job != null'>,#{job}</if>)</script>")
    void insertEmp(@Param("username") String username,
                   @Param("name") String name,
                   @Param("gender") Integer gender,
                   @Param("image") String image,
                   @Param("deptId") Integer deptId,
                   @Param("entrydate") String entrydate,
                   @Param("job") Integer job
                   );
    @Select("SELECT * from emp WHERE id=#{id}")
    EmpList selectEmpById(@Param("id") Integer id);
    @Update("UPDATE dept SET (username=#{username}, name=#{name}, gender=#{gender}" +
            "<if test='image != null'>,image=#{image}</if>" +
            "<if test='deptId != null'>,deptId=#{deptId}</if>" +
            "<if test='entrydate != null'>,entrydate=#{entrydate} </if>" +
            "<if test='job != null'>,job=#{job}</if>) where id=#{id}")
    void updateEmpById(@Param("username") String username,
                       @Param("name") String name,
                       @Param("gender") Integer gender,
                       @Param("image") String image,
                       @Param("deptId") Integer deptId,
                       @Param("entrydate") String entrydate,
                       @Param("job") Integer job
                        );
}
