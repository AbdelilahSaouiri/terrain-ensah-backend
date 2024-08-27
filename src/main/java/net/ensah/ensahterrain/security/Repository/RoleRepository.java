package net.ensah.ensahterrain.security.Repository;

import net.ensah.ensahterrain.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
