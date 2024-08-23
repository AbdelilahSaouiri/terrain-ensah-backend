package net.ensah.ensahterrain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "matches")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchId;
    private Integer matchTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private  Date  matchDate;
    private String matchPlayer;
    private Integer dayNumber;
}
