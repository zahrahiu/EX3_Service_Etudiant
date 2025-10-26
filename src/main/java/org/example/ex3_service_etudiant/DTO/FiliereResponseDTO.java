package org.example.ex3_service_etudiant.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereResponseDTO {
    private Long idFiliere;
    private String code;
    private String libelle;
}