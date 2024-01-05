package be.helb.arami.DAO;


import be.helb.arami.Models.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface WeightCategoryRepository extends JpaRepository<WeightCategory, Long> {


    Optional<WeightCategory> findById(Long id);

    @Override
    @Transactional
    WeightCategory save(WeightCategory weightCategory);
}
