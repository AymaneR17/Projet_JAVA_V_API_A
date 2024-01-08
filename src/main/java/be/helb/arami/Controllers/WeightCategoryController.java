package be.helb.arami.Controllers;


import be.helb.arami.Models.Fighter;
import be.helb.arami.Models.WeightCategory;
import be.helb.arami.Services.FighterService;
import be.helb.arami.Services.WeightCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WeightCategoryController {

    WeightCategoryService weightCategoryService;
    FighterService fighterService;


    @Autowired
    public WeightCategoryController(WeightCategoryService weightCategoryService, FighterService fighterService){
        this.weightCategoryService = weightCategoryService;
        this.fighterService = fighterService;
    }


    @PostMapping(value = "/weightcategory/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeightCategory> addNewWeightCategory(@RequestBody WeightCategory weightCategory ) {
        if (weightCategory != null) {
            this.weightCategoryService.addNewWeightCategory(weightCategory);
            return new ResponseEntity<>(weightCategory, HttpStatus.CREATED);
        }
        return null;
    }

    @PostMapping(value = "/weightCategory/{weightCategoryId}/fighter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrUpdateFighterToWeightCategory(@PathVariable Long weightCategoryId, @RequestBody Fighter fighter) {
        Optional<WeightCategory> weightCategoryOptional = weightCategoryService.findWeightCategoryById(weightCategoryId);

        if (weightCategoryOptional.isPresent()) {
            WeightCategory weightCategory = weightCategoryOptional.get();

            Fighter savedFighter = fighterService.addOrUpdateFighter(fighter);
            // Mettre à jour la catégorie de poids du combattant
            savedFighter.setWeightCategory(weightCategory);
            fighterService.addOrUpdateFighter(savedFighter);

            return ResponseEntity.ok("Fighter category updated or new fighter added successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("weightcategory/delete/{weightCategoryId}")
    public ResponseEntity<String> deleteWeightCategory(@PathVariable Long weightCategoryId) {
        weightCategoryService.deleteWeightCategory(weightCategoryId);
        return new ResponseEntity<>("Organisation deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/weightcategory/all")
    public ResponseEntity<List<WeightCategory>> getAllWeightCategory() {
        List<WeightCategory> weightCategories = weightCategoryService.getAllWeightCategories();
        return new ResponseEntity<>(weightCategories, HttpStatus.OK);
    }


    @GetMapping("/weightcategory/most-fighters")
    public ResponseEntity<WeightCategory> getWeightCategoryWithMostFighters() {
        WeightCategory weightCategory = weightCategoryService.getWeightCategoryWithMostFighters();

        if (weightCategory != null) {
            return ResponseEntity.ok(weightCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/weightcategory/least-fighters")
    public ResponseEntity<List<WeightCategory>> getWeightCategoriesWithLeastFighters() {
        List<WeightCategory> weightCategories = weightCategoryService.getWeightCategoriesWithLeastFighters();

        if (!weightCategories.isEmpty()) {
            return ResponseEntity.ok(weightCategories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
