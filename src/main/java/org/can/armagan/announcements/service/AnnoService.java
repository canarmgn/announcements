package org.can.armagan.announcements.service;

import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.repository.AnnoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnoService {

    private final AnnoRepository annoRepository;

    public void addContent(String content) {
        Announcement announcement = new Announcement();
        announcement.setContent(content);
        announcement.setSupporters(3);
        annoRepository.save(announcement);
    }
}
