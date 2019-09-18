package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.models.DARS.events.FeatureCollection;
import si.samgres.api.models.DARS.events.features.Properties;
import si.samgres.api.models.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrafficService {
    @Autowired
    DARSService darsService;

    @Autowired
    LoginService loginService;

    @Autowired
    PostService postService;

    public String getEvents(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //combine dars events with our posts
        List<Post> allPosts = new ArrayList<Post>();

        //get all posts from our db
        List posts = postService.getAllPosts();
        if (posts != null) {
            allPosts.addAll(posts);
        }


        //set index
        int index = 0;
        if (allPosts.size() > 0) {
            index = allPosts.stream().max(Comparator.comparing(c -> c.getId())).get().getId(); //get max id
            index++; //set id for next
        }


        //get all events from dars db
        FeatureCollection events = darsService.getEvents();
        if (events != null) {
            for (int i = 0; i < events.getFeatures().length; i++) {
                //get the current feature
                Properties currentlyFeaturedEventProperties = events.getFeatures()[i].getProperties();

                //prepare needed properties
                String description = currentlyFeaturedEventProperties.getOpis() + " " + currentlyFeaturedEventProperties.getDodatnoPojasnilo();
                String category = currentlyFeaturedEventProperties.getKategorija();
                String cause = currentlyFeaturedEventProperties.getVzrok();
                double x = currentlyFeaturedEventProperties.getX();
                double y = currentlyFeaturedEventProperties.getY();

                //set format
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = null;
                try {
                    date = format.parse(currentlyFeaturedEventProperties.getUpdated().replace("T", " "));
                } catch (ParseException e) {
                    e.printStackTrace();
                    date = new Date();
                }

                //load data of the feature into a new post
                Post newPost = new Post(index, description, category, cause, x, y, "TODO get region from google", date);
                allPosts.add(newPost);
            }
        }

        //return all
        return GsonHelper.toJson(allPosts);
    }

    public String getBorderDelays(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        return GsonHelper.toJson(darsService.getBorderDelays());
    }

    public String getStorms(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        return GsonHelper.toJson(darsService.getStorms());
    }
}
