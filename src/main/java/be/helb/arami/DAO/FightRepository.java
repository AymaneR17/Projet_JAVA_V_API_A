package be.helb.arami.DAO;

import be.helb.arami.Models.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FightRepository extends JpaRepository<Fight, Long> {

    Fight findById(long id);

}
