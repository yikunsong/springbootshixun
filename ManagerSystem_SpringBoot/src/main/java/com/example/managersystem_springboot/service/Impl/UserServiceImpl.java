package com.example.managersystem_springboot.service.Impl;

import com.example.managersystem_springboot.service.UserService;
import com.example.managersystem_springboot.mapper.UserMapper;
import com.example.managersystem_springboot.pojo.User;
import com.example.managersystem_springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public Integer delete(int id) {
        return userMapper.delete(id);
    }


    @Override
    public User get(int id) {
        return userMapper.get(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User selectUserById(int id){
        return userMapper.selectUserById(id);
    }

    @Override
    public boolean login(User user){
        String name = user.getUsername();
        String password = user.getPassword();
        User u1 =  userMapper.selectUserByName(name);
        if(u1==null){
            return false;
        }else{
            if(u1.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public boolean register(User user){
        int x = userMapper.insertUser(user);
        if(x > 0){
            return true;
        }else {
            return false;
        }
    }
}

