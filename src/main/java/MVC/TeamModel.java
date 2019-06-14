package MVC;

import javax.persistence.*;
import java.util.List;

@Entity
public class TeamModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;


    @OneToOne
    @JoinColumn(name = "capitan_id", referencedColumnName = "id"  )
    private PlayerModel capitan;

    @OneToMany(mappedBy = "team")
    private List<PlayerModel> players;

    public TeamModel() {
    }

    @OneToMany(mappedBy = "team")
    private List<PlayerModel> playersInTeam;

    public List<PlayerModel> getPlayers() {
        return players;
    }
    public PlayerModel getCapitan() {
        return capitan;
    }

    public void setCapitan(PlayerModel capitan) {
        this.capitan = capitan;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public TeamModel(String name, PlayerModel id_capitans) {
        this.name = name;
    }

    @Override
    public String toString() {
        String format = String.format(
                "Team[ name ='%s']");
        return format;
    }

}