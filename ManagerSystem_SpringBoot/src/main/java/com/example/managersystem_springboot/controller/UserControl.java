package com.example.managersystem_springboot.controller;

import com.example.managersystem_springboot.pojo.User;
import com.example.managersystem_springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 控制层，导入service层
 */
@Controller
public class UserControl {
    @Resource
    private UserService userService;


    //设置默认访问页
    @RequestMapping("/")
    public String hello(){
        return "login.html";
    }

    //-----------------------------登录功能
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/result") //登录结果跳转
    public String result(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean flag = userService.login(user);

        if(flag==true){
            return "redirect:/list";
        }else {
            return "loginerror";
        }
    }

    @GetMapping("/loginerror") //登录错误
    public String loginerror(){
        return "login";
    }

    //-----------------------------注册功能
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/registerend") //注册结果跳转
    public String registerend(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = userService.register(user);
        if(flag==true){
            return "registersuccess";
        }else{
            return "registererror";
        }
    }

    //查询全部
    @RequestMapping("/list")
    public String userList(Model model){
        List<User> user = userService.findAll();
        model.addAttribute("user",user);
        return "index";
    }

    //新增数据
    @RequestMapping("/add")
    public String save(User user){
        userService.save(user);
        return "redirect:/list";
    }
    @RequestMapping("/useradd")
    public String add(){
        return "add";
    }

    //删除数据
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletResponse servletResponse) throws IOException {
        int count =userService.delete(id);
        if(count==1){
            servletResponse.sendRedirect("/list");
        }
        return "error";
    }

    //根据id查找
    @GetMapping("/updatePage/{id}")
    public String updatePage(Model model,@PathVariable int id){
        User users = userService.get(id);
        model.addAttribute("users",users);
        return "update";
    }

    //更新数据
    @PostMapping("/update")
    public String updateUser(Model model,User user,HttpServletRequest request){
        String id = request.getParameter("id");
        User userById = userService.get(Integer.parseInt(id));
        userService.update(user);
        System.out.println(user);
        return "redirect:/list";
    }

}

