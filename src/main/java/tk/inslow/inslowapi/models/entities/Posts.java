package tk.inslow.inslowapi.models.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Posts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String media;
    private String content;
//    @NotNull
    @CreationTimestamp
    private LocalDateTime posted;
    @ManyToOne @JoinColumn(name="idUser")
    private Users users;
    // ADD REACTIONS
    // ADD COMMENTS
}
