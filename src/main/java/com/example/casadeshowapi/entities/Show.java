package com.example.casadeshowapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "shows")
public class Show implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Nome do show é obrigatório.")
    private String shows;

    private double valor;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data é uma informação obrigatória.")
    private Date data;

    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Local é uma informação obrigatória.")
    private String local;

    @NotNull(message = "Quantidade é obrigatório.")
    private int ingRestante;

    private int compra;

    @NotNull(message = "Estilo é obrigatório.")
    private String estilo;

    @ManyToOne
    @JoinColumn(name = "casa_id")
    @JsonIgnore
    private Casa casa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShows() {
        return shows;
    }

    public void setShows(String shows) {
        this.shows = shows;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getIngRestante() {
        return ingRestante;
    }

    public void setIngRestante(int ingRestante) {
        this.ingRestante = ingRestante;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}