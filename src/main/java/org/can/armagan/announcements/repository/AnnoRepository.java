package org.can.armagan.announcements.repository;

import org.can.armagan.announcements.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnoRepository extends JpaRepository<Announcement, Long> {
}
