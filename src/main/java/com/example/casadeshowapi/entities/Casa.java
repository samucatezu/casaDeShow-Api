package com.example.casadeshowapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity(name = "casa")
public class Casa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCasa;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "A casa precisa ter um nome!")
    private String nome;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "A casa precisa ter um endereço!")
    private String endereco;

    @OneToMany(mappedBy = "casa", cascade = CascadeType.REMOVE, orphanRemoval = true)
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