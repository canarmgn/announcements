package org.can.armagan.announcements.auth.model;

import javax.persistence.*;



public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createdBy;
    private int supporters;

}
