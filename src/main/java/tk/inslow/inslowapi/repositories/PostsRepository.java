package tk.inslow.inslowapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.inslow.inslowapi.models.entities.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
