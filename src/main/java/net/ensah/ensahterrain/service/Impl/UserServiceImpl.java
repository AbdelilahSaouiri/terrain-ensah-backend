package net.ensah.ensahterrain.service.Impl;

import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.entity.ConfirmationToken;
import net.ensah.ensahterrain.entity.User;
import net.ensah.ensahterrain.mapper.UserRegisterMapper;
import net.ensah.ensahterrain.repository.ConfirmationTokenRepoistory;
import net.ensah.ensahterrain.repository.UserRepoistory;
import net.ensah.ensahterrain.service.EmailService;
import net.ensah.ensahterrain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepoistory userRepoistory;
    private final ConfirmationTokenRepoistory confirmationTokenRepoistory;
    private final EmailService emailService;
    private UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepoistory userRepoistory,
                           ConfirmationTokenRepoistory confirmationTokenRepoistory,
                           EmailService emailService, UserRegisterMapper userRegisterMapper, PasswordEncoder passwordEncoder) {
        this.userRepoistory = userRepoistory;
        this.confirmationTokenRepoistory = confirmationTokenRepoistory;
        this.emailService = emailService;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<?> saveUser(RegisterDto user) {
        if(userRepoistory.existsByEmail(user.email())){
               return ResponseEntity.badRequest().body("Error: Email address already in use");
           }
        User user2=User.builder()
                .userId(UUID.randomUUID().toString())
                .userName(user.username())
                .year(user.year())
                .email(user.email())
                .password(new BCryptPasswordEncoder().encode(user.password()))
                .build();
          User save = userRepoistory.save(user2);
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .user(save)
                .confirmationToken(UUID.randomUUID().toString())
                .tokenId(1L)
                .build();
         confirmationTokenRepoistory.save(confirmationToken);
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(user.email());
        simpleMailMessage.setSubject("Complete Registration!");
        simpleMailMessage.setText("To confirm your account, Please click here : "+
                "http://localhost:4200/verifyEmail?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(simpleMailMessage);
        return ResponseEntity.ok("Verify email by the link sent to email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken byConfirmationToken = confirmationTokenRepoistory.findByConfirmationToken(confirmationToken);
        if(byConfirmationToken == null){
            return ResponseEntity.badRequest().body("Error: Couldn't Verify Email");
        }
        User byEmailIgnoreCase = userRepoistory.findByEmailIgnoreCase(byConfirmationToken.getUser().getEmail());
        byEmailIgnoreCase.setIsEnable(true);
        userRepoistory.save(byEmailIgnoreCase);

        return ResponseEntity.ok("Email verified successfully ");
    }
}
