package net.ensah.ensahterrain.security.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String RoleName;
}
