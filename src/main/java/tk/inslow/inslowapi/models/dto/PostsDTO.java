package tk.inslow.inslowapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PostsDTO {
    private long postId;
    private String media;
    private String content;
    private LocalDateTime posted;
    @JsonIgnore
    private UsersDTO users;
    private long userId;
    private String userName;

    public long getUserId() {
        return this.users.getUserId();
    }

    public String getUserName() {
        return this.users.getName();
    }
}
