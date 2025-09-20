package com.example.java_crud.Services;

import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> listarTodosAtivos() {
        return funcionarioRepository.findByExcluidoFalse();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public void excluir(Long id) {
        funcionarioRepository.findById(id).ifPresent(func -> {
            func.setExcluido(true);
            funcionarioRepository.save(func);
        });
    }

    public Optional<Funcionario> login(String matricula, String senha) {
        return funcionarioRepository.findByMatriculaAndSenha(matricula, senha);
    }
}
