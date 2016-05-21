package me.codaline.service;

import javafx.geometry.Pos;
import me.codaline.dao.ActionDao;
import me.codaline.dao.PostDao;
import me.codaline.model.Post;
import me.codaline.model.actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao dao;
    @Autowired
    ActionDao actionDao;

    public Post createPost(String title, String context,String date,String image) {
        Post post = new Post();
        post.setTitle(title);
        post.setContext(context);
        post.setDate(date);
        post.setImage(image);
        dao.save(post);
        return post;
    }

    public Post deletePost(int id){
        Post post = new Post();
        post.setId(id);
        dao.delete(post);
        return post;
    }

    public  void update (int id,String title,String date, String context,  String image){
        Post post = new Post();
        post.setTitle(title);
        post.setContext(context);
        post.setDate(date);
        post.setId(id);
        post.setImage(image);
        dao.update(post);

    }
    public List<Post> getPosts(){return  dao.getPosts();}
    public Post getPost(int id){return dao.getPost(id);}

    public void setAction(String user,String action, String date){
        actions actionns=new actions();
        actionns.setUser(user);
        actionns.setAction(action);
        actionns.setDate(date);
        actionDao.setAction(actionns);
    }
    public List<actions> getActions(){
        return actionDao.getActions();
    }
}
