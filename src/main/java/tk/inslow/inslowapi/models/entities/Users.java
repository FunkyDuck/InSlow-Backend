package tk.inslow.inslowapi.models.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true) @NotNull
    private String mail;

    @Column @NotNull
    private String password;

    @Column(length = 64, unique = true) @NotNull
    private String name;

    @NotNull
    private LocalDate birthDate;

    private String country;

    private String city;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(columnDefinition = "varchar(16) default 'USER'")
    private Role role;

    @CreationTimestamp
    private LocalDateTime registered;
}
