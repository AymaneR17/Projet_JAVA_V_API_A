package be.helb.arami.DAO;

import be.helb.arami.Models.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {

    @Query("select s from Fighter s where s.firstName  = :firstName")
    Fighter find(@Param("firstName") String firstName);

    Optional<Fighter> findById(Long id);


    @Query("select f from Fighter f where f.weightCategory.nameWeightCategory = :weightCategoryName")
    List<Fighter> findByWeightCategoryName(@Param("weightCategoryName") String weightCategoryName);


    List<Fighter> findAllByOrderByAgeDesc();
    List<Fighter> findAllByOrderByAgeAsc();
    List<Fighter> findAllByOrderBySizeDesc();
    List<Fighter> findAllByOrderBySizeAsc();




    @Override
    @Transactional
    Fighter save(Fighter fighter);
}
