package almeida.ferreira.junio.bluetasks.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface AppUserRepository extends CrudRepository<AppUser, Integer>{
	
	public AppUser findByUsername(String username);
}
