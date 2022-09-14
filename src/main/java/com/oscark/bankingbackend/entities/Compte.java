package com.oscark.bankingbackend.entities;

import com.oscark.bankingbackend.enumeration.CompteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
public class Compte {

    @Id
    private String id;
    private double solde;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private CompteStatus compteStatus;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "compte",fetch=FetchType.LAZY)
    private List<Operation> operations;


}
