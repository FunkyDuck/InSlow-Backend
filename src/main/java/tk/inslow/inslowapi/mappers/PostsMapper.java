package tk.inslow.inslowapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.inslow.inslowapi.models.dto.PostsDTO;
import tk.inslow.inslowapi.models.entities.Posts;

@Service
public class PostsMapper {
    @Autowired
    private UsersMapper usersMapper;

    public PostsDTO toDto(Posts posts){
        if(posts == null){
            return null;
        }

        PostsDTO dto = new PostsDTO();
        dto.setPostId(posts.getPostId());
        dto.setPosted(posts.getPosted());
        dto.setContent(posts.getContent());
        dto.setMedia(posts.getMedia());

        if(posts.getUsers() != null){
            dto.setUsers(usersMapper.toDto(posts.getUsers()));
        }

        // ADD COMMENT
        // ADD REACTIONS
        return dto;
    }

    public Posts toEntity(PostsDTO postsDTO){
        if(postsDTO == null){
            return null;
        }

        Posts entity = new Posts();
        entity.setPostId(postsDTO.getPostId());
        entity.setPosted((postsDTO.getPosted()));
        entity.setContent(postsDTO.getContent());
        entity.setMedia(postsDTO.getMedia());

        if(postsDTO.getUsers() != null){
            entity.setUsers((usersMapper.toEntity(postsDTO.getUsers())));
        }

        return entity;
    }
}
