package com.oscark.bankingbackend.services;

import com.oscark.bankingbackend.dtos.*;
import com.oscark.bankingbackend.entities.*;
import com.oscark.bankingbackend.enumeration.CompteStatus;
import com.oscark.bankingbackend.enumeration.TypeOperation;
import com.oscark.bankingbackend.exceptions.CompteNotFoundException;
import com.oscark.bankingbackend.exceptions.ClientNotFoundException;
import com.oscark.bankingbackend.exceptions.SoldeInsuffisantException;
import com.oscark.bankingbackend.mappers.BankingMapperImpl;
import com.oscark.bankingbackend.repositories.ClientRepository;
import com.oscark.bankingbackend.repositories.CompteRepository;
import com.oscark.bankingbackend.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CompteServieImpl implements CompteService {

    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    private BankingMapperImpl bankingMapperImpl;



    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("Enregistrement de client!");
        Client client= bankingMapperImpl.fromClientDTO(clientDTO);
        Client clientsave=clientRepository.save(client);
        return bankingMapperImpl.fromClient(clientsave) ;
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO){
        log.info("Mise à jour de client!");
        Client client= bankingMapperImpl.fromClientDTO(clientDTO);
        Client clientsave=clientRepository.save(client);
        return bankingMapperImpl.fromClient(clientsave) ;

    }

    @Override
    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    @Override
    public CompteCourantDTO saveCompteCourant(double soldeinitial, double decouvert, Long idClient) {
        log.info("Enregistrement de compte courant!");
        Client client= clientRepository.findById(idClient).orElse(null);
        if(client==null)
            throw new ClientNotFoundException("Client inexixtant");
        CompteCourant compteCourant=new CompteCourant();
        compteCourant.setId(UUID.randomUUID().toString());
        compteCourant.setDateCreation(new Date());
        compteCourant.setSolde(soldeinitial);
        compteCourant.setDecouvert(decouvert);
        compteCourant.setCompteStatus(CompteStatus.CREE);
        compteCourant.setClient(client);
        CompteCourant compteSave=compteRepository.save(compteCourant);
        return bankingMapperImpl.fromCompteCourant(compteSave);
    }

    @Override
    public CompteEpargneDTO saveCompteEpargne(double soldeinitial, double interet, Long idClient) {

        Client client= clientRepository.findById(idClient).orElse(null);
        if(client==null)
            throw new ClientNotFoundException("Client inexixtant");
        CompteEpargne compteEpargne=new CompteEpargne();
        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setSolde(soldeinitial);
        compteEpargne.setInteret(interet);
        compteEpargne.setCompteStatus(CompteStatus.CREE);
        compteEpargne.setClient(client);
        CompteEpargne compteEpargenrSave=compteRepository.save(compteEpargne);
        return bankingMapperImpl.fromCompteEpargne(compteEpargenrSave) ;
    }

    @Override
    public List<ClientDTO> listclients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> bankingMapperImpl.fromClient(client))
                .collect(Collectors.toList());
    }

    @Override
    public CompteDTO getCompte(String idCompte) {
        Compte compte=compteRepository.findById(idCompte)
                .orElseThrow(()->new CompteNotFoundException("Compte introuvable"));
        if(compte instanceof CompteCourant){
            CompteCourant compteCourant= (CompteCourant) compte;
            return bankingMapperImpl.fromCompteCourant(compteCourant);
        }else{
            CompteEpargne compteEpargne=(CompteEpargne) compte;
            return bankingMapperImpl.fromCompteEpargne(compteEpargne);
        }

    }

    @Override
    public void debit(String IdCompte, double Montant, String Description) throws SoldeInsuffisantException {
        Compte compte=compteRepository.findById(IdCompte)
                .orElseThrow(()->new CompteNotFoundException("Compte introuvable"));
        if(compte.getSolde()<Montant){
            throw new SoldeInsuffisantException("Solde insuffisant");
        }
        Operation op= new Operation();
        op.setCompte(compte);
        op.setTypeOperation(TypeOperation.DEBIT);
        op.setMontant(Montant);
        op.setDateCreation(new Date());
        op.setDescription(Description);
        operationRepository.save(op);
        compte.setSolde(compte.getSolde()-Montant);
        compteRepository.save(compte);


    }

    @Override
    public void credit(String idCompte, double montant, String description) {
        Compte compte=compteRepository.findById(idCompte)
                .orElseThrow(()->new CompteNotFoundException("Compte introuvable"));
        Operation op= new Operation();
        op.setCompte(compte);
        op.setTypeOperation(TypeOperation.CREDIT);
        op.setMontant(montant);
        op.setDateCreation(new Date());
        op.setDescription(description);
        operationRepository.save(op);
        compte.setSolde(compte.getSolde()+montant);
        compteRepository.save(compte);

    }

    @Override
    public void transfert(String idCompteSource, double montant,String idComptedestination ) throws SoldeInsuffisantException {
        debit(idCompteSource,montant,"Transfert à:"+ idComptedestination);
        credit(idComptedestination,montant,"Transfert de :"+ idCompteSource);

    }

    @Override
    public List<CompteDTO> listcomptes() {
        List<Compte> listescomptes = compteRepository.findAll();
       return listescomptes.stream().map(compte -> {
            if(compte instanceof CompteCourant){
                CompteCourant compteCourant= (CompteCourant) compte;
                return bankingMapperImpl.fromCompteCourant(compteCourant);
            }else{
                CompteEpargne compteEpargne=(CompteEpargne) compte;
                return bankingMapperImpl.fromCompteEpargne(compteEpargne);
            }

        }).collect(Collectors.toList());

    }

    @Override
    public ClientDTO getclientById(Long id){
        Client client= clientRepository.findById(id).orElse(null);
        if(client==null)
            throw new ClientNotFoundException("Client inexistant!");
       return   bankingMapperImpl.fromClient(client);

    }

    @Override
    public List<OperationDTO> historyCompte(String compteId) {
        List<Operation> compteOperations=operationRepository.findByCompteId(compteId);
        return compteOperations.stream().map(operation ->bankingMapperImpl.fromOperation(operation))
                .collect(Collectors.toList());
    }

    @Override
    public HistoriqueCompteDTO gethistoryCompte(String idCompte, int page, int size) {
        Compte compte=compteRepository.findById(idCompte)
                .orElse(null);
        if (compte==null)
            throw new CompteNotFoundException("Compte introuvable");
      Page<Operation> operations= operationRepository.findByCompteId(idCompte, PageRequest.of(page,size));
      HistoriqueCompteDTO historiqueCompteDTO= new HistoriqueCompteDTO();
      List<OperationDTO> operationDTOS= operations.stream().map(operation ->bankingMapperImpl
              .fromOperation(operation)).collect(Collectors.toList());
      historiqueCompteDTO.setOperationDTOS(operationDTOS);
      historiqueCompteDTO.setIdCompte(compte.getId());
      historiqueCompteDTO.setSolde(compte.getSolde());
      historiqueCompteDTO.setCurrentPage(page);
      historiqueCompteDTO.setPageSize(size);
      historiqueCompteDTO.setTotalPage(operations.getTotalPages());
        return historiqueCompteDTO;
    }

    @Override
    public List<ClientDTO> searchClient(String motCle) {
        List<Client> clientList=clientRepository.searchClient(motCle);
        return clientList.stream().map(client ->bankingMapperImpl.fromClient(client))
                .collect(Collectors.toList());
    }


}
