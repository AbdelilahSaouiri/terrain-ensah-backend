package net.ensah.ensahterrain.repository;

import net.ensah.ensahterrain.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
      Match   findByMatchTimeAndDayNumber(Integer matchTime,Integer dayNumber);
      List<Match>  findAllByDayNumber(Integer jour);
     Match  findByMatchPlayerAndDayNumber(String matchPlayer,Integer Day);
}
