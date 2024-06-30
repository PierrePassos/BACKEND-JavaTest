package com.example.demo.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
   @Query(value = "SELECT * FROM usuario " +
                   "WHERE " +
                   "(:search IS NULL OR :search = '' OR " +
                   "LOWER(name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                   "LOWER(email) LIKE LOWER(CONCAT('%', :search, '%')))",
           countQuery = "SELECT COUNT(*) FROM usuario " +
                        "WHERE " +
                        "(:search IS NULL OR :search = '' OR " +
                        "LOWER(name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                        "LOWER(email) LIKE LOWER(CONCAT('%', :search, '%')))",
           nativeQuery = true)
    Page<Usuario> findUsersBySearch(@Param("search") String search, Pageable pageable);
}