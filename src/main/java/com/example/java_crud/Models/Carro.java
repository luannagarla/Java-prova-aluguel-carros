package com.example.java_crud.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro extends Veiculo {

    private String categoria;

    private Integer quilometragem;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }
}