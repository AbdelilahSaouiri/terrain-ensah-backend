package net.ensah.ensahterrain.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

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
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Boolean isEnable=false;
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role>  roles;

}
