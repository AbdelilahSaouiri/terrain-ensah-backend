package net.ensah.ensahterrain.mapper;


import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.security.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    RegisterDto userToRegisterDto(User user);

    User RegisterDtoToUser (RegisterDto registerDto);


}

