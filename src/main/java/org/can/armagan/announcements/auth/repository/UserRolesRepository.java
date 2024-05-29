package org.can.armagan.announcements.auth.repository;

import org.can.armagan.announcements.auth.model.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository  extends JpaRepository<User_Roles, Long> {
}
