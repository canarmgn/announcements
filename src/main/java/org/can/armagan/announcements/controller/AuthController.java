package org.can.armagan.announcements.controller;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.can.armagan.announcements.auth.model.Announcement;
import org.can.armagan.announcements.auth.model.request.AuthRequest;
import org.can.armagan.announcements.auth.service.AuthService;
import org.can.armagan.announcements.auth.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @DeleteMapping("/deleteuser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "The user has been successfully deleted.";
    }

    @PostMapping("/newAnnouncement")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public Announcement createAnnouncement(@RequestBody Announcement announcement, @AuthenticationPrincipal User user) {
         announcementService.createAnnouncement(announcement);
         return "announcement has been successfully created";
    }

    @PostMapping("/{id}/support")
    public void supportAnnouncement(@PathVariable Long id, @AuthenticationPrincipal User user) {
        announcementService.supportAnnouncement(id, user);
        return "Supported successfully.";
    }
}
