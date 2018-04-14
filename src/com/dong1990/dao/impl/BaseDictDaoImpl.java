package com.dong1990.dao.impl;

import com.dong1990.dao.BaseDao;
import com.dong1990.dao.BaseDictDao;
import com.dong1990.domain.BaseDict;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {


    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {

        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
        dc.add(Restrictions.eq("dict_type_code",dict_type_code));
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }
}
