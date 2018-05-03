package com.dong1990.dao.impl;

import com.dong1990.dao.UserDao;
import com.dong1990.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

// HibernateDaoSupport 为dao注入sessionFactory
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Resource(name="sessionFactory")
    public  void setSesion(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public User getByUserCode(final String usercode) {

        //HQL
        return getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "from User where user_code = ? ";
                Query query = session.createQuery(hql);
                query.setParameter(0, usercode);
                User user = (User) query.uniqueResult();
                return user;
            }
        });
        //Criteria
		/*DetachedCriteria dc = DetachedCriteria.forClass(User.class);

		dc.add(Restrictions.eq("user_code", usercode));

		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);

		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}*/
    }
}
