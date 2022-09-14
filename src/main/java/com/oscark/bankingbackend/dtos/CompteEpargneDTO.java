package com.oscark.bankingbackend.dtos;


import com.oscark.bankingbackend.enumeration.CompteStatus;
import lombok.Data;
import java.util.Date;

 @Data
public class CompteEpargneDTO extends CompteDTO {

    private String id;
    private double solde;
    private Date dateCreation;
    private CompteStatus compteStatus;
    private ClientDTO clientDTO;
    private double interet;


}
