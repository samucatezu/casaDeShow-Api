package com.example.casadeshowapi.rescontroller;

import com.example.casadeshowapi.Dto.VendaDto;
import com.example.casadeshowapi.repository.VendaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendacontroller")
@CrossOrigin
public class VendaRest {

    @Autowired
    VendaRepository repositorio;

    @ApiOperation(value = "Retorna uma lista de vendas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de vendas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<VendaDto>> getVendas() {
        List<VendaDto> dto = VendaDto.converter(repositorio.findAll());
        return ResponseEntity.ok(dto);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Retorna uma venda por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma venda por id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id não encontrado")
    public ResponseEntity buscarVendaPorID(@PathVariable Long id) {
        return repositorio.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

}
