package com.example.personapi.service;

import com.example.personapi.dto.PersonneDto;
import com.example.personapi.entity.Personne;
import com.example.personapi.repository.PersonneRepository;
import com.example.personapi.spec.PersonneSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonneService {

    private final PersonneRepository repository;

    public PersonneService(PersonneRepository repository) {
        this.repository = repository;
    }

    public Page<PersonneDto> rechercher(int page,
                                        int size,
                                        String sortField,
                                        String sortDir,
                                        LocalDate dateDebut,
                                        LocalDate dateFin,
                                        String search) {

        Sort sort = Sort.by(sortField == null || sortField.isBlank() ? "id" : sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size <= 0 ? 10 : size, sort);

        Page<Personne> personnes = repository.findAll(
                PersonneSpecifications.filtreComplet(dateDebut, dateFin, search),
                pageable
        );

        List<PersonneDto> dtoList = personnes.getContent()
                .stream()
                .map(PersonneDto::fromEntity)
                .toList();

        return new PageImpl<>(dtoList, pageable, personnes.getTotalElements());
    }
}
