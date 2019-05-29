package com.example.demo.controller;


import com.example.demo.model.Admin;
import com.example.demo.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @ApiOperation(value = "用户登录接口")
    @PostMapping("")
    public String login(@RequestBody Admin admin){
        System.out.println(admin.getUserName());
        System.out.println(admin.getPassWord());
        if(admin.getUserName().equals("ustc") && admin.getPassWord().equals("nsrl123")) {
            String token = JWTUtil.sign(admin.getUserName(), admin.getPassWord());
            return token;
        }else{
            return "failed";
        }
    }
}
