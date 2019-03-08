package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
	public AppRole findByRoleName(String roleName);

}
