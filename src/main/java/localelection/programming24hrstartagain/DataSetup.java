package localelection.programming24hrstartagain;


import localelection.programming24hrstartagain.entities.Party;
import localelection.programming24hrstartagain.repositories.CandidateRepository;
import localelection.programming24hrstartagain.repositories.PartyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSetup implements CommandLineRunner {

    CandidateRepository candidateRepository;
    PartyRepository partyRepository;

    public DataSetup(CandidateRepository candidateRepository, PartyRepository partyRepository) {
        this.candidateRepository = candidateRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Party p1 = partyRepository.save(new Party("Social Democrats", 10418));
        Party p2 = partyRepository.save(new Party("Radical Left", 5686));
        Party p3 = partyRepository.save(new Party("The Conservative People's Party", 24607));
        Party p4 = partyRepository.save(new Party("SF - Social People's Party", 2852));
        Party p5 = partyRepository.save(new Party("Venstre Danmarks Liberale Party", 3603));
        Party p6 = partyRepository.save(new Party("Unity List", 10720));
    }
}
