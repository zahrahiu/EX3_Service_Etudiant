package org.example.ex3_service_etudiant.Service;

import org.example.ex3_service_etudiant.DTO.RequestEtudiantDto;
import org.example.ex3_service_etudiant.DTO.ResponseEtudiantDto;

import java.util.List;

public interface EtudiantService {
    ResponseEtudiantDto addEtudiant(RequestEtudiantDto dto);
    List<ResponseEtudiantDto> getAllEtudiants();
    ResponseEtudiantDto getEtudiantById(Long id);
    ResponseEtudiantDto updateEtudiant(Long id, RequestEtudiantDto dto);
    void deleteEtudiant(Long id);
}
