package com.dong1990.service;

import com.dong1990.domain.Customer;
import com.dong1990.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    // 分页业务查询
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    // 保存客户方法
    void save(Customer customer);

    //根据id获得客户对象
    Customer getById(Long cust_id);

    List<Object> getIndustryCount();

    List<Object> getSourceCount();
}
