package be.helb.arami.Services;


import be.helb.arami.Client.DataAccessTracingClient;
import be.helb.arami.DAO.FighterRepository;
import be.helb.arami.Models.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FighterService {

    FighterRepository fighterRepository;
     DataAccessTracingClient dataAccessTracingClient;

     @Autowired
     public FighterService(FighterRepository fighterRepository, DataAccessTracingClient dataAccessTracingClient){
               this.fighterRepository = fighterRepository;
               this.dataAccessTracingClient = dataAccessTracingClient;
     }


    public Fighter find(String firstName) {
        return this.fighterRepository.find(firstName);
    }
    public Fighter addOrUpdateFighter(Fighter fighter) {
        if (fighter.getId() == null) {
            // Si l'ID du combattant est null, cela signifie qu'il n'existe pas encore, alors ca devient un nouveau combattant
            return fighterRepository.save(fighter);
        } else {
            // Si le fighter existe alors je set ces informations
            Optional<Fighter> existingFighterOptional = fighterRepository.findById(fighter.getId());

            if (existingFighterOptional.isPresent()) {
                Fighter existingFighter = existingFighterOptional.get();
                existingFighter.setFirstName(fighter.getFirstName());
                existingFighter.setLastName(fighter.getLastName());
                existingFighter.setAge(fighter.getAge());
                existingFighter.setSize(fighter.getSize());

                return fighterRepository.save(existingFighter);
            } else {
                return fighterRepository.save(fighter);
            }
        }
    }
    public Optional<Fighter> findFighterById(Long id) {
        if (id != null) {
            return fighterRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }

    public List<Fighter> findByWeightCategory(String weightCategoryName) {
        return fighterRepository.findByWeightCategoryName(weightCategoryName);
    }

    public List<Fighter> findAll() {
        return this.fighterRepository.findAll();

    }

    public List<Fighter> getOldestFighters() {
        return fighterRepository.findAllByOrderByAgeDesc();
    }

    public List<Fighter> getYoungestFighters() {
        return fighterRepository.findAllByOrderByAgeAsc();
    }

    public List<Fighter> getTallestFighters() {
        return fighterRepository.findAllByOrderBySizeDesc();
    }
    public List<Fighter> getShortestFighters() {
        return fighterRepository.findAllByOrderBySizeAsc();
    }

    public void deleteFighter(Long fighterId) {
        fighterRepository.deleteById(fighterId);
    }

    public void updateFighter(Long fighterId, Fighter updatedFighter) {
        if (fighterRepository.existsById(fighterId)) {
            updatedFighter.setId(fighterId);
            fighterRepository.save(updatedFighter);
        }
    }


}
