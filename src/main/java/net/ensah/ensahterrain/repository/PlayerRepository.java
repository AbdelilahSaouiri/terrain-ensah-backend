package net.ensah.ensahterrain.repository;

import net.ensah.ensahterrain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
