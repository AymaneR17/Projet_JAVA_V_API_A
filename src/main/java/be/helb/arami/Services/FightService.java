package be.helb.arami.Services;


import be.helb.arami.DAO.FightRepository;
import be.helb.arami.Models.Fight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FightService {

    public final FightRepository fightRepository;

    @Autowired
    public FightService(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    public Fight createFight(Fight fight) {

        return fightRepository.save(fight);
    }

    public void deleteFight(Long fightId) {
        fightRepository.deleteById(fightId);
    }

    public Fight updateFight(Long fightId, Fight updatedFight) {
        Optional<Fight> existingFightOptional = fightRepository.findById(fightId);
        if (existingFightOptional.isPresent()) {
            Fight existingFight = existingFightOptional.get();

            existingFight.setPlace(updatedFight.getPlace());
            existingFight.setFighter1(updatedFight.getFighter1());
            existingFight.setFighter2(updatedFight.getFighter2());
            existingFight.setResult(updatedFight.getResult());

            return fightRepository.save(existingFight);
        }
        return null;
    }


}
