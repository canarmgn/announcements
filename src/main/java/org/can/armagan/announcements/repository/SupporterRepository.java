package org.can.armagan.announcements.repository;

import org.can.armagan.announcements.model.Supporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupporterRepository extends JpaRepository<Supporter, Long> {
    Optional<Supporter> findByName(String name);
    List<Supporter> findAll();
    Optional<Supporter> findById(Long id);
}

