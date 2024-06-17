package com.jonacruz.literalura.repository;

import com.jonacruz.literalura.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<PersonModel,Long> {

    Optional<PersonModel> findByNameContainsIgnoreCase(String name);
}


