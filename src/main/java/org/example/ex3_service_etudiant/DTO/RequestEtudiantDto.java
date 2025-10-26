package org.example.ex3_service_etudiant.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEtudiantDto {
    private String nom;
    private String prenom;
    private String cne;
    private Long filiereId;
}

