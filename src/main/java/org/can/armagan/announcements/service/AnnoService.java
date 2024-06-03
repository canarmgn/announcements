package org.can.armagan.announcements.service;

import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.auth.jwt.JwtFilter;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.repository.AnnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnoService {

    private final AnnoRepository annoRepository;

    public Announcement addContent(String content, String subject) {
        String creator = JwtFilter.getCurrentUser();

        Announcement announcement = new Announcement();
        announcement.setContent(content);
        announcement.setSubject(subject);
        announcement.setCreator(creator);
        announcement.setSupporters("");
        return annoRepository.save(announcement);
    }
    public String addSupport(String id) {
        String supporter = JwtFilter.getCurrentUser();

        Optional<Announcement> optionalAnnouncement = annoRepository.findById(Long.parseLong(id));
        if (optionalAnnouncement.isPresent()) {
            Announcement announcement = optionalAnnouncement.get();
            announcement.setSupporters(announcement.getSupporters() + "," + supporter);
            annoRepository.save(announcement);
            return "Announcement supported successfully.";
        } else {
            return "Announcement not found for id: " + id;
        }
    }

    public List<Announcement> getAnno(Long annoId) {
        return annoRepository.findAnnoById(annoId);
    }

}
