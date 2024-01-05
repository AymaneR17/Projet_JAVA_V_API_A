package be.helb.arami.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganisationDTO {
    private Long id;
    private String name;

    // Ajoutez d'autres propriétés si nécessaire

    public OrganisationDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}