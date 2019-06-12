package MVC.Repos;
import org.springframework.data.repository.CrudRepository;

import MVC.ModelTeams;
import java.util.List;


public interface TeamsRepository extends  CrudRepository <ModelTeams, Long>{
    @Override
    Iterable<ModelTeams> findAllById(Iterable<Long> iterable);

}
