package net.ensah.ensahterrain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor  @NoArgsConstructor @Builder @Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userName;
    private String year;
    private String email;
    private String password;
    private Boolean isEnable=false;
}
