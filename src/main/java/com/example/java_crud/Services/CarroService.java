package com.example.java_crud.Services;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Repositories.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    public void excluir(Long id) {
        carroRepository.deleteById(id);
    }

    public List<Carro> listarDisponiveis() {
        return carroRepository.findByDisponivelTrue();
    }
}
