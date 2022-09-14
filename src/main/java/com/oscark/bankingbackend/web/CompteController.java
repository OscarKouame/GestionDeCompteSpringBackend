package com.oscark.bankingbackend.web;

import com.oscark.bankingbackend.dtos.*;
import com.oscark.bankingbackend.exceptions.SoldeInsuffisantException;
import com.oscark.bankingbackend.services.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CompteController {

    private CompteService compteService;

    @GetMapping("/comptes/{idcompte}")
    public CompteDTO getCompte(@PathVariable String idcompte){
       return compteService.getCompte(idcompte);

    }

    @GetMapping("/comptes")
    public List<CompteDTO> ListesComptes(){
        return compteService.listcomptes();
    }

    @GetMapping("comptes/{idCompte}/operations")
    public List<OperationDTO> historiqueDeCompte(@PathVariable String idCompte){
        return compteService.historyCompte(idCompte);
    }

    @GetMapping("comptes/{idCompte}/pageOperations")
    public HistoriqueCompteDTO gethistoriqueDeCompte(
            @PathVariable String idCompte,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5") int size
                                                    ){
        return compteService.gethistoryCompte(idCompte,page,size);
    }

    @PostMapping("/comptes/compteCourant")
    public CompteCourantDTO saveCompteCourant(@RequestParam double soldeinitial,
                                              @RequestParam double decouvert,
                                              @RequestParam Long idClient){

        return compteService.saveCompteCourant(soldeinitial,decouvert,idClient);
    }

    @PostMapping("/comptes/compeepargne")
    public CompteEpargneDTO saveCompteEpargne(@RequestParam double soldeinitial,
                                              @RequestParam double interet,
                                              @RequestParam long idClient){
        return compteService.saveCompteEpargne(soldeinitial,interet,idClient);
    }

    @PostMapping("/comptes/debiter")
    public DebitDTO debiter(@RequestBody DebitDTO debitDTO) throws SoldeInsuffisantException {
       compteService.debit(debitDTO.getIdCompte(),debitDTO.getMontant(),debitDTO.getDescription());
      return debitDTO;
    }

    @PostMapping("/comptes/crediter")
    public CreditDTO crediter(@RequestBody CreditDTO creditDTO){
        compteService.credit(creditDTO.getIdCompte(),creditDTO.getMontant(),creditDTO.getDescription());
       return creditDTO;
    }

    @PostMapping("/comptes/transfert")
    public TransfertDTO transferer(@RequestBody TransfertDTO transfertDTO) throws SoldeInsuffisantException {
        compteService.transfert(transfertDTO.getIdCompteSource(),transfertDTO.getMontant(),transfertDTO.getIdCompteDestination());
        return transfertDTO;
    }
}
