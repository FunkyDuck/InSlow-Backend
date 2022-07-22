package tk.inslow.inslowapi.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tk.inslow.inslowapi.config.JwtProvider;
import tk.inslow.inslowapi.mappers.PostsMapper;
import tk.inslow.inslowapi.models.dto.PostsDTO;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.services.PostsServices;
import tk.inslow.inslowapi.services.UsersServices;

@RestController
@CrossOrigin
public class PostsController
{
    @Autowired
    private PostsServices postsServices;
    @Autowired
    private PostsMapper postsMapper;
    @Autowired
    private UsersServices usersServices;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/posts")
    public String newPost(Authentication auth, @RequestBody ObjectNode object){
        String u = object.get("userName").toString();
        u = u.substring(1,u.length()-1);
        String c = object.get("content").toString();
        UsersDTO user = new UsersDTO();
        user = usersServices.getUserByName(u);
        PostsDTO post = new PostsDTO();
        post.setUsers(user);
        post.setContent(c);

//        System.out.println(object);

        if(user.getName().equals(auth.getPrincipal())){
            System.out.println("bouh");
            postsServices.save(post);
        }else{
            System.out.println("YOU LOOSE !");
        }


        return "post";
    }
}
