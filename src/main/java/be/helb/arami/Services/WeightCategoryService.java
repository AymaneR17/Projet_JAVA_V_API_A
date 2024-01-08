package be.helb.arami.Services;


import be.helb.arami.DAO.WeightCategoryRepository;
import be.helb.arami.Models.Fighter;
import be.helb.arami.Models.Organisation;
import be.helb.arami.Models.WeightCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeightCategoryService {

WeightCategoryRepository weightCategoryRepository;


    @Autowired
    public WeightCategoryService (WeightCategoryRepository weightCategoryRepository){
        this.weightCategoryRepository = weightCategoryRepository;
    }


    public void addNewWeightCategory(WeightCategory weightCategory) {
      this.weightCategoryRepository.save(weightCategory);
    }

    public void deleteWeightCategory(Long weightCategoryId) {
        weightCategoryRepository.deleteById(weightCategoryId);
    }

    public List<WeightCategory> getAllWeightCategories() {
        return weightCategoryRepository.findAll();
    }
    public Optional<WeightCategory> findWeightCategoryById(Long weightCategoryId) {
        return weightCategoryRepository.findById(weightCategoryId);
    }
    public WeightCategory addNewFighterToWeightCategory(Long weightCategoryId, String firstName, String lastName, int age, int size) {
        Optional<WeightCategory> optionalWeightCategory = weightCategoryRepository.findById(weightCategoryId);

        if (optionalWeightCategory.isPresent()) {
            WeightCategory weightCategory = optionalWeightCategory.get();
            List<Fighter> fighters = weightCategory.getFighters();
            fighters.add(new Fighter(firstName, lastName, age,size)); // Ajoute un nouvel étudiant avec le prénom fourni
            weightCategory.setFighters(fighters);
            return weightCategoryRepository.save(weightCategory);
        }

        return null;
    }

    public WeightCategory getWeightCategoryWithMostFighters() {
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();

        if (weightCategories.isEmpty()) {
            return null;
        }
        return weightCategories.stream()
                .max(Comparator.comparingInt(weightCategory -> weightCategory.getFighters().size()))
                .orElse(null);
    }


    public List<WeightCategory> getWeightCategoriesWithLeastFighters() {
        List<WeightCategory> weightCategories = weightCategoryRepository.findAll();

        if (weightCategories.isEmpty()) {
            return Collections.emptyList();
        }

        int minFighterCount = weightCategories.stream()
                .mapToInt(weightCategory -> weightCategory.getFighters().size())
                .min()
                .orElse(0);

        return weightCategories.stream()
                .filter(weightCategory -> weightCategory.getFighters().size() == minFighterCount)
                .collect(Collectors.toList());
    }
}
