package com.ihrm.company.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin  //TODO 解决跨域问题
@RestController
@RequestMapping(value = "/str")
public class StringController {

    public static void main2(String[] args) {
        String a = "1,2@1=2--";
        System.out.println("字符串替换"+a.replace("2", "a"));  //结果1a1a
        System.out.println("首次出现替换"+a.replaceFirst("2", "a"));//  结果1a12
        System.out.println("多个正则替换"+a.replaceAll("[@,=]","9"));
    }

    public static void main(String[] args) {
        String a = "ssS234";
        
    }
}
