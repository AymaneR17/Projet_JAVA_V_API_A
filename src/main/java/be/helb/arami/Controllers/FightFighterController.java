package be.helb.arami.Controllers;

import be.helb.arami.Models.Fight;
import be.helb.arami.Models.Fighter;
import be.helb.arami.Services.FightFighterService;
import be.helb.arami.Services.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FightFighterController {

    FightFighterService fightFighterService;
    FighterService fighterService;

    @Autowired
    public FightFighterController(FightFighterService fightFighterService, FighterService fighterService) {
        this.fightFighterService = fightFighterService;
        this.fighterService = fighterService;
    }

    //récupere tout les combats qu'un combattant a realisé
    @GetMapping("fight-fighter/{fighterId}")
    public ResponseEntity<List<Fight>> getFightsByFighter(@PathVariable Long fighterId) {

        Fighter fighter = fighterService.findFighterById(fighterId).orElse(null);

        if (fighter != null) {
            List<Fight> fights = fightFighterService.getFightsByFighter(fighter);
            return ResponseEntity.ok(fights);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
