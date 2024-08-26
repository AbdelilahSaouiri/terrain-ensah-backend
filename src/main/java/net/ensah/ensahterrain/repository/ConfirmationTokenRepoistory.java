package net.ensah.ensahterrain.repository;

import net.ensah.ensahterrain.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepoistory extends JpaRepository<ConfirmationToken,Long> {
      ConfirmationToken findByConfirmationToken(String confirmationToken);
}
