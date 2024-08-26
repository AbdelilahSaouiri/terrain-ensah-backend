package net.ensah.ensahterrain.controller;

import jakarta.validation.Valid;
import net.ensah.ensahterrain.dto.LoginDto;
import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        return  userService.saveUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto) {
        return null;
    }

    @GetMapping("/confirm-token")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String token) {
        return userService.confirmEmail(token);
    }

}
