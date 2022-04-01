package com.example.casadeshowapi.Dto;

import com.example.casadeshowapi.entities.Show;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class EventoDto {

    private Long id;
    private String shows;
    private double valor;
    private Calendar data;
    private String local;
    private int ingRestante;
    private int compra;
    private String estilo;
    private String Casa;

    public EventoDto(Show show) {
        super();
        this.id = show.getId();
        this.shows = show.getShows();
        this.valor = show.getValor();
        this.local = show.getLocal();
        this.ingRestante = show.getIngRestante();
        this.compra = show.getCompra();
        this.estilo = show.getEstilo();
        Casa = show.getCasa().getNome();
    }

    public static List<EventoDto> converter(List<Show> show) {

        return show.stream().map(EventoDto::new).collect(Collectors.toList());
    }

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
    public Calendar getData() {
        return data;
    }
    public void setData(Calendar data) {
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
    public String getCasa() {
        return Casa;
    }
    public void setCasa(String casa) {
        Casa = casa;
    }



}
