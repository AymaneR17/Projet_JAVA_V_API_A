package be.helb.arami.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FighterRetiredDTO {
    Long id;
    String name;
    String lastName;
    Boolean isRetired;
}