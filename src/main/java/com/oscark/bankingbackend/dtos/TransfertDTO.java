package com.oscark.bankingbackend.dtos;

import lombok.Data;

@Data
public class TransfertDTO {
  private String idCompteSource;
  private String idCompteDestination;
  private double montant;
}
