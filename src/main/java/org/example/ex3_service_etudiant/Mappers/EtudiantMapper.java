package org.example.ex3_service_etudiant.Mappers;

import org.example.ex3_service_etudiant.DTO.RequestEtudiantDto;
import org.example.ex3_service_etudiant.DTO.ResponseEtudiantDto;
import org.example.ex3_service_etudiant.Entity.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public Etudiant DTO_TO_ENTITY(RequestEtudiantDto dto){
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(dto, etudiant);
        return etudiant;
    }

    public ResponseEtudiantDto ENTITY_TO_DTO(Etudiant etudiant){
        ResponseEtudiantDto dto = new ResponseEtudiantDto();
        BeanUtils.copyProperties(etudiant, dto);
        dto.setIdEtudiant(etudiant.getId());
        return dto;
    }
}