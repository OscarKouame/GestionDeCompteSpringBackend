package com.oscark.bankingbackend.services;

import com.oscark.bankingbackend.dtos.*;
import com.oscark.bankingbackend.exceptions.SoldeInsuffisantException;

import java.util.List;

public interface CompteService {

    ClientDTO saveClient(ClientDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    void deleteClient(Long id);

    CompteCourantDTO saveCompteCourant(double soldeinitial, double decouvert, Long idClient)  ;
    CompteEpargneDTO saveCompteEpargne(double soldeinitial, double interet, Long idClient)  ;
    List<ClientDTO> listclients();
    CompteDTO getCompte(String idCompte);
    void debit(String IdCompte, double Montant, String Description) throws SoldeInsuffisantException;
    void credit(String idCompte, double montant, String description);
    void transfert(String idCompteSource, double montant,String idComptedestination) throws SoldeInsuffisantException;
    List<CompteDTO> listcomptes();
    ClientDTO getclientById(Long id);
    List<OperationDTO> historyCompte(String compteId);

   HistoriqueCompteDTO gethistoryCompte(String idCompte, int page, int size);

    List<ClientDTO> searchClient(String motCle);
}
