package tk.inslow.inslowapi.models.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Users implements UserDetails {
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
    @OneToMany(mappedBy = "users")
    private List<Posts> posts = new ArrayList<>();
    @OneToMany(mappedBy = "usersFollow")
    private Set<Followers> usersFollow;
    @OneToMany(mappedBy = "usersFollowed")
    private Set<Followers> usersFollowed;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(this.getRole().toString());
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(auth);
        return roles;
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
