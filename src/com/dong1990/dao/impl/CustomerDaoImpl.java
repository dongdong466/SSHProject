package com.dong1990.dao.impl;

import com.dong1990.dao.CustomerDao;
import com.dong1990.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

    @Resource(name="sessionFactory")
    public  void setSesion(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Object> getIndustryCount() {
        List<Object> list = getHibernateTemplate().execute(new HibernateCallback<List>(){

            String sql = "SELECT bd.dict_item_name, count(*) FROM cst_customer cc, base_dict bd" +
                    " WHERE cc.cust_industry = bd.dict_id" +
                    " GROUP BY cc.cust_industry";
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
            }
        });
        return list;
    }

    @Override
    public List<Object> getSourceCount() {
        List<Object> list = getHibernateTemplate().execute(new HibernateCallback<List>(){

            String sql = "SELECT bd.dict_item_name, count(*) FROM cst_customer cc, base_dict bd" +
                    " WHERE cc.cust_source = bd.dict_id" +
                    " GROUP BY cc.cust_source";
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
            }
        });
        return list;
    }
}
