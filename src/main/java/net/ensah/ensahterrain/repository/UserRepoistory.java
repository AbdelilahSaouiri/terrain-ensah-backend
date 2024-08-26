package net.ensah.ensahterrain.repository;

import net.ensah.ensahterrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoistory extends JpaRepository<User,Long> {
    User findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
