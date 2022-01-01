package localelection.programming24hrstartagain.repositories;

import localelection.programming24hrstartagain.entities.Candidate;
import localelection.programming24hrstartagain.entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findCandidateByPartyId(Long partyId);
    Candidate findCandidateById(Long candidateId);
}
