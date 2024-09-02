package net.ensah.ensahterrain.security.service.impl;

import net.ensah.ensahterrain.dto.UserRequestDto;
import net.ensah.ensahterrain.dto.RegisterDto;
import net.ensah.ensahterrain.security.Repository.UserRepository;
import net.ensah.ensahterrain.security.entity.ConfirmationToken;
import net.ensah.ensahterrain.security.entity.Role;
import net.ensah.ensahterrain.security.entity.User;
import net.ensah.ensahterrain.mapper.UserRegisterMapper;
import net.ensah.ensahterrain.security.Repository.ConfirmationTokenRepoistory;
import net.ensah.ensahterrain.security.service.EmailService;
import net.ensah.ensahterrain.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ConfirmationTokenRepoistory confirmationTokenRepoistory;
    private final EmailService emailService;
    private final JwtEncoder jwtEncoder;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           ConfirmationTokenRepoistory confirmationTokenRepoistory,
                           EmailService emailService,
                           UserRegisterMapper userRegisterMapper,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.confirmationTokenRepoistory = confirmationTokenRepoistory;
        this.emailService = emailService;
        this.userRegisterMapper = userRegisterMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }


    @Override
    public Map<String, String> saveUser(RegisterDto user) {
        Map<String,String> map= new HashMap<>();
        if(userRepository.existsByEmail(user.email())){
            map.put("message","Error: Email address already in use");
            return map;
           }
        User user1 = userRegisterMapper.RegisterDtoToUser(user);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().RoleName("USER").build());
        User user2=User.builder()
                .userId(UUID.randomUUID().toString())
                .userName(user.username())
                .year(user.year())
                .email(user.email())
                .roles(roles)
                .password(user.password())
                .build();
        User save = userRepository.save(user2);
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .user(save)
                .createDate(new Date())
                .confirmationToken(UUID.randomUUID().toString())
                .build();
         confirmationTokenRepoistory.save(confirmationToken);
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(user.email());
        simpleMailMessage.setSubject("Complete Registration!");
        simpleMailMessage.setText("To confirm your account, Please click here : "+
                "http://localhost:4200/verifyEmail?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(simpleMailMessage);
        map.put("message","Verify email by the link sent to email address");
        map.put("confirmationToken",confirmationToken.getConfirmationToken());
        return map;
    }

    @Override
    public Map<String, String> confirmEmail(String confirmationToken) {
        Map<String, String> response = new HashMap<>();
        try {
            ConfirmationToken token = confirmationTokenRepoistory.findByConfirmationToken(confirmationToken);

            if (token == null) {
                response.put("message", "Error: Invalid confirmation token");
                return response;
            }

            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            String rawPassword=user.getPassword();
            user.setIsEnable(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword);
            Authentication authentication = authenticationManager.authenticate(authToken);
            // Generate JWT token
            String jwtToken = generateJwtToken(authentication,user.getUserName());
            response.put("access-token", jwtToken);
            response.put("message", "Email verified successfully");
        } catch (Exception e) {
            response.put("message", "Error: Authentication failed");
        }
        return response;
    }


    @Override
    public Map<String, String> login(UserRequestDto loginDto) {
        Map<String,String> map = new HashMap<>();
        User byEmailIgnoreCase = userRepository.findByEmailIgnoreCase(loginDto.email());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
         //generate Jwt token
        String jwtToken = generateJwtToken(authenticate,byEmailIgnoreCase.getUserName());
        map.put("access-token",jwtToken);
        logger.debug("token {}",jwtToken);
        return map;
    }

    private String generateJwtToken(Authentication authenticate,String userName) {
        String scope = authenticate.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(authenticate.getName())
                .claim("scope", scope)
                .claim("username", userName)
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet);
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }


}
