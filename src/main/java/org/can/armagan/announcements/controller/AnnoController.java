package org.can.armagan.announcements.controller;

import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.service.AnnoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnoController {

    private final AnnoService annoService;

    @PostMapping("/newContent")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public String addContent(@RequestBody Announcement announcement) {
        annoService.addContent(announcement.getContent());
        return "The content has been successfully added.";
    }

}
