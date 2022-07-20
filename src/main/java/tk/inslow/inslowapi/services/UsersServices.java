package tk.inslow.inslowapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.inslow.inslowapi.config.JwtProperties;
import tk.inslow.inslowapi.config.JwtProvider;
import tk.inslow.inslowapi.mappers.UsersMapper;
import tk.inslow.inslowapi.models.dto.UsersDTO;
import tk.inslow.inslowapi.models.entities.Users;
import tk.inslow.inslowapi.repositories.UsersRepository;

import java.util.Date;
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

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final JwtProperties properties;

    @Autowired
    private final JwtProvider provider;

    public UsersServices(AuthenticationManager authManager, JwtProperties properties, JwtProvider provider){
        this.authManager = authManager;
        this.properties = properties;
        this.provider = provider;
    }

    public String save(UsersDTO usersDTO){
        System.out.println(usersDTO.toString());
        usersDTO.setName(usersDTO.getName().replace(" ",""));
        usersDTO.setPassword(this.encoder.encode(usersDTO.getPassword()));
        usersMapper.toDto(usersRepository.save(usersMapper.toEntity(usersDTO)));

        return provider.createToken(usersDTO.getName(), usersDTO.getRole());
    }


    public Set<UsersDTO> getUsers(){
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toDto)
                .collect(Collectors.toSet());
    }

    public UsersDTO getUserById(long id){
        return usersMapper.toDto(
                usersRepository.findById(id).orElse(null)
        );
    }

    public UsersDTO getUserByName(String name){
        UsersDTO user = usersMapper.toDto(
                usersRepository.findByName(name)
        );

        user.setPassword("");

        return user;
    }

    public boolean checkMail(String mail){
        System.out.println(usersRepository.findByMail(mail));
        if(usersRepository.findByMail(mail) != null) {
            return true;
        }
        return false;
    }

    public boolean checkName(String name){
        if(usersRepository.findByName(name) != null)
            return true;
        return false;
    }

    public String connectUser(UsersDTO usersDTO){
        Users user = usersRepository.findByMail(usersDTO.getMail());
        if(user!=null){
            try {
                if(this.encoder.matches(usersDTO.getPassword(),user.getPassword())) {
                    System.out.println(user);

                    return provider.createToken(user.getName(), user.getRole());
                }
            }catch (Exception e){
                return e.toString();
                //+"Password is empty... Are you dumb???";
            }

            return "Bad password";
        }else{
            return "User not found!";
        }
    }

    public String deleteUser(long id) {
        usersRepository.deleteById(id);
        return "User deleted";
    }
}
