package com.dong1990.dao.impl;


import com.dong1990.dao.LinkManDao;
import com.dong1990.domain.LinkMan;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

    @Resource(name="sessionFactory")
    public  void setSesion(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
