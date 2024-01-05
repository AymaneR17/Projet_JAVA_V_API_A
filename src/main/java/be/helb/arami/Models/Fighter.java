package be.helb.arami.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    int age;
    int size;

    public Fighter(String firstName, String lastName ,int age, int size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.size = size;
    }


    @ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @JsonIgnore
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "weightCategory_id" , referencedColumnName = "id")
    @JsonIgnore
    private WeightCategory weightCategory;
}
