package MVC.Repos;
import org.springframework.data.repository.CrudRepository;

import MVC.TeamModel;


public interface TeamsRepository extends  CrudRepository <TeamModel, Long>{
//    @Override
//    Iterable<TeamModel> findAllById(Iterable<Long> iterable);

}
