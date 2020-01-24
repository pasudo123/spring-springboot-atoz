package edu.pasudo123.querydsl.app.league.api;

import edu.pasudo123.querydsl.app.league.dto.LeagueDto;
import edu.pasudo123.querydsl.app.league.model.League;
import edu.pasudo123.querydsl.app.league.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pasudo123 on 2019-09-07
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueRepository leagueRepository;

    @PostMapping("/league")
    public League createNewLeague(@RequestBody LeagueDto.createRequest createRequest){
        return null;
    }

    @GetMapping("/league/{leagueId}")
    public League findLeagueById(@PathVariable("leagueId") Long id){
        return null;
    }

}
