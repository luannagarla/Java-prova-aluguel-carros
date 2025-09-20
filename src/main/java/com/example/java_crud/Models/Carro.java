package com.example.java_crud.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro extends Veiculo {

    private String categoria;

    private Integer quilometragem;

    @Column(nullable = false)
    private Boolean excluido = false;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }

    public Boolean getExcluido() { return excluido; }
    public void setExcluido(Boolean excluido) { this.excluido = excluido; }
}