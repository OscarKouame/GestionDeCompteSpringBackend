package com.oscark.bankingbackend.repositories;

import com.oscark.bankingbackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContains(String motcle);

    @Query("select c from Client c where c.nom like :mcl ")
    List<Client> searchClient(@Param(value="mcl") String motcle);
}