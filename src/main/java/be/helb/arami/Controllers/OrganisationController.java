package be.helb.arami.Controllers;


import be.helb.arami.DTO.OrganisationDTO;
import be.helb.arami.Models.Fighter;
import be.helb.arami.Models.Organisation;
import be.helb.arami.Services.FighterService;
import be.helb.arami.Services.OrganisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrganisationController {


    OrganisationService organisationService;
    FighterService fighterService;

    public OrganisationController(OrganisationService organisationService, FighterService fighterService){
        this.organisationService = organisationService;
        this.fighterService = fighterService;
    }


    @PostMapping(value = "/organisation/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organisation> addNewOrganisation(@RequestBody Organisation organisation ) {
        if (organisation != null) {
            this.organisationService.addNewOrganisation(organisation);
            return new ResponseEntity<>(organisation, HttpStatus.CREATED);
        }
        return null;
    }

    @PostMapping(value = "/organisation/{organisationId}/fighter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrUpdateFighterToOrganisation(@PathVariable Long organisationId, @RequestBody Fighter fighter) {
        Optional<Organisation> optionalOrganisation = organisationService.findOrganisationById(organisationId);

        if (optionalOrganisation.isPresent()) {
            Organisation organisation = optionalOrganisation.get();

            Fighter savedFighter = fighterService.addOrUpdateFighter(fighter);

            savedFighter.setOrganisation(organisation);
            fighterService.addOrUpdateFighter(savedFighter);

            return ResponseEntity.ok("Fighter category updated or new fighter added successfully");
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("organisation/all")
    public ResponseEntity<List<Organisation>> getAllOrganisations() {
        List<Organisation> organisations = organisationService.getAllOrganisations();
        return new ResponseEntity<>(organisations, HttpStatus.OK);
    }

    @DeleteMapping("organisation/delete/{organisationId}")
    public ResponseEntity<String> deleteOrganisation(@PathVariable Long organisationId) {
        try {
            organisationService.deleteOrganisation(organisationId);
            return new ResponseEntity<>("Organisation deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete organisation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("organisation/update/{organisationId}")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable Long organisationId, @RequestBody Organisation updatedOrganisation) {
        Organisation result = organisationService.updateOrganisation(organisationId, updatedOrganisation);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("organisation/most-fighters")
    public ResponseEntity<OrganisationDTO> getOrganisationWithMostFighters() {
        Organisation organisationWithMostFighters = organisationService.getOrganisationWithMostFighters();
        if (organisationWithMostFighters != null) {
            OrganisationDTO organisationDTO = convertToDTO(organisationWithMostFighters);
            return ResponseEntity.ok(organisationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrganisationDTO convertToDTO(Organisation organisation) {
        return new OrganisationDTO(organisation.getId(), organisation.getNameOrganisation());
    }
}
