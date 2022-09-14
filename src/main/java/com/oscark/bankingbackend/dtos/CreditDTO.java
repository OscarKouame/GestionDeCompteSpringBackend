package com.oscark.bankingbackend.dtos;

import lombok.Data;

@Data
public class CreditDTO {
  private String idCompte;
  private double montant;
  private String description;
}
