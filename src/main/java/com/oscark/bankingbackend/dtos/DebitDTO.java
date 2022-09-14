package com.oscark.bankingbackend.dtos;

import lombok.Data;

@Data
public class DebitDTO {
  private String idCompte;
  private double montant;
  private String description;
}
