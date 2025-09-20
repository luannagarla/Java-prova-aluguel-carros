package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByMatriculaAndSenha(String matricula, String senha);

    List<Funcionario> findByExcluidoFalse();
}
