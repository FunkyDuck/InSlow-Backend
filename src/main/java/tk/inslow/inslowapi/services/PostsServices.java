package tk.inslow.inslowapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.inslow.inslowapi.mappers.PostsMapper;
import tk.inslow.inslowapi.mappers.UsersMapper;
import tk.inslow.inslowapi.models.dto.PostsDTO;
import tk.inslow.inslowapi.models.entities.Posts;
import tk.inslow.inslowapi.models.entities.Users;
import tk.inslow.inslowapi.repositories.PostsRepository;
import tk.inslow.inslowapi.repositories.UsersRepository;

@Service
public class PostsServices {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private PostsMapper postsMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;

    public PostsDTO save(PostsDTO postsDTO){
        Users users = usersRepository.findByName(postsDTO.getUserName());
        Posts posts = postsMapper.toEntity(postsDTO);
        posts.setUsers(users);
        return postsMapper.toDto(postsRepository.save(posts));
    }
}
