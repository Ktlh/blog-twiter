package me.codaline.dao;

import me.codaline.model.Activity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ActivityDao {

    @Autowired
    SessionFactory sessionFactory;


    public void save(Activity activity) {
        sessionFactory.getCurrentSession().save(activity);
    }

    public void clean() {
        Query query = sessionFactory.getCurrentSession().createQuery("update Activity set logcount = :nol");
        query.setParameter("nol", 0);
        int result = query.executeUpdate();
    }

    public List<Activity> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Activity.class).list();
    }
    public void upCount(String login){

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Activity.class);
        criteria.add(Restrictions.eq("login", login));
       Activity activity = (Activity) criteria.uniqueResult();
        activity.setLogcount(activity.getLogcount()+1);
        sessionFactory.getCurrentSession().update(activity);
    }
}
