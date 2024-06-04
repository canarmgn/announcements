package org.can.armagan.announcements.repository;

import org.can.armagan.announcements.model.AnnouncementSupporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnnouncementSupporterRepository extends JpaRepository<AnnouncementSupporter, Long> {

    List<AnnouncementSupporter> findBySupporterId(Long supporterId);
    List<AnnouncementSupporter> findByAnnouncementId(Long announcementId);
}
