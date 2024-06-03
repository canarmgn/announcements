package org.can.armagan.announcements.controller;


import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.model.request.AnnouncementRequest;
import org.can.armagan.announcements.model.request.SupportRequest;
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

    @PostMapping("/providesupport")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String support(@RequestBody SupportRequest supportRequest) {
        return annoService.addSupport(supportRequest.getId());
    }

    @GetMapping("/announcements/{annoId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Announcement> getAnno(@PathVariable("annoId") Long annoId) {
        return annoService.getAnno(annoId);
    }

}
