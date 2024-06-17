package com.jonacruz.literalura.repository;

import com.jonacruz.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,Long> {
    
}
