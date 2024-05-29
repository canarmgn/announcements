package org.can.armagan.announcements.auth.service;

import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.auth.model.User;
import org.can.armagan.announcements.auth.model.User_Roles;
import org.can.armagan.announcements.auth.repository.UserRepository;
import org.can.armagan.announcements.auth.repository.UserRolesRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    public void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);
    }



    public void addRoleToUser(long userId, long roleId) {
        User_Roles userRoles = new User_Roles();
        userRoles.setUser_id(userId);
        userRoles.setRole_id(roleId);
        userRolesRepository.save(userRoles);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
