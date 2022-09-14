package com.oscark.bankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
 @Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Client {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;

}
