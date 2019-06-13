package MVC.Repos;

import org.springframework.data.repository.CrudRepository;

import MVC.PlayerModel;


public interface PlayersRepository extends CrudRepository<PlayerModel,Long>{

}