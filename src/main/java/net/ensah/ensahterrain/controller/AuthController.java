package net.ensah.ensahterrain.controller;

import jakarta.validation.Valid;
import net.ensah.ensahterrain.dto.UserRequestDto;
import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

    private final UserService userService;
    private static final Logger logger=LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        logger.info("register {}", registerDto.email());

        Map<String, String> registerInfos= userService.saveUser(registerDto);
        return ResponseEntity.ok(registerInfos);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody @Valid UserRequestDto loginDto) {
        Map<String, String> login = userService.login(loginDto);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/confirm-token")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String token) {
        Map<String, String> infos = userService.confirmEmail(token);
        return ResponseEntity.ok(infos);
    }

}
