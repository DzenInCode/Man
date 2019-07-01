package  MVC;

import MVC.Repos.PlayersRepository;
import MVC.Repos.TeamsRepository;
import MVC.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")



@Controller
public class CreateController {

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    PlayersRepository playersRepository;

    @GetMapping("/show_players") /////показати всіх гравців
    public List<PlayerModel> ShowAllPlayers() {
        return (List<PlayerModel>) playersRepository.findAll();
    }

    @GetMapping("/show_capitan/{id}") //показати капітана команди
    public ResponseEntity<PlayerModel> ShowCapitan(@PathVariable(value = "id") Long teamid)
            throws ResourceNotFoundException {
        TeamModel team = teamsRepository.findById(teamid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " ));
        PlayerModel teamCapitan;
        teamCapitan =team.getCapitan();
        return ResponseEntity.ok().body(teamCapitan);
    }
    @GetMapping("/show_players_in_team/{id}") //показати    гравців команди
    public ResponseEntity <List<PlayerModel>>ShowPlayersInTeam(@PathVariable(value = "id") Long teamid)
            throws ResourceNotFoundException {
        TeamModel team = teamsRepository.findById(teamid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " ));
        List<PlayerModel> players;
        players =team.getPlayers();
        return ResponseEntity.ok().body(players);
    }


    @GetMapping("/show_teams") //////показати всі команди
    public List<TeamModel> ShowAllTeams() {
        return (List<TeamModel>) teamsRepository.findAll();
    }

    @PostMapping("/create_team")  //створити команду
    public ResponseEntity<TeamModel> createTeam(@Valid @RequestBody TeamModel newTeam) throws ResourceNotFoundException {
       return ResponseEntity.ok(teamsRepository.save(newTeam));
    }
    @PostMapping("/create_player") //створити гравця
    public ResponseEntity<PlayerModel> createPlayer(@Valid @RequestBody PlayerModel newPlayer) throws ResourceNotFoundException {
        return ResponseEntity.ok(playersRepository.save(newPlayer));
    }
    @PutMapping("/addPlayersInTeam/{id}") //додати команду
    public ResponseEntity<TeamModel> AddPlayers(@PathVariable("id") Long id, @Valid @RequestBody PlayerModel newPlayer ) throws ResourceNotFoundException {
        TeamModel oldTeam = teamsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + id));
    List<PlayerModel> playersInTeam =oldTeam.getPlayers();
    playersInTeam.add(newPlayer);
    oldTeam.setPlayers(playersInTeam);

        return ResponseEntity.ok(teamsRepository.save(oldTeam));
    }
    @PutMapping("/asignCapitan/{id}") //призначити капітана
    public ResponseEntity<TeamModel> AssignCapitan(@PathVariable("id") Long id, @Valid @RequestBody PlayerModel capitan ) throws ResourceNotFoundException {
        TeamModel oldTeam = teamsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + id));
        oldTeam.setCapitan(capitan);

        return ResponseEntity.ok(teamsRepository.save(oldTeam));
    }

    @PutMapping("/write_teams/{id}") //редагувати команду
    public ResponseEntity<TeamModel> writerTeam(@PathVariable("id") Long id, @Valid @RequestBody TeamModel newTeam) throws ResourceNotFoundException {
        TeamModel oldTeam = teamsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found for this id :: " + id));

        oldTeam.setName(newTeam.getName());
        oldTeam.setCapitan(newTeam.getCapitan());
        oldTeam.setPlayers(newTeam.getPlayers());

        return ResponseEntity.ok(teamsRepository.save(oldTeam));
    }
    @PutMapping("/write_players/{id}") //редагувати гравця
    public ResponseEntity<PlayerModel> writerPlayer(@PathVariable("id") Long id, @Valid @RequestBody PlayerModel newPlayer ) throws ResourceNotFoundException {
        PlayerModel oldPlayer = playersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + id));

        oldPlayer.setName(newPlayer.getName());
        oldPlayer.setBirthday(newPlayer.getBirthday());
        oldPlayer.setLast_name(newPlayer.getLast_name());
        oldPlayer.setTeam(newPlayer.getTeam());


        return ResponseEntity.ok(playersRepository.save(oldPlayer));
    }
    @DeleteMapping("/del_players/{id}") //видалити  гравця
    public Map<String, Boolean> deletePlayer(@PathVariable(value = "id") Long playerId)
            throws ResourceNotFoundException {
        PlayerModel player = playersRepository.findById (playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + playerId));

        playersRepository.delete(player);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @DeleteMapping("/del_team/{id}") //видалити  команду
    public Map<String, Boolean> deleteTeam(@PathVariable(value = "id") Long teamId)
            throws ResourceNotFoundException {
        TeamModel team = teamsRepository.findById (teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + teamId));

        teamsRepository.delete(team);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
