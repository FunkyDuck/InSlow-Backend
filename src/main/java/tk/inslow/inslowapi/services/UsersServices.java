package tk.inslow.inslowapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.inslow.inslowapi.mappers.UsersMapper;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.models.entities.Users;
import tk.inslow.inslowapi.repositories.UsersRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersServices {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder encoder;

    public UsersDTO save(UsersDTO usersDTO){
        usersDTO.setName(usersDTO.getName().replace(" ",""));
        usersDTO.setPassword(this.encoder.encode(usersDTO.getPassword()));
        return usersMapper.toDto(usersRepository.save(usersMapper.toEntity(usersDTO)));
    }

    public Set<UsersDTO> getUsers(){
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toDto)
                .collect(Collectors.toSet());
    }

    public UsersDTO getUserById(long id, PasswordEncoder encoder){
        this.encoder = encoder;
        return usersMapper.toDto(
                usersRepository.findById(id).orElse(null)
        );
    }

    public String deleteUser(long id) {
        usersRepository.deleteById(id);
        return "User deleted";
    }
}
