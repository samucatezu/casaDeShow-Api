package com.example.casadeshowapi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity (name = "casa")
public class Casa implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCasa;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String endereco;

    @OneToMany(mappedBy="casa", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Show> show;

    public Long getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Long idCasa) {
        this.idCasa = idCasa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Show> getShow() {
        return show;
    }

    public void setShow(List<Show> show) {
        this.show = show;
    }


}