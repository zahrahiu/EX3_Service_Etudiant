package org.example.ex3_service_etudiant.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEtudiantDto {
    private Long idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private FiliereResponseDTO filiere;
}
