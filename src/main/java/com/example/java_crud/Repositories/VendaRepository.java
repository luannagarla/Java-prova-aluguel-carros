package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByClienteId(Long clienteId);
}
