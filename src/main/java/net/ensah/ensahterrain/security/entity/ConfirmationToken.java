package net.ensah.ensahterrain.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter   @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private User user;
}
