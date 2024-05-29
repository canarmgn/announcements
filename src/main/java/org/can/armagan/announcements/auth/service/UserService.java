package org.can.armagan.announcements.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final ArticleRepository articleRepository;

    public void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);
    }

    public void addRoleToUser(long userId, long roleId) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(userId);
        userRoles.setRoleId(roleId);
        userRolesRepository.save(userRoles);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Article> dailyHeadlines() {
        return articleRepository.findAll();
    }
}
