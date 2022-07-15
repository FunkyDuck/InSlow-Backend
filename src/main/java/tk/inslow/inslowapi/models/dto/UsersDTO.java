package tk.inslow.inslowapi.models.dto;

import lombok.*;
import tk.inslow.inslowapi.models.entities.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsersDTO {
    private long userId;
    private String mail;
    private String password;
    private String name;
    private LocalDate birthDate;
    private String country;
    private String city;
    private Role role = Role.USER;
    private LocalDateTime registered;
}
