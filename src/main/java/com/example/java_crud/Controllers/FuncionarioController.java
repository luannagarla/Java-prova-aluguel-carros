package com.example.java_crud.Controllers;

import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Services.FuncionarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String matricula,
                        @RequestParam String senha,
                        Model model) {
        var funcionario = funcionarioService.login(matricula, senha);
        if (funcionario.isPresent()) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erro", "Matrícula ou senha inválidos!");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("/funcionarios")
    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionario/index"; // vai alimentar a tabela
    }

    @GetMapping("/funcionarios/novo")
    public String novoFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionario/form";
    }

    @PostMapping("/funcionarios/salvar")
    public ResponseEntity<?> salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        try {
            funcionarioService.salvar(funcionario);
            return ResponseEntity.ok("Funcionário salvo com sucesso");
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "CPF, matrícula ou login já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }
    }

    @GetMapping("/funcionarios/editar/form/{id}")
    public String openEditarFuncionario(@PathVariable Long id, Model model) {
        var funcionario = funcionarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        model.addAttribute("funcionario", funcionario);
        return "funcionario/form"; // abre o mesmo form preenchido
    }

    @PutMapping("/funcionarios/editar/{id}")
    public ResponseEntity<?> editarFuncionario(@PathVariable Long id,
                                               @ModelAttribute Funcionario funcionario,
                                               Model model) {
        funcionario.setId(id);
        try {
            funcionarioService.salvar(funcionario);
            return ResponseEntity.ok("Funcionário salvo com sucesso");
        } catch (DataIntegrityViolationException ex) {
            String mensagem = "CPF, matrícula ou login já existe no sistema!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
        }
    }

    @GetMapping("/funcionarios/excluir/{id}")
    public String excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return "redirect:/funcionarios";
    }
}
