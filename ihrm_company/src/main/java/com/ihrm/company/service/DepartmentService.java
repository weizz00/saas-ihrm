package com.ihrm.company.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService extends BaseService {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 保存部门
     */
    public void save(Department department){
        //设置主键的值
        //调用dao保存部门
        String id = idWorker.nextId()+"";
        department.setId(id);
        departmentDao.save(department);
    }
    /**
     * 更新部门
     */
    public void update(Department department){
        //根据id查询部门
        Department dept = departmentDao.findById(department.getId()).get();
        //设置部门属性
        dept.setCode(department.getCode());
        dept.setName(department.getName());
        dept.setIntroduce(department.getIntroduce());
        //更新部门
        departmentDao.save(dept);
    }
    /**
     * 根据id查询部门
     */
    public Department findById(String id){
        return departmentDao.findById(id).get();
    }
    /**
     * 查询全部部门列表
     */
    public List<Department> findAll(String companyId){
        /**
         *   Specification用于构造查询条件
         *      root：  包含了所有的对象数据
         *      query：  一般不用
         *      criteriaBuilder：  用于构造查询条件
         */
/*        Specification<Department> spec = new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
            }
        };*/
        return  departmentDao.findAll(getSpec(companyId));
    }
    /**
     * 根据id删除部门
     */
    public void deleteById(String id){
        departmentDao.deleteById(id);
    }
}
