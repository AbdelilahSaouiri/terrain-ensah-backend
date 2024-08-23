package net.ensah.ensahterrain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")   @AllArgsConstructor  @NoArgsConstructor @Getter @Setter @Builder
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
