package localelection.programming24hrstartagain.services;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import localelection.programming24hrstartagain.entities.Candidate;
import localelection.programming24hrstartagain.entities.Party;
import localelection.programming24hrstartagain.repositories.CandidateRepository;
import localelection.programming24hrstartagain.repositories.PartyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CandidateService {

    CandidateRepository candidateRepository;
    PartyRepository partyRepository;

    public CandidateService(CandidateRepository candidateRepository, PartyRepository partyRepository) {
        this.candidateRepository = candidateRepository;
        this.partyRepository = partyRepository;
    }

    public Iterable<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate findById(Long candidateId) {
        return candidateRepository.findById(candidateId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Candidate found for ID: " + candidateId));
    }

    public Iterable<Candidate> getCandidateByParty(Long partyId) {
        return candidateRepository.findCandidateByPartyId(partyId);
    }

    public void deleteCandidate(Long candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

}
