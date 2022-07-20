package tk.inslow.inslowapi.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.inslow.inslowapi.mappers.PostsMapper;
import tk.inslow.inslowapi.models.dto.PostsDTO;
import tk.inslow.inslowapi.services.PostsServices;

@RestController
@CrossOrigin
public class PostsController
{
    @Autowired
    private PostsServices postsServices;
    @Autowired
    private PostsMapper postsMapper;

    @PostMapping("/posts")
    public String newPost(@RequestBody PostsDTO postsDTO){
        System.out.println(postsDTO);
        return "";
    }
}
