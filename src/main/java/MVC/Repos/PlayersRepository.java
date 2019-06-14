package MVC.Repos;

import MVC.exeption.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import MVC.PlayerModel;

import java.util.Optional;


public interface PlayersRepository extends CrudRepository<PlayerModel,Long>{

}