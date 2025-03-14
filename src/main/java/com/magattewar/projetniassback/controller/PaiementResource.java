package com.magattewar.projetniassback.controller;


import com.magattewar.projetniassback.model.Paiement;
import com.magattewar.projetniassback.repository.PaiementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiement")
@Transactional
public class PaiementResource {

    private final Logger log = LoggerFactory.getLogger(PaiementResource.class);

    private static final String ENTITY_NAME = "testjhipsterPaiement";


    private final PaiementRepository paiementRepository;

    public PaiementResource(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }
//
@PostMapping("/paiements")
public List<Paiement> createPaiement(@RequestBody Paiement paiement) throws URISyntaxException {
    log.debug("REST request to save Paiement : {}", paiement);
    paiementRepository.save(paiement);
    return paiementRepository.findAll();
}

    @GetMapping("/paiements/all")
    public List<Paiement> getAll(){
        return paiementRepository.findAll();
    }


    @PutMapping("/paiements")
    public List<Paiement> updatePaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to update Paiement : {}", paiement);
        if (paiement.getId() == null) {

            return paiementRepository.findAll();
        }
        Paiement result = paiementRepository.save(paiement);
        return paiementRepository.findAll();
    }

    @GetMapping("/paiements/{id}")
    public Optional<Paiement> getPaiement(@PathVariable Long id) {
        Optional<Paiement> paiement = paiementRepository.findById(id);
        return paiement;
    }

    @DeleteMapping("/paiements/{id}")
    public List<Paiement> deletePaiement(@PathVariable Long id) {
        paiementRepository.deleteById(id);
        return paiementRepository.findAll();
    }
}
