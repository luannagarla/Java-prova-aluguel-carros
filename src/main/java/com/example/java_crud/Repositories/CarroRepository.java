package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByDisponivelTrue();
}
