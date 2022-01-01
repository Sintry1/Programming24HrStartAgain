package localelection.programming24hrstartagain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    private String name;
    private String surname;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id", referencedColumnName = "party_id")
    private Party party;

    public Candidate(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Candidate(String name, String surname, Party party) {
        this.name = name;
        this.surname = surname;
        this.party = party;
    }
}
