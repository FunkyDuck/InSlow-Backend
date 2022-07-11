package tk.inslow.inslowapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.inslow.inslowapi.mappers.UsersMapper;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.services.UsersServices;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServices usersServices;
    @Autowired
    private UsersMapper usersMapper;

    @GetMapping
    public Set<UsersDTO> getUsers() {return usersServices.getUsers();}

    @PostMapping
    public UsersDTO newUser(@RequestBody UsersDTO postUser) {
        return usersServices.save(postUser);}
}
