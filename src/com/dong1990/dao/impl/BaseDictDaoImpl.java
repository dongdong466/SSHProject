package com.dong1990.dao.impl;

import com.dong1990.dao.BaseDao;
import com.dong1990.dao.BaseDictDao;
import com.dong1990.domain.BaseDict;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

    @Resource(name="sessionFactory")
    public  void setSesion(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {

        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
        dc.add(Restrictions.eq("dict_type_code",dict_type_code));
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }
}
