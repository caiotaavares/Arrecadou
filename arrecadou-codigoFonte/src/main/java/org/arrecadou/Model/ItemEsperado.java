package org.arrecadou.Model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemEsperado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidadeEmKg;
    private double valorKg;

    @OneToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    @JoinColumn(name = "itemFaltante_id", foreignKey = @ForeignKey(name = "FK_esperado_faltante"))
    private ItemFaltante itemFaltante;

    public ItemEsperado(String nome, int quantidadeEmKg, double valorKg) {
        this.nome = nome;
        this.quantidadeEmKg = quantidadeEmKg;
        this.valorKg = valorKg;
        this.setItemFaltante(new ItemFaltante(nome, quantidadeEmKg));
    }

    public ItemEsperado() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ItemFaltante getItemFaltante() {
        return itemFaltante;
    }

    public void setItemFaltante(ItemFaltante itemFaltante) {
        this.itemFaltante = itemFaltante;
    }
}
