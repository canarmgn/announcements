package org.can.armagan.announcements.controller;

import org.can.armagan.announcements.auth.model.Announcement;
import org.can.armagan.announcements.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @PostMapping
    public Announcement createAnnouncement(@RequestBody Announcement announcement, @AuthenticationPrincipal User user) {
        if (!user.getRoles().contains("EDITOR") && !user.getRoles().contains("ADMIN")) {
            throw new UnauthorizedException("You do not have permission to create announcements.");
        }
        return announcementService.createAnnouncement(announcement);
    }

    @PostMapping("/{id}/support")
    public void supportAnnouncement(@PathVariable Long id, @AuthenticationPrincipal User user) {
        announcementService.supportAnnouncement(id, user);
    }
}


}
