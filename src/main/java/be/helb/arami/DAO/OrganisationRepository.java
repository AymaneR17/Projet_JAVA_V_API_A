package be.helb.arami.DAO;

import be.helb.arami.Models.Fight;
import be.helb.arami.Models.Organisation;
import be.helb.arami.Models.WeightCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findById(Long id);

    @Override
    @Transactional
    Organisation save(Organisation organisation);
}
