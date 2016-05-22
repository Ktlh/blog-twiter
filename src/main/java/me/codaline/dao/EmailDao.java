package me.codaline.dao;

import me.codaline.model.EmailAccess;
import me.codaline.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ighor on 22.05.2016.
 */
@Repository
@Transactional
public class EmailDao {
    @Autowired
    SessionFactory sessionFactory;

    public void saveEmail(EmailAccess emailAccess) {
        sessionFactory.getCurrentSession().save(emailAccess);
    }

    public void confirmationEmail(String code) {
        Criteria criteriaEmail = sessionFactory.getCurrentSession().createCriteria(EmailAccess.class);
        criteriaEmail.add(Restrictions.eq("code", code));
        EmailAccess emailAccess =(EmailAccess) criteriaEmail.uniqueResult();
        Criteria criteriaUser = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteriaUser.add(Restrictions.eq("username",emailAccess.getUsername()));
        User user = (User)  criteriaUser.uniqueResult();

        user.setEnabled(true);
        sessionFactory.getCurrentSession().update(user);
        sessionFactory.getCurrentSession().delete(emailAccess);
    }
    public EmailAccess  getUserByCode(String code)
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmailAccess.class);
        criteria.add(Restrictions.eq("code", code));
//
        return (EmailAccess) criteria.uniqueResult();
    }
}
