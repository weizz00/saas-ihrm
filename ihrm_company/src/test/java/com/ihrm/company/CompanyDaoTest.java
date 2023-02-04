package com.ihrm.company;

import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;
    @Test
    public void test(){

        companyDao.deleteById("1");  //根据id删除
        companyDao.save(new Company());  //保存或者更新
        companyDao.findAll(); //查询全部
        Company company = companyDao.findById("1").get(); //TODO 根据id查询
        System.out.println(company);
    }
}
