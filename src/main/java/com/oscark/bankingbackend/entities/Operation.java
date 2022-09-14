package com.oscark.bankingbackend.entities;

import com.oscark.bankingbackend.enumeration.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCreation;
    private double montant;
    public String description;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @ManyToOne
    private Compte compte;
}
