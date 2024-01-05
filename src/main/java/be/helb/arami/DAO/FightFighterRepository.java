package be.helb.arami.DAO;

import be.helb.arami.Models.Fight;
import be.helb.arami.Models.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FightFighterRepository  extends JpaRepository<Fight, Long> {

    @Query("SELECT f FROM Fight f WHERE f.fighter1 = :fighter OR f.fighter2 = :fighter")
    List<Fight> findByFighter(@Param("fighter") Fighter fighter);
}
