package com.example.webflux.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.annonation.CurrentUser;
import com.example.domain.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/test")
    public void test(@CurrentUser UserBean userBean){

        System.out.println(JSONObject.toJSONString(userBean));

    }
}
