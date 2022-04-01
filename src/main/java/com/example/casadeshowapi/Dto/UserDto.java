package com.example.casadeshowapi.Dto;

import com.example.casadeshowapi.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private int id;
    private String nome;
    private String email;

    public UserDto(User user) {
        super();
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public static List<UserDto> converter(List<User> usuario) {

        return usuario.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
