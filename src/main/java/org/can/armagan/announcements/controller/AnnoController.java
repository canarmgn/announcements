package org.can.armagan.announcements.controller;


import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.model.request.AnnouncementRequest;
import org.can.armagan.announcements.model.response.AnnouncementResponse;
import org.can.armagan.announcements.service.AnnoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnnoController {

    private final AnnoService annoService;

    @PostMapping("/newcontent")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public Announcement createAnnouncement(@RequestBody AnnouncementRequest request) {
        return annoService.addContent(request.getContent(), request.getSubject());
    }

    @PostMapping("/announcement/{id}/support")
    public String addSupportToAnnouncement(@PathVariable String id) {
        return annoService.addSupport(id);
    }

    @GetMapping("/announcements/{creator}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AnnouncementResponse> getAnnoCreator(@PathVariable String creator) {
        return annoService.getAnnoCreator(creator);
    }

    @GetMapping("/announcements/supporter/{name}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AnnouncementResponse> getSupportedAnno(@PathVariable String name) {
        return annoService.getSupportedAnno(name);
    }



}
