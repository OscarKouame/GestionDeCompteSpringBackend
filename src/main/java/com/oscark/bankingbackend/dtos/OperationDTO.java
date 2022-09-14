package com.oscark.bankingbackend.dtos;

import com.oscark.bankingbackend.enumeration.TypeOperation;
import lombok.Data;

import java.util.Date;

@Data
public class OperationDTO {
    private Long id;
    private Date dateCreation;
    private double montant;
    public String description;
    private TypeOperation typeOperation;

}
