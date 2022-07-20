package tk.inslow.inslowapi.models.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Followers {
    @Id
    private int followersId;
    @ManyToOne
    @JoinColumn(name="usersFollow")
    private Users usersFollow;
    @ManyToOne
    @JoinColumn(name="userFollowed")
    private Users usersFollowed;
}
