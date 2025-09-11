package com.example.java_crud.Models;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String cep;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}

