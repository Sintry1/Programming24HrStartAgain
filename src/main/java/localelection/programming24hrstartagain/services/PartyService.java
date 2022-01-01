package localelection.programming24hrstartagain.services;

import localelection.programming24hrstartagain.entities.Party;
import localelection.programming24hrstartagain.repositories.CandidateRepository;
import localelection.programming24hrstartagain.repositories.PartyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PartyService {

    PartyRepository partyRepository;
    CandidateRepository candidateRepository;

    public PartyService(PartyRepository partyRepository, CandidateRepository candidateRepository) {
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
    }

    public Iterable<Party> getAll() {
        return partyRepository.findAll();
    }

    public Party findById(Long partyId) {
        return partyRepository.findById(partyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Candidate found for ID: " + partyId));
    }

    public Party save(Party party) {
        return partyRepository.save(party);
    }

}
