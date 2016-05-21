package me.codaline.dao;

import me.codaline.model.Post;
import me.codaline.model.actions;
import org.dom4j.rule.Action;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ActionDao {
    @Autowired
    SessionFactory sessionFactory;

    public void setAction(actions action){
        sessionFactory.getCurrentSession().save(action);
    }
    public List<actions> getActions(){
        return sessionFactory.getCurrentSession().createCriteria(actions.class).addOrder(Property.forName("id").desc()).list();
    }
}
