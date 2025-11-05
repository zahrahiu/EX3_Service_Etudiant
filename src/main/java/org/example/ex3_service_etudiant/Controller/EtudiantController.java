package org.example.ex3_service_etudiant.Controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.ex3_service_etudiant.DTO.RequestEtudiantDto;
import org.example.ex3_service_etudiant.DTO.ResponseEtudiantDto;
import org.example.ex3_service_etudiant.Service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Service des Étudiants",
                description = "Ce service permet de gérer les étudiants et leur filière associée.",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8084")
)
@RestController
@RequestMapping("/v1/Etudiant")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @Operation(
            summary = "Ajouter un étudiant",
            description = "Ajoute un nouvel étudiant en précisant sa filière (via l'id de la filière).",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")

    @PostMapping
    public ResponseEntity<ResponseEtudiantDto> addEtudiant(@RequestBody RequestEtudiantDto dto) {
        return ResponseEntity.ok(etudiantService.addEtudiant(dto));
    }

    @Operation(
            summary = "Afficher la liste des étudiants",
            description = "Retourne la liste de tous les étudiants avec leurs filières associées.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEtudiantDto.class))
                            )),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )

    @PreAuthorize("hasAuthority('ADMIN')")

    @GetMapping
    public ResponseEntity<List<ResponseEtudiantDto>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @Operation(
            summary = "Afficher un étudiant par ID",
            parameters = @Parameter(name = "id", description = "ID de l'étudiant à récupérer", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant trouvé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class))),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
            }
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> getEtudiantById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @Operation(
            summary = "Modifier un étudiant",
            parameters = @Parameter(name = "id", description = "ID de l'étudiant à modifier", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant modifié avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDto.class))),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
            }
    )

    @PreAuthorize("hasAuthority('ADMIN')")

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDto> updateEtudiant(@PathVariable Long id, @RequestBody RequestEtudiantDto dto) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, dto));
    }

    @Operation(
            summary = "Supprimer un étudiant",
            parameters = @Parameter(name = "id", description = "ID de l'étudiant à supprimer", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Étudiant supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}