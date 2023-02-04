package com.ihrm.company.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ihrm.domain.company.Company;
import org.apache.poi.ss.usermodel.Workbook;
import org.aspectj.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin  //TODO 解决跨域问题
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {


    @RequestMapping(value = "/excel",method = RequestMethod.GET)
    public String excel () throws Exception {
        //获取模板
        String a = "C:/Users/Administrator/Desktop/11113/小明.xlsx";
        TemplateExportParams params = new TemplateExportParams(a);
        //复制模板
        File file = new File(a);
        File file1 = new File("C:/Users/Administrator/Desktop/11113/小明1.xlsx");
        if (!file1.getParentFile().exists()){
            file1.getParentFile().mkdir();
        }
        FileUtil.copyFile(file,file1);
        //模板数据
        Map<String, Object> map = new HashMap<String, Object>();
        String data = "1";
        String aa = "22";
        map.put("data", data);
        map.put("aa",aa);
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Company company = new Company();
            company.setId(String.valueOf(i));
            company.setName("小明"+i);
            companies.add(company);
        }
        map.put("data","11111");
        map.put("aa","2222");
        map.put("list",companies);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        FileOutputStream fos = new FileOutputStream(file1);
        workbook.write(fos);
        fos.close();
        workbook.close();
        return "小明1.xlsx";
    }
}
