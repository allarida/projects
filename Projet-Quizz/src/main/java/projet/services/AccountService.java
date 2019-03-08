package projet.services;

import projet.entities.AppRole;
import projet.entities.AppUser;

public interface AccountService {
 public AppUser saveUser(AppUser user);
 public AppRole saveRole(AppRole role);
 public void addRoleToUser(String username, String roleName);
 public AppUser findUserByUsername(String username);
}
