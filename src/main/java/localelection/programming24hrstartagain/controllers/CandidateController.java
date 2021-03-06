package localelection.programming24hrstartagain.controllers;


import localelection.programming24hrstartagain.entities.Candidate;
import localelection.programming24hrstartagain.entities.Party;
import localelection.programming24hrstartagain.services.CandidateService;
import localelection.programming24hrstartagain.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    PartyService partyService;

    // Create candidate

    // WORKING
    @PostMapping("/new")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateService.save(candidate);
    }

    @GetMapping("/{candidateId}")
    public Candidate getCandidateById(@PathVariable Long candidateId){
        return candidateService.findById(candidateId);
    }

    // Add candidate to a given party

    // WORKING
    @PutMapping("/add/{candidateId}/{partyId}")
    public Party addCandidateToParty(@PathVariable Long candidateId, @PathVariable Long partyId) {
        Candidate candidate = candidateService.findById(candidateId);
        Party party = partyService.findById(partyId);

        candidate.setParty(party);
        candidateService.save(candidate);

        return party;
    }

    // Edit candidates for a given party

    // Working, but not as intended. Currently edits candidates, but removes them from their party.
    @PutMapping("/edit/{candidateId}")
    public Candidate editCandidate(@PathVariable Long candidateId, @RequestBody Candidate candidate) {
        candidate.setId(candidateId);
        candidate.setName(candidate.getName());
        candidate.setSurname(candidate.getSurname());
        return candidateService.save(candidate);
    }

    @PutMapping("/edit/{candidateId}/{partyId}")
    public Candidate editCandidateInParty(@PathVariable Long candidateId, @PathVariable Long partyId, @RequestBody Candidate candidate) {
        Party party = partyService.findById(partyId);

        candidate.setId(candidateId);
        candidate.setName(candidate.getName());
        candidate.setSurname(candidate.getSurname());
        candidate.setParty(party);

        return candidateService.save(candidate);
    }

//    // Edit candidates for a given party

//    // Not working as intended. Currently edits candidates, but removes them from their party.
//    @PutMapping("/edit/{candidateId}")
//    public Candidate editCandidate(@PathVariable Long candidateId, @RequestBody Candidate candidate) {
//        candidate = candidateService.findById(candidateId);
//        return candidateService.editCandidate(candidate);
//    }

    // Remove candidate from a party without deleting them

    // Working
    @Transactional
    @PutMapping("/remove/{candidateId}/{partyId}")
    public Party removeCandidateFromParty(@PathVariable Long partyId, @PathVariable Long candidateId) {
        Party party = partyService.findById(partyId);
        Candidate candidate = candidateService.findById(candidateId);

        party.removeCandidate(candidate);
        candidate.setParty(null);
        partyService.save(party);
        candidateService.save(candidate);
        return partyService.findById(partyId);
    }


    // Delete candidates for a given party

    // WORKING AS INTENDED
    // Will delete a candidate if Party == null. If it != null, it will remove candidate from party
    // and then it will delete the candidate. Prevents deletion of party when deleting candidate
    // which was a problem I was facing.
    @Transactional
    @DeleteMapping("/delete/{candidateId}")
    public void deleteCandidate(@PathVariable Long candidateId) {
        if (candidateService.findById(candidateId).getParty()!= null){
            removeCandidateFromParty(candidateService.findById(candidateId).getParty().getId(), candidateId);
        }
        candidateService.deleteCandidate(candidateId);
    }

    // Get all Candidates

    // Working
    @GetMapping("/all")
    public Iterable<Candidate> getAllCandidates(){
        return candidateService.getAll();
    }

    // Get all candidates for a given party

    // Working
    @GetMapping("/parties/{partyId}")
    public Iterable<Candidate> getCandidatesForParty(@PathVariable Long partyId){
        return candidateService.getCandidateByParty(partyId);
    }

}
