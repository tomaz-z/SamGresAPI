package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestHeader String token, @RequestHeader String description, @RequestHeader String category, @RequestHeader String cause, @RequestHeader double x, @RequestHeader double y, @RequestHeader String region, @RequestHeader String date) {
        return postService.add(token, description, category, cause, x, y, region, date);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestHeader String token, @RequestHeader int postId) {
        return postService.remove(token, postId);
    }
}
