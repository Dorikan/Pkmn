package su.drkn.pkmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.drkn.pkmn.models.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
