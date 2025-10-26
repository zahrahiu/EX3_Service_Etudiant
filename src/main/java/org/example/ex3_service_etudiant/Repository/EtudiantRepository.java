package org.example.ex3_service_etudiant.Repository;

import org.example.ex3_service_etudiant.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
