package org.sfeir.maxime.mqapp.infrastructure.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.sfeir.maxime.mqapp.application.service.PartnerServiceImpl;
import org.sfeir.maxime.mqapp.domain.model.Partner;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/partners")
public class PartnerController {
    private static final Logger logger = LogManager.getLogger(PartnerController.class);
    
    //generate a crud for partner

    @Autowired
    private PartnerServiceImpl partnerService;

    @GetMapping()
    public List<Partner> getAllPartners() {
        logger.info("Getting all partners");
        List<Partner> partners = partnerService.getAllPartners().join();
        logger.debug("Retrieved {} partners", partners.size());
        return partners;
    }

    @GetMapping("/{id}")
    public Optional<Partner> getPartnerById(String id) {
        logger.info("Getting partner by id: {}", id);
        Optional<Partner> partner = partnerService.getPartnerById(id).join();
        logger.debug("Partner found: {}", partner.isPresent());
        return partner;
    }

    @PostMapping("/create")
    public Partner processPartner(@RequestBody Partner partnerContent) {
        logger.info("Creating new partner: {}", partnerContent);
        Partner partner = partnerService.createPartner(partnerContent).join();
        logger.debug("Partner created successfully with id: {}", partner.getId());
        return partner;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deletePartner(String id) {
        logger.info("Deleting partner with id: {}", id);
        Boolean result = partnerService.deletePartner(id).join();
        logger.debug("Partner deletion result: {}", result);
        return result;
    }
}
