package com.dong1990.service.impl;

import com.dong1990.dao.CustomerDao;
import com.dong1990.domain.Customer;
import com.dong1990.service.CustomerService;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation= Isolation.REPEATABLE_READ,propagation= Propagation.REQUIRED,readOnly=true)
public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {

        // 1调用dao查询总记录
        Integer totalCount = customerDao.getTotalCount(dc);
        // 2创建PageBean对象
        PageBean pageBean = new PageBean(currentPage,totalCount,pageSize);
        // 3调用dao查询分页列表数据
        List<Customer> list = customerDao.getPageList(dc,pageBean.getStart(),pageBean.getPageSize());
        // 4列表数据放入PageBean中，并返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    @Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
    public void save(Customer customer) {
        //1 维护Customer与数据字典对象的关系,由于struts2参数封装,会将参数封装到数据字典的id属性.
        //那么我们无需手动维护关系
        //2 调用Dao保存客户
        customerDao.saveOrUpdate(customer);// 这里使用saveOrUpdate，hibernate会自动根据客户是否含有id值，来自动执行保存或者更新操作
    }

    @Override
    public Customer getById(Long cust_id) {
        return customerDao.getById(cust_id);
    }

    @Override
    public List<Object> getIndustryCount() {
        List<Object> list = customerDao.getIndustryCount();
        return list;
    }

    @Override
    public List<Object> getSourceCount() {
        List<Object> list = customerDao.getSourceCount();
        return list;
    }
}
