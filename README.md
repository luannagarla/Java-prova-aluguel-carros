# Sistema de Aluguel de Carros 🏎️
Projeto desenvolvido como parte da disciplina de Laboratório de Programação do curso de Ciência de Dados e Inteligência Artificial na Universidade Estadual de Londrina (UEL).

Sistema web de gerenciamento de aluguel de carros, desenvolvido em Spring Boot, Thymeleaf, MySQL e Bootstrap. Ele permite gerenciar funcionários, clientes, carros e vendas, utilizando exclusão lógica para preservar registros no banco de dados sem removê-los fisicamente.

<img width="1920" height="959" alt="image" src="https://github.com/user-attachments/assets/ba6041ff-38c6-4471-bc98-fbda08a735a0" />

## 🤖 Tecnologias Utilizadas

Java 17+

Spring Boot 3+

Spring Data JPA

Thymeleaf

MySQL

Bootstrap 5

## ⚙️ Funcionalidades


- Login de funcionários com validação de matrícula e senha.

- Dashboard com cards para acesso rápido a cada módulo.

- Exclusão lógica: registros marcados como excluido = true não aparecem no sistema, mas permanecem no banco.

- Cadastrar, editar e listar funcionários e excluir logicamente funcionários.
- Cadastrar, editar e listar funcionários e excluir logicamente clientes.
- Cadastrar, editar e listar funcionários e excluir logicamente carros.
- Cadastrar, editar e listar funcionários e excluir vendas.

## 📸 Alguns Screenshots

### Telas de Carros
<img width="1920" height="868" alt="image" src="https://github.com/user-attachments/assets/bf9d03e6-55f7-451e-aea4-efe2c3c0a466" />
<img width="1920" height="959" alt="image" src="https://github.com/user-attachments/assets/62d4a4e9-f95f-49f0-a188-c64145bff13f" />

### Tela de Clientes
<img width="1920" height="853" alt="image" src="https://github.com/user-attachments/assets/4f26c4a6-5df8-4b8c-84d0-c2b4e262d869" />
<img width="1920" height="958" alt="image" src="https://github.com/user-attachments/assets/aad91022-938f-46d5-81a1-bdf6f51772e1" />

### Tela de Vendas

<img width="1920" height="914" alt="image" src="https://github.com/user-attachments/assets/b9edb352-1a6b-4d9c-a154-150b27a76f04" />
<img width="1920" height="956" alt="image" src="https://github.com/user-attachments/assets/e40dc644-0815-40d8-b369-b338c783db9a" />


## 📂 Estrutura de Pacotes 

```
com.example.java_crud
├── Controllers
│   ├── ClienteController.java
│   ├── CarroController.java
│   ├── FuncionarioController.java
│   └── VendaController.java
├── Models
│   ├── Cliente.java
│   ├── Carro.java
│   ├── Funcionario.java
│   ├── Pessoa.java
│   └── Venda.java
├── Repositories
│   ├── ClienteRepository.java
│   ├── CarroRepository.java
│   ├── FuncionarioRepository.java
│   └── VendaRepository.java
├── Services
│   ├── ClienteService.java
│   ├── CarroService.java
│   ├── FuncionarioService.java
│   └── VendaService.java
└── resources
    ├── templates
    │   ├── cliente
    │   ├── carro
    │   ├── funcionario
    │   └── venda
    └── static
        ├── css
        └── images
```
