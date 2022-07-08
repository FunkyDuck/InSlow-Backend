package tk.inslow.inslowapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tk.inslow.inslowapi.models.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByName(String name);
    Users findByMail(String mail);

    @Query(value = "SELECT u FROM Users u WHERE u.name LIKE CONCAT('%',:name,'%')")
    Users findByPartialName(@Param("name") String name);
}
