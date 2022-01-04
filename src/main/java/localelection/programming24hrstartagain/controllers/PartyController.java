package localelection.programming24hrstartagain.controllers;


import localelection.programming24hrstartagain.entities.Party;
import localelection.programming24hrstartagain.services.CandidateService;
import localelection.programming24hrstartagain.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/parties")
public class PartyController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    PartyService partyService;

    // Working
    @PostMapping("/new")
    public Party addParty(@RequestBody Party party) {
        return partyService.save(party);
    }


    // Working
    @GetMapping("/all")
    public Iterable<Party> getAllParties(){
        return partyService.getAll();
    }

    // Working
    @GetMapping("/{partyId}")
    public Party getPartyById(@PathVariable Long partyId) {
        return partyService.findById(partyId);
    }


}
