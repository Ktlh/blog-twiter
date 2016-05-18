package me.codaline.dao;


import me.codaline.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
//
		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession().createQuery(" from User where username=?").setParameter(0, username)
				.list();



		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}


//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
//        // criteria.add(Restrictions.eq( email ,"email"));
//        criteria.add(Restrictions.eq("username", username));
//		System.out.println(criteria.uniqueResult().toString());


//ss
////        Query query = sessionFactory.getCurrentSession().createQuery("select from User as u where u.email =:email");
////        query.setParameter("email", email);
      //  return (User) criteria.uniqueResult();
	}

}