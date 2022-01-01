package localelection.programming24hrstartagain.repositories;

import localelection.programming24hrstartagain.entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Party findPartyById(Long partyId);
}
