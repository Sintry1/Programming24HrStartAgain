package localelection.programming24hrstartagain.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    private String name;
    private int votes;

    @OneToMany(mappedBy = "party")
    private Set<Candidate> candidates = new HashSet<>();

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void removeCandidate(Candidate candidate) {
        candidates.remove(candidate);
    }

    public Party(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }
}
