package com.example.casadeshowapi.rescontroller;

import com.example.casadeshowapi.Dto.UserDto;
import com.example.casadeshowapi.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usercontroller")
@CrossOrigin
public class UserRest {

    @Autowired
    UserRepository repositorio;

    @ApiOperation(value = "Retorna uma lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de eventos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> dto = UserDto.converter(repositorio.findAll());
        return ResponseEntity.ok(dto);
    }

}
