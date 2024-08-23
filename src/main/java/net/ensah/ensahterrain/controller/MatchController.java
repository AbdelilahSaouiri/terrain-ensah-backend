package net.ensah.ensahterrain.controller;

import net.ensah.ensahterrain.dto.MatchRequestDto;
import net.ensah.ensahterrain.dto.MatchResponseDto;
import net.ensah.ensahterrain.exceptions.MatchException;
import net.ensah.ensahterrain.security.MatchServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@CrossOrigin(origins = "*")
public class MatchController {

    private final MatchServiceImpl matchService;

    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<?>  getAllMatches(@RequestParam(name = "jour",required = true) Integer jour){
       List<MatchResponseDto> allMatches = matchService.getAllMatches(jour);
       return ResponseEntity.ok().body(allMatches);
   }

    @PostMapping
    public ResponseEntity<MatchResponseDto> ReserverMatch(@RequestBody MatchRequestDto matchRequestDto) throws MatchException {
           MatchResponseDto matchResponseDto = matchService.addNewMatch(matchRequestDto);
           return ResponseEntity.ok().body(matchResponseDto);

    }
}
