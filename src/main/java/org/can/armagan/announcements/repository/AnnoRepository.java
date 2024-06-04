package org.can.armagan.announcements.repository;

import org.can.armagan.announcements.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnnoRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAnnoById(Long annoId);
    List<Announcement> findByCreator(String creator);

}
