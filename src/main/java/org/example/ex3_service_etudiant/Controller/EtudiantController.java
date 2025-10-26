package org.example.ex3_service_etudiant.Controller;

import org.example.ex3_service_etudiant.DTO.RequestEtudiantDto;
import org.example.ex3_service_etudiant.DTO.ResponseEtudiantDto;
import org.example.ex3_service_etudiant.Service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Etudiant")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public ResponseEntity<ResponseEtudiantDto> addEtudiant(@RequestBody RequestEtudiantDto dto) {
        return ResponseEntity.ok(etudiantService.addEtudiant(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseEtudiantDto>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> getEtudiantById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> updateEtudiant(@PathVariable Long id, @RequestBody RequestEtudiantDto dto) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}