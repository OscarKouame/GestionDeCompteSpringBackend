package com.oscark.bankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
    private double decouvert;

}
