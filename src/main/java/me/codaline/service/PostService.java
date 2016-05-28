package me.codaline.service;


import me.codaline.dao.ActionDao;
import me.codaline.dao.PostDao;
import me.codaline.model.Post;
import me.codaline.model.actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao dao;
    @Autowired
    ActionDao actionDao;

    public Post createPost(String title, String context,String date,String image, String userName) {
        Post post = new Post();
        post.setTitle(title);
        post.setContext(context);
        post.setDate(date);
        post.setImage(image);
        post.setUsername(userName);
        dao.save(post);
        return post;
    }

    public Post deletePost(int id){
        Post post = new Post();
        post.setId(id);
        dao.delete(post);
        return post;
    }

    public  void update (int id,String title,String date, String context,  String image, String userName){
        Post post = new Post();
        post.setTitle(title);
        post.setContext(context);
        post.setDate(date);
        post.setId(id);
        post.setImage(image);
        post.setUsername(userName);
        dao.update(post);

    }
    public List<Post> getPosts(String userName){return  dao.getPosts(userName);}
    public Post getPost(int id){return dao.getPost(id);}

    public void setAction(String user,String action,String href, String date){
        actions actionns=new actions();
        actionns.setUser(user);
        actionns.setAction(action);
        actionns.setDate(date);
        actionns.setHref(href);
        actionDao.setAction(actionns);
    }
    public List<actions> getActions(){
        return actionDao.getActions();
    }
}
