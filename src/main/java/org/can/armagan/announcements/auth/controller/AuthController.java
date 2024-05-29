package org.can.armagan.announcements.auth.controller;


import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.auth.model.User_Roles;
import org.can.armagan.announcements.auth.model.request.AuthRequest;
import org.can.armagan.announcements.auth.service.AuthService;
import org.can.armagan.announcements.auth.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) {
        return authService.getToken(authRequest);
    }
    @PostMapping("/adduser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insertUser(@RequestBody AuthRequest authRequest) {
        userService.createUser(authRequest.getUsername(), authRequest.getPassword());
        return "The user has been successfully registered.";
    }
    @PostMapping("/addrole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRole(@RequestBody User_Roles userRoles) {
        userService.addRoleToUser(userRoles.getUser_id(), userRoles.getRole_id());
        return "The role has been successfully added.";
    }

    @DeleteMapping("/deleteuser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "The user has been successfully deleted.";
    }

}
