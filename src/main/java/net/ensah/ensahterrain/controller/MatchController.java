package net.ensah.ensahterrain.controller;

import net.ensah.ensahterrain.dto.MatchRequestDto;
import net.ensah.ensahterrain.dto.MatchResponseDto;
import net.ensah.ensahterrain.exceptions.MatchException;
import net.ensah.ensahterrain.service.Impl.MatchServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@CrossOrigin(origins = "http://localhost:4200")
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MatchResponseDto> addNewMatch(@RequestBody MatchRequestDto matchRequestDto,Principal principal) throws MatchException {
        MatchResponseDto matchResponseDto = matchService.addNewMatch(matchRequestDto,principal);
        return ResponseEntity.ok().body(matchResponseDto);

    }
}