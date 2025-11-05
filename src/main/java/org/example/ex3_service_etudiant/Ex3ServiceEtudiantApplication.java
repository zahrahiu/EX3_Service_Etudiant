package org.example.ex3_service_etudiant;

import org.example.ex3_service_etudiant.Config.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)

public class Ex3ServiceEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ex3ServiceEtudiantApplication.class, args);
    }

}
