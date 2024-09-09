package net.ensah.ensahterrain.security.Repository;

import net.ensah.ensahterrain.security.entity.ConfirmationToken;
import net.ensah.ensahterrain.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class ConfirmationTokenRepositoryTest {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private  UserRepository userRepository;

    private final String token=UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        User   user=User.builder()
                .userId(UUID.randomUUID().toString())
                .email("abdelilah@gmail.com")
                .password("1234")
                .isEnable(true)
                .build();
        User save = userRepository.save(user);
        ConfirmationToken confirmationToken= ConfirmationToken.builder()
                .user(save)
                .confirmationToken(token)
                .build();

        confirmationTokenRepository.save(confirmationToken);

    }

    @Test
    void shouldFindByConfirmationToken() {
        ConfirmationToken result = confirmationTokenRepository.findByConfirmationToken(token);
        assertThat(result).isNotNull();
        assertThat(result.getConfirmationToken()).isEqualTo(token);
    }

    @Test
    void shouldNotFindByConfirmationToken() {
        String invalidToken=UUID.randomUUID().toString();
        ConfirmationToken result = confirmationTokenRepository.findByConfirmationToken(invalidToken);
        assertThat(result).isNull();
    }
}