package be.helb.arami.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nameOrganisation;

    @OneToMany(mappedBy = "organisation")
    private List<Fighter> fighters;
}
