package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long nid;
    @Column(length = 55)
    private String title;
    private String detail;
}
