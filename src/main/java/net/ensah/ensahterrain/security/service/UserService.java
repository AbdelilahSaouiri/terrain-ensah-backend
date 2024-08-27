package net.ensah.ensahterrain.security.service;

import net.ensah.ensahterrain.dto.UserRequestDto;
import net.ensah.ensahterrain.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<?> saveUser(RegisterDto user);
    Map<String,String> confirmEmail(String confirmationToken);

    Map<String,String> login(UserRequestDto loginDto);
}
