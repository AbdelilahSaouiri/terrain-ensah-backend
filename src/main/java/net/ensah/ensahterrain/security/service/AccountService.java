package net.ensah.ensahterrain.security.service;

import net.ensah.ensahterrain.exceptions.UserException;
import net.ensah.ensahterrain.security.entity.Role;
import net.ensah.ensahterrain.security.entity.User;

public interface AccountService {

    User addNewUser(User user) throws UserException;
    Role addNewRole(Role role);
    void addNewRoleToUser(Role role, User user);
    void removeRoleFromUser(Role role, User user);

}
