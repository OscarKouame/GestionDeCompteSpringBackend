package com.oscark.bankingbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoriqueCompteDTO {
    private String idCompte;
    private double solde;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<OperationDTO> operationDTOS;
}
