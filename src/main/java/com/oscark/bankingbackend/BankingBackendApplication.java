package com.oscark.bankingbackend;

import com.oscark.bankingbackend.dtos.ClientDTO;
import com.oscark.bankingbackend.entities.*;
import com.oscark.bankingbackend.enumeration.CompteStatus;
import com.oscark.bankingbackend.enumeration.TypeOperation;
import com.oscark.bankingbackend.repositories.ClientRepository;
import com.oscark.bankingbackend.repositories.CompteRepository;
import com.oscark.bankingbackend.repositories.OperationRepository;
import com.oscark.bankingbackend.services.CompteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingBackendApplication.class, args);

    }
    //@Bean
    CommandLineRunner start(CompteService compteService){
        return args ->{

            ClientDTO clientDTOientDTO=new ClientDTO();
            clientDTOientDTO.setNom("Kouame");
            clientDTOientDTO.setEmail("Kouame@gmail.com");
            compteService.saveClient(clientDTOientDTO);
           /* Stream.of("karim","Ester","Guy","Arnaud","Serge").forEach(nom->{
                Client client= new Client();
                client.setNom(nom);
                client.setEmail(nom+"@gmail.com");
                clientRepository.save(client);
            });
            clientRepository.findAll().forEach(clt->{
                CompteCourant compte= new CompteCourant();
                compte.setId(UUID.randomUUID().toString());
                compte.setSolde(Math.random()*90000);
                compte.setDateCreation(new Date());
                compte.setCompteStatus(CompteStatus.CREE);
                compte.setClient(clt);
                compte.setDecouvert(9000);
                compteRepository.save(compte);

            });

            clientRepository.findAll().forEach(clt->{
                CompteEpargne compteEpargne= new CompteEpargne();
                compteEpargne.setId(UUID.randomUUID().toString());
                compteEpargne.setSolde(Math.random()*90000);
                compteEpargne.setDateCreation(new Date());
                compteEpargne.setCompteStatus(CompteStatus.CREE);
                compteEpargne.setClient(clt);
                compteEpargne.setInteret(5.5);
                compteRepository.save(compteEpargne);

            });

            compteRepository.findAll().forEach(cpte->{
                for( int i=0;i<10;i++){
                    Operation  op= new Operation();
                    op.setDateCreation(new Date());
                    op.setMontant(Math.random()*12000);
                    op.setTypeOperation(Math.random()>0.5? TypeOperation.DEBIT: TypeOperation.CREDIT);
                    op.setCompte(cpte);
                    operationRepository.save(op);

                }

            });

            Compte compte=compteRepository.findById("12139df5-e1f2-4952-87e5-b59de7da4319").orElse(null);
            System.out.println(compte.getId());
            System.out.println(compte.getDateCreation());
            System.out.println(compte.getSolde());
            System.out.println(compte.getCompteStatus());
            System.out.println(compte.getClient().getNom());
            if(compte instanceof CompteCourant){
                System.out.println("Decouvert =>"+((CompteCourant)compte).getDecouvert());
            } else if(compte instanceof CompteEpargne) {
                System.out.println("Taux d'interet =>"+((CompteEpargne)compte).getInteret());
            }

            compte.getOperations().forEach(operation -> {
                System.out.println(operation.getMontant());
                System.out.println(operation.getTypeOperation());
                System.out.println(operation.getDateCreation());

            });*/

        };

    }

}
