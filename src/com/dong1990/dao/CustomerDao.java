package com.dong1990.dao;

import com.dong1990.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer>{
    List<Object> getIndustryCount();

    List<Object> getSourceCount();
}
