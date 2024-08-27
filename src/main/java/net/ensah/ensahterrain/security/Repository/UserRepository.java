package net.ensah.ensahterrain.security.Repository;

import net.ensah.ensahterrain.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailIgnoreCase(String email);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
