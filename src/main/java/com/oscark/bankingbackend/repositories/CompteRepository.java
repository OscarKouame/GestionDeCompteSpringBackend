package com.oscark.bankingbackend.repositories;

import com.oscark.bankingbackend.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}