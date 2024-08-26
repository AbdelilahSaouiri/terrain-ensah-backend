package net.ensah.ensahterrain.mapper;


import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    RegisterDto userToRegisterDto(User user);

    User RegisterDtoToUser (RegisterDto registerDto);


}

