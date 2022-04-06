package com.example.casadeshowapi.Dto;

import com.example.casadeshowapi.entities.Vendas;

import java.util.List;
import java.util.stream.Collectors;

public class VendaDto {

    private Long id;
    private Long userId;
    private Long eventoId;

    public VendaDto(Vendas venda) {
        super();
        this.id = venda.getId();
        this.userId = venda.getUserId();
        this.eventoId = venda.getEventoId();
    }

    public static List<VendaDto> converter(List<Vendas> venda) {
        return venda.stream().map(VendaDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

}
