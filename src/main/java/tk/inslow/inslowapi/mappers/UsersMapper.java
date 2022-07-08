package tk.inslow.inslowapi.mappers;

import org.springframework.stereotype.Service;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.models.entities.Users;

@Service
public class UsersMapper {
    public UsersDTO toDto(Users users){
        if(users == null){
            return null;
        }

        UsersDTO dto = new UsersDTO();
        dto.setUserId(users.getUserId());
        dto.setMail(users.getMail());
        dto.setName(users.getName());
        dto.setPassword(users.getPassword());
        dto.setBirthDate(users.getBirthDate());
        dto.setRegistered(users.getRegistered());
        dto.setCity(users.getCity());
        dto.setCountry(users.getCountry());
        dto.setRole(users.getRole());

        return dto;
    }

    public Users toEntity(UsersDTO usersDTO) {
        if(usersDTO == null){
            return null;
        }

        Users entity = new Users();
        entity.setUserId(usersDTO.getUserId());
        entity.setMail(usersDTO.getMail());
        entity.setName(usersDTO.getName());
        entity.setPassword(usersDTO.getPassword());
        entity.setBirthDate(usersDTO.getBirthDate());
        entity.setRegistered(usersDTO.getRegistered());
        entity.setCity(usersDTO.getCity());
        entity.setCountry(usersDTO.getCountry());
        entity.setRole(usersDTO.getRole());

        return entity;
    }
}
