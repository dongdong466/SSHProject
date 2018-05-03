package com.dong1990.dao.impl;

import com.dong1990.dao.SaleVisitDao;
import com.dong1990.domain.SaleVisit;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao{

    @Resource(name="sessionFactory")
    public  void setSesion(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
