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
public class WeightCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nameWeightCategory;
    //fk fighterId
    @OneToMany(mappedBy = "weightCategory")
    private List<Fighter> fighters;

}
