package com.example.personapi.spec;

import com.example.personapi.entity.Personne;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PersonneSpecifications {

    private PersonneSpecifications() {
        // utility
    }

    public static Specification<Personne> filtreComplet(LocalDate dateDebut, LocalDate dateFin, String search) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dateDebut != null && dateFin != null) {
                predicates.add(cb.between(root.get("dateNaissance"), dateDebut, dateFin));
            }

            if (search != null && !search.isBlank()) {
                String like = "%" + search.toLowerCase() + "%";
                Predicate nom = cb.like(cb.lower(root.get("nom")), like);
                Predicate prenom = cb.like(cb.lower(root.get("prenom")), like);
                Predicate adresse = cb.like(cb.lower(root.get("adresse")), like);
                predicates.add(cb.or(nom, prenom, adresse));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
