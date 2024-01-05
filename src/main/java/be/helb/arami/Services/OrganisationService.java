package be.helb.arami.Services;


import be.helb.arami.DAO.OrganisationRepository;
import be.helb.arami.DAO.WeightCategoryRepository;
import be.helb.arami.Models.Fighter;
import be.helb.arami.Models.Organisation;
import be.helb.arami.Models.WeightCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisationService {

    OrganisationRepository organisationRepository;


    public OrganisationService (OrganisationRepository organisationRepository){
        this.organisationRepository = organisationRepository;
    }


    public void addNewOrganisation(Organisation organisation) {
        this.organisationRepository.save(organisation);
    }

    public Optional<Organisation> findOrganisationById(Long organisationId) {
        return organisationRepository.findById(organisationId);
    }

    public void deleteOrganisation(Long organisationId) {
        organisationRepository.deleteById(organisationId);
    }

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }
    public Organisation addNewFighterToOrganisation(Long organisationId, String firstName, String lastName, int age, int size) {
        Optional<Organisation> optionalOrganisation = organisationRepository.findById(organisationId);

        if (optionalOrganisation.isPresent()) {
            Organisation organisation = optionalOrganisation.get();
            List<Fighter> fighters = organisation.getFighters();
            fighters.add(new Fighter(firstName, lastName, age,size));
            organisation.setFighters(fighters);
            return organisationRepository.save(organisation);
        }

        return null;
    }



    public Organisation updateOrganisation(Long organisationId, Organisation updatedOrganisation) {
        Optional<Organisation> existingOrganisationOptional = organisationRepository.findById(organisationId);
        if (existingOrganisationOptional.isPresent()) {
            Organisation existingOrganisation = existingOrganisationOptional.get();

            existingOrganisation.setNameOrganisation(updatedOrganisation.getNameOrganisation());
            return organisationRepository.save(existingOrganisation);
        }
        return null;
    }

    public Organisation getOrganisationWithMostFighters() {
        List<Organisation> organisations = organisationRepository.findAll();

        Organisation organisationWithMostFighters = null;
        int maxFighterCount = 0;

        for (Organisation organisation : organisations) {
            int currentFighterCount = organisation.getFighters().size();
            if (currentFighterCount > maxFighterCount) {
                maxFighterCount = currentFighterCount;
                organisationWithMostFighters = organisation;
            }
        }

        return organisationWithMostFighters;
    }

}
