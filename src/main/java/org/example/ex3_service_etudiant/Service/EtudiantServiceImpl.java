package org.example.ex3_service_etudiant.Service;

import org.example.ex3_service_etudiant.DTO.FiliereResponseDTO;
import org.example.ex3_service_etudiant.DTO.RequestEtudiantDto;
import org.example.ex3_service_etudiant.DTO.ResponseEtudiantDto;
import org.example.ex3_service_etudiant.Entity.Etudiant;
import org.example.ex3_service_etudiant.Mappers.EtudiantMapper;
import org.example.ex3_service_etudiant.Repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;
    private final RestTemplate restTemplate;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper, RestTemplate restTemplate) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEtudiantDto addEtudiant(RequestEtudiantDto dto) {
        Etudiant etudiant = etudiantMapper.DTO_TO_ENTITY(dto);
        Etudiant saved = etudiantRepository.save(etudiant);

        ResponseEtudiantDto response = etudiantMapper.ENTITY_TO_DTO(saved);
        FiliereResponseDTO filiere = getFiliereById(saved.getFiliereId());
        response.setFiliere(filiere);

        return response;
    }

    @Override
    public List<ResponseEtudiantDto> getAllEtudiants() {
        return etudiantRepository.findAll().stream().map(e -> {
            ResponseEtudiantDto dto = etudiantMapper.ENTITY_TO_DTO(e);
            dto.setFiliere(getFiliereById(e.getFiliereId()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseEtudiantDto getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();
        ResponseEtudiantDto dto = etudiantMapper.ENTITY_TO_DTO(etudiant);
        dto.setFiliere(getFiliereById(etudiant.getFiliereId()));
        return dto;
    }

    @Override
    public ResponseEtudiantDto updateEtudiant(Long id, RequestEtudiantDto dto) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();

        if (dto.getNom() != null) etudiant.setNom(dto.getNom());
        if (dto.getPrenom() != null) etudiant.setPrenom(dto.getPrenom());
        if (dto.getCne() != null) etudiant.setCne(dto.getCne());
        if (dto.getFiliereId() != null) etudiant.setFiliereId(dto.getFiliereId());

        Etudiant updated = etudiantRepository.save(etudiant);
        ResponseEtudiantDto response = etudiantMapper.ENTITY_TO_DTO(updated);
        response.setFiliere(getFiliereById(updated.getFiliereId()));

        return response;
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    private FiliereResponseDTO getFiliereById(Long filiereId) {
        String url = "http://localhost:8083/v1/Filiere/" + filiereId;
        return restTemplate.getForObject(url, FiliereResponseDTO.class);
    }
}
