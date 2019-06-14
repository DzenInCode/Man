package MVC;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PlayerModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String name;
    @Column
    private  String last_name;
    @Column
    private Date birthday;
    @Column
    private  String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamModel team;

    public PlayerModel() {
    }

    public PlayerModel(String name, String last_name, Date birthday, String position, TeamModel team) {
        this.name = name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.position = position;
        this.team = team;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }
}