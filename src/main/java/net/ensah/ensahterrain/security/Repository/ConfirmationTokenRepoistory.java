package net.ensah.ensahterrain.security.Repository;

import net.ensah.ensahterrain.security.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepoistory extends JpaRepository<ConfirmationToken,Long> {
      ConfirmationToken findByConfirmationToken(String confirmationToken);
}
