package com.example.personapi.dto;

import com.example.personapi.entity.Personne;

import java.time.LocalDate;

public class PersonneDto {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private String email;

    public PersonneDto() {
    }

    public PersonneDto(Long id, String nom, String prenom, String adresse, LocalDate dateNaissance, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.email = email;
    }

    public static PersonneDto fromEntity(Personne personne) {
        if (personne == null) {
            return null;
        }
        return new PersonneDto(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getAdresse(),
                personne.getDateNaissance(),
                personne.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
