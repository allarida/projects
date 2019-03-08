package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.AppUser;

public interface AppUserRepository  extends JpaRepository<AppUser, Long>{
	public AppUser findByUsername(String username);

}
