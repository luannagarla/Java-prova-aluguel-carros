package com.example.java_crud.Controllers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Services.CarroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroService.listarTodos());
        return "carro/index"; // vai alimentar a grid
    }

    @GetMapping("/novo")
    public String novoCarro(Model model) {
        model.addAttribute("carro", new Carro());
        return "carro/form";
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarCarro(@ModelAttribute Carro Carro) {
        try {
            carroService.salvar(Carro);
            return ResponseEntity.ok("Carro salvo com sucesso"); // 200
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "Placa já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem); // 400
        }
    }

    @GetMapping("/editar/form/{id}")
    public String openEditarCarro(@PathVariable Long id, Model model) {
        var Carro = carroService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Carro inválido: " + id));
        model.addAttribute("carro", Carro);
        return "carro/form"; // abre o mesmo form, mas preenchido
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editarCarro(@PathVariable Long id, @ModelAttribute Carro Carro, Model model) {
        Carro.setId(id);
        try {
            carroService.salvar(Carro);
            return ResponseEntity.ok("Carro salvo com sucesso");
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "Placa já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem); // 400
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirCarro(@PathVariable Long id) {
        carroService.excluir(id);
        return "redirect:/carros";
    }
}