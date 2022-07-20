package tk.inslow.inslowapi.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.inslow.inslowapi.config.JwtProvider;
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
    public String newPost(@RequestBody ObjectNode object, @RequestHeader(value="Authorization") String header){
        System.out.println(object);
        System.out.println(header);
//        JwtProvider jwtProvider;
//        jwtProvider.resolveToken();

        return "";
    }
}
