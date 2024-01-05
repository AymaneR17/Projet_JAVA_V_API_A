package be.helb.arami.Models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String place;

    @ManyToOne
    @JoinColumn(name = "fighter1_id", referencedColumnName = "id")
    Fighter fighter1;

    @ManyToOne
    @JoinColumn(name = "fighter2_id", referencedColumnName = "id")
    Fighter fighter2;

    String result;


    public Fight(Fighter fighter1, Fighter fighter2, String place, String result) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.place = place;
        this.result = result;
    }

}
