package org.can.armagan.announcements.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "supporter")
public class Supporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "supporters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Announcement> announcements = new HashSet<>();
}
