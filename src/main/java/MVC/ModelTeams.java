package MVC;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ModelTeams {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long team_id;
    @Column
    private String names;


    @ManyToOne
    @JoinColumn(name = "capitan_id")
    private ModelPlayers capitan;


    public ModelTeams() {
    }

    @OneToMany(mappedBy = "teams")
    private List<ModelPlayers> players;

    public List<ModelPlayers> getPlayers() {
        return players;
    }
    public ModelPlayers getCapitan() {
        return capitan;
    }

    public void setCapitan(ModelPlayers capitan) {
        this.capitan = capitan;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public String getNames() {
        return names;
    }

    public void setPlayers(List<ModelPlayers> players) {
        this.players = players;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public void setNames(String names) {
        this.names = names;
    }



    public ModelTeams(String names, ModelPlayers id_capitans) {
        this.names = names;
    }

    @Override
    public String toString() {
        String format = String.format(
                "Team[ names ='%s']");
        return format;
    }

}