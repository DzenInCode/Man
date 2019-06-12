package  MVC;

import MVC.Repos.TeamsRepository;
import MVC.ModelTeams;
import MVC.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;


@RestController
@RequestMapping("/api/v1")



@Controller
public class CreateController {
    @Autowired
    TeamsRepository teamsRepository;

    @GetMapping("/teams")
    public List<ModelTeams> getAllModelTeams() {
        return (List<ModelTeams>) teamsRepository.findAll();
    }
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @PutMapping("/teams/{id}")
    public ResponseEntity<ModelTeams> updateEmployee(@PathVariable(value = "id") Long teamsId,
                                                   @Valid @RequestBody ModelTeams teamsDetails) throws ResourceNotFoundException {
        ModelTeams teams;
        teams = teamsRepository.findById(teamsId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + teamsId));

        teams.setTeam_id(teamsDetails.getTeam_id());
        teams.setNames(teamsDetails.getNames());
        teams.setCapitan(teamsDetails.getCapitan());
        final ModelTeams updatedEmployee = teamsRepository.save(teams);
        return ResponseEntity.ok(updatedEmployee);
    }
}
