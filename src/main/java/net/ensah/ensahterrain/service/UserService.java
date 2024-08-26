package net.ensah.ensahterrain.service;

import net.ensah.ensahterrain.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(RegisterDto user);
    ResponseEntity<?> confirmEmail(String confirmationToken);
}
