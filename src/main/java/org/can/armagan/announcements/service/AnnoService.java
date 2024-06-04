package org.can.armagan.announcements.service;

import lombok.RequiredArgsConstructor;
import org.can.armagan.announcements.auth.jwt.JwtFilter;
import org.can.armagan.announcements.model.Announcement;
import org.can.armagan.announcements.model.AnnouncementSupporter;
import org.can.armagan.announcements.model.Supporter;
import org.can.armagan.announcements.model.response.AnnouncementResponse;
import org.can.armagan.announcements.repository.AnnoRepository;
import org.can.armagan.announcements.repository.AnnouncementSupporterRepository;
import org.can.armagan.announcements.repository.SupporterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnoService {

    private final AnnoRepository annoRepository;
    private final SupporterRepository supporterRepository;
    private final AnnouncementSupporterRepository   announcementSupporterRepository;


    public Announcement addContent(String content, String subject) {
        String creator = JwtFilter.getCurrentUser();

        Announcement announcement = new Announcement();
        announcement.setContent(content);
        announcement.setSubject(subject);
        announcement.setCreator(creator);
        return annoRepository.save(announcement);
    }

    @Transactional
    public String addSupport(String id) {
        String supporterName = JwtFilter.getCurrentUser();

        Supporter supporter = supporterRepository.findByName(supporterName)
                .orElseGet(() -> {
                    Supporter newSupporter = new Supporter();
                    newSupporter.setName(supporterName);
                    return supporterRepository.save(newSupporter);
                });

        Optional<Announcement> optionalAnnouncement = annoRepository.findById(Long.parseLong(id));
        if (optionalAnnouncement.isPresent()) {
            Announcement announcement = optionalAnnouncement.get();
            announcement.getSupporters().add(supporter);
            annoRepository.save(announcement);

            return "Announcement supported successfully.";
        } else {
            return "Announcement not found for id: " + id;
        }
    }

    public List<AnnouncementResponse> getAnnoCreator(String creator) {
        List<Announcement> announcements = annoRepository.findByCreator(creator);
        List<AnnouncementResponse> responses = new ArrayList<>();
        for (Announcement announcement : announcements) {
            AnnouncementResponse response = new AnnouncementResponse();
            response.setContent(announcement.getContent());
            response.setSubject(announcement.getSubject());
            response.setCreator(announcement.getCreator());
            responses.add(response);
        }
        return responses;
    }

    public List<AnnouncementResponse> getSupportedAnno(String name) {
        Supporter supporter = supporterRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Supporter not found"));

        List<AnnouncementSupporter> announcementSupporters = announcementSupporterRepository.findBySupporterId(supporter.getId());

        List<AnnouncementResponse> responses = new ArrayList<>();
        for (AnnouncementSupporter announcementSupporter : announcementSupporters) {
            Announcement announcement = annoRepository.findById(announcementSupporter.getAnnouncementId())
                    .orElseThrow(() -> new RuntimeException("Announcement not found"));

            AnnouncementResponse response = new AnnouncementResponse();
            response.setContent(announcement.getContent());
            response.setSubject(announcement.getSubject());
            response.setCreator(announcement.getCreator());
            responses.add(response);
        }
        return responses;
    }
}




