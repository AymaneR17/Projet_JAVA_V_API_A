package be.helb.arami.Services;


import be.helb.arami.DAO.FightFighterRepository;
import be.helb.arami.Models.Fight;
import be.helb.arami.Models.Fighter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightFighterService {

    FightFighterRepository fightFighterRepository;

    public FightFighterService(FightFighterRepository fightFighterRepository) {
        this.fightFighterRepository = fightFighterRepository;
    }

    public List<Fight> getFightsByFighter(Fighter fighter) {
        return fightFighterRepository.findByFighter(fighter);
    }
}
