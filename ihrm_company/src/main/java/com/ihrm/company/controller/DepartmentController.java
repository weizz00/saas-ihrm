package com.ihrm.company.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.service.CompanyService;
import com.ihrm.company.service.DepartmentService;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.company.response.DeptListResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class DepartmentController  extends BaseController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;
    /**
     * 保存
     */
    @RequestMapping(value = "/department",method = RequestMethod.POST)
    public Result save(@RequestBody Department department){
        //设置保存的企业id
        department.setCompanyId(companyId);
        //调用service完成保存企业
        departmentService.save(department);
        //返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业部门列表
     * 制定企业邮id
     * @return
     */
    @RequestMapping(value = "/department" ,method = RequestMethod.GET)
    public Result findAll(){
        List<Department> list = departmentService.findAll(companyId);
        Company company = companyService.findById(companyId);
        DeptListResult deptListResult = new DeptListResult(company,list);
        return new Result(ResultCode.SUCCESS,deptListResult);
    }

    /**
     * 根据id查询企业
     */
    @RequestMapping(value = "/department/{id}" ,method = RequestMethod.GET)
    public Result findById(@PathVariable (value = "id")String id){
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 根据id修改企业信息
     */
    @RequestMapping(value = "/department/{id}" ,method = RequestMethod.PUT)
    public Result update(@PathVariable (value = "id")String id,@RequestBody Department department){
        //设置部门id修改
        department.setId(id);
        //调用service更新
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除企业信息
     */
    @RequestMapping(value = "/department/{id}" ,method = RequestMethod.DELETE)
    public Result delete(@PathVariable (value = "id")String id){
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
