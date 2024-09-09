package net.ensah.ensahterrain.mapper;

import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.security.entity.User;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterMapperTest {


     UserRegisterMapper  underTest= new UserRegisterMapperImpl();

    @Test
    void  shouldReturnUserFromRegisterDto() {
         RegisterDto registerDto=new RegisterDto("abdelilah","CI2","abdelilah@gmail.com","1234","1234");
         User expected=User.builder().userName("abdelilah").year("CI2").email("abdelilah@gmail.com").password("1234").build();
        User result = underTest.RegisterDtoToUser(registerDto);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldNotReturnUserFromRegisterDto() {
        RegisterDto registerDto=new RegisterDto("abdelilah","CI2","abdelilah@gmail.com","1234","1234");
        User expected=User.builder().userName("abdelilah").year("CI1").email("abdelilah@gmail.com").password("1234").build();
        User result = underTest.RegisterDtoToUser(registerDto);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isNotEqualTo(expected);
    }
}