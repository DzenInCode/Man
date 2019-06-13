package  MVC;

import MVC.Repos.PlayersRepository;
import MVC.Repos.TeamsRepository;
import MVC.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/v1")



@Controller
public class CreateController {
    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    PlayersRepository playersRepository;

    @GetMapping("/show_players") /////показати всіх гравців
    public List<PlayerModel> getAllModelPlayers() {
        return (List<PlayerModel>) playersRepository.findAll();
    }


    @GetMapping("/show_teams") //////показати всі команди
    public List<TeamModel> getAllModelTeams() {
        return (List<TeamModel>) teamsRepository.findAll();
    }

    @PostMapping("/create_team")  //створити команду
    public ResponseEntity<TeamModel> createTeam(@Valid @RequestBody TeamModel newTeam) throws ResourceNotFoundException {
       return ResponseEntity.ok(teamsRepository.save(newTeam));
    }
    @PostMapping("/create_player") //створити гравця
    public ResponseEntity<PlayerModel> createTeam(@Valid @RequestBody PlayerModel newPlayer) throws ResourceNotFoundException {
        return ResponseEntity.ok(playersRepository.save(newPlayer));
    }

    @PutMapping("/teams/{id}") //редагувати команду
    public ResponseEntity<TeamModel> createTeam(@PathVariable("id") Long id, @Valid @RequestBody TeamModel newTeam) throws ResourceNotFoundException {
        TeamModel oldTeam = teamsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + id));

        oldTeam.setName(newTeam.getName());
        oldTeam.setCapitan(newTeam.getCapitan());
        oldTeam.setPlayers(newTeam.getPlayers());

        return ResponseEntity.ok(teamsRepository.save(oldTeam));
    }

}
