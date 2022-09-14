package com.oscark.bankingbackend.web;

import com.oscark.bankingbackend.dtos.ClientDTO;
import com.oscark.bankingbackend.services.CompteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ClientRestController {

    private CompteService compteService;

    @GetMapping("/clients")
    public List<ClientDTO> listclients(){
        return compteService.listclients();
    }


    @GetMapping("/clients/search")
    public List<ClientDTO> searchClient(@RequestParam(name="motCle",defaultValue = "") String motCle){
        return compteService.searchClient("%"+motCle+"%");
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return compteService.getclientById(id);
    }

    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO){
        return compteService.saveClient(clientDTO);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        clientDTO.setId(id);
        return compteService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id){
         compteService.deleteClient(id);
    }
}
