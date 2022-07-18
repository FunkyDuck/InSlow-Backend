package tk.inslow.inslowapi.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.*;
import tk.inslow.inslowapi.mappers.UsersMapper;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.services.UsersServices;

import java.util.Set;

@RestController
@CrossOrigin
//@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServices usersServices;
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping("/user")
    public Set<UsersDTO> getUsers() {return usersServices.getUsers();}

    @PostMapping("/user")
    public String newUser(@RequestBody UsersDTO postUser) {
        System.out.println(postUser);
        return usersServices.save(postUser);}

    @PostMapping("/user/connect")
    public String connectUser(@RequestBody UsersDTO connectUser) {
        return "{\"JWT\": \"" + usersServices.connectUser(connectUser) + "\"}";
    }

    @ResponseBody
    @GetMapping("/user/check/{dataType}/{data}")
    public String checkUserNameOrMail(@PathVariable("dataType") String dataType, @PathVariable("data") String data) {
        String str = "{\"exist\" : ";
        if(dataType.equals("mail"))
            str +=usersServices.getUserByMail(data);
        else if (dataType.equals("name"))
            str += usersServices.getUserByName(data);
        else
            str += false;
        str += "}";
        return str;
    }
}
