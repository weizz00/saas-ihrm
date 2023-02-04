package com.ihrm.common.controller;

import com.ihrm.common.entity.CompanyModel;
import com.ihrm.common.entity.UserModel;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    @ModelAttribute
    public void setResAnReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.companyId = "1";
        this.companyName  = "传智播客";
    }

    public static void main(String[] args) {
        List<UserModel> list = list();
        System.out.println(list);
    }

    private static List<UserModel> list() {
        List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserModel userModel = new UserModel();
            userModel.setId("1"+i);
            userModel.setName("小明"+i);
            userModel.setAge(10+i);
            userModels.add(userModel);
        }

        List<CompanyModel> companyModels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CompanyModel companyModel = new CompanyModel();
            companyModel.setId("1"+i);
            companyModel.setName("小明"+i);
            companyModel.setDapt("信息管理部"+i);
            companyModels.add(companyModel);
        }
        List<UserModel> list = userModels.stream().map(m -> {
            companyModels.stream().filter(m2-> Objects.equals(m.getId(),m2.getId()) && Objects.equals(m.getName(),m2.getName()) ).forEach(m2-> {
                m.setDept(m2.getDapt());
            });
            return m;
        }).collect(Collectors.toList());

        return list;
    }

}
