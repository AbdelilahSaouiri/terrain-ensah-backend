package net.ensah.ensahterrain.security.Repository;

import net.ensah.ensahterrain.security.entity.Role;
import net.ensah.ensahterrain.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Role role =Role.builder().RoleName("USER").build();
        List<Role> roles = List.of(role);
        User user1= User.builder().
                userId(UUID.randomUUID().toString())
                .roles(roles).email("abdelilah@gmail.com").password("1234").build();
        userRepository.save(user1);
        User user2= User.builder()
        .userId(UUID.randomUUID().toString()).roles(roles).email("test@gmail.com").password("1234").build();
        userRepository.save(user2);
    }

    @Test
    void shouldFindByEmailIgnoreCase() {
        String email = "test@gmail.com";
        User user = userRepository.findByEmailIgnoreCase(email);
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldNotFindByEmailIgnoreCase() {
        String email = "xxx@gmail.com";
        User user = userRepository.findByEmailIgnoreCase(email);
        assertThat(user).isNull();
    }

    @Test
    void shouldExistsByEmail() {
        String email = "test@gmail.com";
        User user = userRepository.findByEmailIgnoreCase(email);
        assertThat(user).isNotNull();
    }
}