package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostService {
    @Autowired
    LoginService loginService;

    public List getAllPosts() {
        return DatabaseManager.getAll(Post.class);
    }

    public String add(String token, String description, String category, String cause, double x, double y, String region, String date) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //get user
        User user = loginService.getUser(token);
        if (user == null) { //flag
            return "false";
        }

        //format date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date formattedDate = new Date();
        try {
             formattedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //get current highest post id from db
        ArrayList<Post> posts = (ArrayList<Post>)DatabaseManager.getAll(Post.class);
        int id = 0;
        if (posts.size() > 0) {
            id = posts.stream().max(Comparator.comparing(z -> z.getId())).get().getId();
            id++; //increase for new post
        }

        //create new post object
        Post post = new Post(id, description, category, cause, x, y, region, formattedDate);

        //add post to user
        user.addPost(post);

        try {
            //add post to db
            DatabaseManager.add(post);

            //add relation to user
            DatabaseManager.update(user);
        } catch (Exception e) {
            e.printStackTrace();

            //fail
            return "false";
        }

        //success
        return "true";
    }

    public String remove(String token, int postId) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //get all posts
        ArrayList<Post> posts = (ArrayList<Post>)DatabaseManager.getAll(Post.class);
        if (posts == null) { //flag
            return "false";
        }

        //find post
        Post foundPost = posts.stream().filter(c -> c.getId() == postId).findFirst().get();
        if (foundPost == null) { //flag
            return "false";
        }

        //get user
        User user = TokenManager.getUser(token).getUser();
        if (!user.getPosts().contains(foundPost)) { //flag
            return "false";
        }

        try {
            user.getPosts().remove(foundPost);
            DatabaseManager.update(user);

            DatabaseManager.remove(foundPost);
        } catch (Exception e) {
            e.printStackTrace();

            //fail
            return "false";
        }

        //success
        return "true";
    }
}
