package com.example.java_crud.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Boolean excluido = false;

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Boolean getExcluido() { return excluido; }
    public void setExcluido(Boolean excluido) { this.excluido = excluido; }
}
