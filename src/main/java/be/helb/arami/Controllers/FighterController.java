package be.helb.arami.Controllers;


import be.helb.arami.Models.Fighter;
import be.helb.arami.Services.FighterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

public class FighterController {

    FighterService fighterService;


    public FighterController (FighterService fighterService){
        this.fighterService = fighterService;
    }



    //New fighter
    @PostMapping(value = "/fighter/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fighter> addNewFighter(@RequestBody Fighter fighter) {
        if (fighter != null) {
            this.fighterService.addOrUpdateFighter(fighter);
            return new ResponseEntity<>(fighter, HttpStatus.CREATED);
        }
        return null;
    }

    //Get fighter by weight category
    @GetMapping("/fighter/category/{weightCategoryName}")
    public ResponseEntity<List<Fighter>> getFightersByWeightCategory(@PathVariable String weightCategoryName) {
        List<Fighter> fighters = fighterService.findByWeightCategory(weightCategoryName);
        return ResponseEntity.ok(fighters);
    }

    //Get fighter by id
    @GetMapping("/fighter/{fighterId}")
    public ResponseEntity<Fighter> findFighterById(@PathVariable Long fighterId) {
        Optional<Fighter> fighterOptional = fighterService.findFighterById(fighterId);

        if (fighterOptional.isPresent()) {
            return ResponseEntity.ok(fighterOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //get all fighters
    @GetMapping("/fighter/all")
    public List<Fighter> findAll() {
        return this.fighterService.findAll();
    }


    @DeleteMapping("/fighter/delete/{fighterId}")
    public ResponseEntity<String> deleteFighter(@PathVariable Long fighterId) {
        fighterService.deleteFighter(fighterId);
        return ResponseEntity.ok("Fighter deleted successfully");
    }


    @PutMapping("fighter/update/{fighterId}")
    public ResponseEntity<String> updateFighter(@PathVariable Long fighterId, @RequestBody Fighter updatedFighter) {
        fighterService.updateFighter(fighterId, updatedFighter);
        return ResponseEntity.ok("Fighter updated successfully");
    }


    @GetMapping("/fighters/oldest")
    public ResponseEntity<List<Fighter>> getOldestFighters() {
        List<Fighter> oldestFighters = fighterService.getOldestFighters();

        if (!oldestFighters.isEmpty()) {
            return ResponseEntity.ok(oldestFighters);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fighters/youngest")
    public ResponseEntity<List<Fighter>> getYoungestFighters() {
        List<Fighter> youngestFighters = fighterService.getYoungestFighters();

        if (!youngestFighters.isEmpty()) {
            return ResponseEntity.ok(youngestFighters);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/fighters/tallest")
    public ResponseEntity<List<Fighter>> getTallestFighters() {
        List<Fighter> tallestFighters = fighterService.getTallestFighters();

        if (!tallestFighters.isEmpty()) {
            return ResponseEntity.ok(tallestFighters);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fighters/shortest")
    public ResponseEntity<List<Fighter>> getShortestFighters() {
        List<Fighter> shortestFighters = fighterService.getShortestFighters();

        if (!shortestFighters.isEmpty()) {
            return ResponseEntity.ok(shortestFighters);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
