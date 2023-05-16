package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 110)
    private String name;
    @Column(unique = true, length = 150)
    private String email;
    @Column(length = 15)
    private String password;
    private Integer age;
    private Boolean status;


}
