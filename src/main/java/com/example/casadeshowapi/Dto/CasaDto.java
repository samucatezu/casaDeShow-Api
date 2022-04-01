package com.example.casadeshowapi.Dto;

import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.entities.Show;

import java.util.List;
import java.util.stream.Collectors;

public class CasaDto {

    private Long idCasa;
    private String nome;
    private String endereco;
    private List<Show> show;

    public CasaDto(Casa casa) {
        super();
        this.idCasa = casa.getIdCasa();
        this.nome = casa.getNome();
        this.endereco = casa.getEndereco();
        this.show = casa.getShow();
    }

    public static List<CasaDto> converter(List<Casa> casa) {

        return casa.stream().map(CasaDto::new).collect(Collectors.toList());
    }

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

