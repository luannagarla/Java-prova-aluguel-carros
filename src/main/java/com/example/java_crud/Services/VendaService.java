package com.example.java_crud.Services;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Models.Venda;
import com.example.java_crud.Repositories.CarroRepository;
import com.example.java_crud.Repositories.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final CarroRepository carroRepository;

    public VendaService(VendaRepository vendaRepository, CarroRepository carroRepository) {
        this.vendaRepository = vendaRepository;
        this.carroRepository = carroRepository;
    }

    public Venda salvar(Venda venda) {
        Carro carro = venda.getCarro();

        if (!carro.isDisponivel()) {
            throw new RuntimeException("Carro já alugado!");
        }

        carro.setDisponivel(false);
        carroRepository.save(carro);

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public void excluir(Long id) {
        vendaRepository.deleteById(id);
    }
}
