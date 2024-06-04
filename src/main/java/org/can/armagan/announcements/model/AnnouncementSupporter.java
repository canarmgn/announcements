package org.can.armagan.announcements.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "announcement_supporter")
public class AnnouncementSupporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column (name = "announcement_id")
    public Long announcementId;
    @Column (name = "supporter_id")
    public Long supporterId;
}
