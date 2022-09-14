package com.oscark.bankingbackend.mappers;

import com.oscark.bankingbackend.dtos.ClientDTO;
import com.oscark.bankingbackend.dtos.CompteCourantDTO;
import com.oscark.bankingbackend.dtos.CompteEpargneDTO;
import com.oscark.bankingbackend.dtos.OperationDTO;
import com.oscark.bankingbackend.entities.Client;
import com.oscark.bankingbackend.entities.CompteCourant;
import com.oscark.bankingbackend.entities.CompteEpargne;
import com.oscark.bankingbackend.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankingMapperImpl {

    public ClientDTO fromClient(Client client){
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }
    public Client fromClientDTO(ClientDTO clientDTO){
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }

    public CompteCourantDTO fromCompteCourant(CompteCourant compteCourant){
        CompteCourantDTO compteCourantDTO=new CompteCourantDTO();
        BeanUtils.copyProperties(compteCourant,compteCourantDTO);
        compteCourantDTO.setClientDTO(fromClient(compteCourant.getClient()));
        compteCourantDTO.setType(compteCourant.getClass().getSimpleName());
        return compteCourantDTO;
    }

    public CompteCourant fromCompteCourantDTO(CompteCourantDTO compteCourantDTO){
        CompteCourant compteCourant=new CompteCourant();
        BeanUtils.copyProperties(compteCourantDTO,compteCourant);
        compteCourant.setClient(fromClientDTO(compteCourantDTO.getClientDTO()));
        return compteCourant;
    }

    public CompteEpargneDTO fromCompteEpargne(CompteEpargne compteEpargne){
        CompteEpargneDTO compteEpargneDTO=new CompteEpargneDTO();
        BeanUtils.copyProperties(compteEpargne,compteEpargneDTO);
        compteEpargneDTO.setClientDTO(fromClient(compteEpargne.getClient()));
        compteEpargneDTO.setType(compteEpargne.getClass().getSimpleName());
        return compteEpargneDTO;
    }

    public CompteEpargne fromCompteEpargneDTO(CompteEpargneDTO compteEpargneDTO){
        CompteEpargne compteEpargne=new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDTO,compteEpargne);
        compteEpargne.setClient(fromClientDTO(compteEpargneDTO.getClientDTO()));
        return compteEpargne;
    }

    public OperationDTO fromOperation(Operation operation){
        OperationDTO operationDTO=new OperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);
        return operationDTO;
    }

    public Operation fromOperationDTO(OperationDTO operationDTO){
        Operation operation=new Operation();
        BeanUtils.copyProperties(operationDTO,operation);
        return operation;
    }
}
