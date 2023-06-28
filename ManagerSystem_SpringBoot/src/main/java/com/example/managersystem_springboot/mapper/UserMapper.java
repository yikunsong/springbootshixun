package com.example.managersystem_springboot.mapper;

import com.example.managersystem_springboot.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据访问层
 */
@Mapper
public interface UserMapper {

    //查询全部
    @Select("select * from user")
    List<User> findAll();

    //新增数据
    @Insert(" insert into  user( username,password ) values (#{username},#{password}) ")
    public int save(User user);

    //删除数据
    @Delete(" delete from user where id= #{id} ")
    public int delete(int id);

    //根据id查找
    @Select("select * from user where id= #{id} ")
    public User get(int id);

    //更新数据
    @Update("update user set username=#{username},password=#{password} where id=#{id} ")
    public int update(User user);

    //根据账号查找
    @Select("select * from user where id= #{id} ")
    public User selectUserById(int id);

    //根据用户查找
    @Select("select * from user where username= #{username} ")
    public User selectUserByName(String username);

    //新增用户
    @Insert(" insert into  user( username,password ) values (#{username},#{password}) ")
    public int insertUser(User user);
}

