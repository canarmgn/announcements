package org.can.armagan.announcements.auth.repository;


import org.can.armagan.announcements.auth.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}