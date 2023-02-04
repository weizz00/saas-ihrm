package com.ihrm.company.controller;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.MultiInsertUtil;
import com.ihrm.domain.company.Company;
import jdk.internal.org.objectweb.asm.commons.Method;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin  //TODO 解决跨域问题
@RestController
@RequestMapping(value = "/method")
public class MethodController {

    @GetMapping("method")
    public void method(){
        List<Company> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Company co = new Company();
            co.setId(IdWorker.uuid);
            co.setName("公司"+i);
            co.setManagerId(IdWorker.uuid);
            co.setVersion("202201");
            co.setRenewalDate(new Date());
            co.setExpirationDate(new Date());
            list.add(co);
        }
//        MultiInsertUtil.insertDataSingle(CompanyMapper.class, Company.class, "insertBatch", list, "出清数据");
    }
}
