package be.helb.arami.Controllers;


import be.helb.arami.Models.Fight;
import be.helb.arami.Services.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FightController {

    private final FightService fightService;

    @Autowired
    public FightController(FightService fightService) {
        this.fightService = fightService;
    }

    @PostMapping(value = "/fight/new",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fight> createFight(@RequestBody Fight fight) {
        Fight createdFight = fightService.createFight(fight);
        return new ResponseEntity<>(createdFight, HttpStatus.CREATED);
    }

    @DeleteMapping("/fight/delete/{fightId}")
    public ResponseEntity<String> deleteFight(@PathVariable Long fightId) {
        try {
            fightService.deleteFight(fightId);
            return ResponseEntity.ok("Fight deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting fight");
        }
    }

    @PutMapping("/fight/update/{fightId}")
    public ResponseEntity<Fight> updateFight(@PathVariable Long fightId, @RequestBody Fight updatedFight) {
        Fight result = fightService.updateFight(fightId, updatedFight);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
