package com.example.casadeshowapi.rescontroller;

import java.util.List;

import com.example.casadeshowapi.Dto.CasaDto;
import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/casacontroller")
@CrossOrigin
public class CasaRest {

    @Autowired
    CasaRepository service;

    @ApiOperation(value = "Retorna uma lista de casas de show")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de casas de show"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<CasaDto>> getCasas() {
        List<CasaDto> dto = CasaDto.converter(service.findAll());
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Retorna uma lista de casas de show ordenadas em crescente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas de forma crescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/asc")
    public ResponseEntity<List<CasaDto>> getCasasAsc() {
        List<CasaDto> dto = CasaDto.converter(service.findAll(sortByIdCasaAsc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByIdCasaAsc() {
        return new Sort(Sort.Direction.ASC, "nome");
    }

    @ApiOperation(value = "Retorna uma lista de casas de show ordenadas em decrescente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas de forma decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/desc")
    public ResponseEntity<List<CasaDto>> getCasasDesc() {
        List<CasaDto> dto = CasaDto.converter(service.findAll(sortByIdCasaDesc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByIdCasaDesc() {
        return new Sort(Sort.Direction.DESC, "nome");
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Retorna uma casa de show por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma casa de show por id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(path = {"/{id}"})
    public ResponseEntity buscarPorID(@PathVariable Long id){
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Salva uma nova casa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva uma nova casa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public Casa criar(@RequestBody Casa casa){
        return service.save(casa);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Atualiza uma casa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza uma casa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value="/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id,
                                    @RequestBody Casa casa) {
        return service.findById(id)
                .map(record -> {
                    record.setNome(casa.getNome());
                    record.setEndereco(casa.getEndereco());
                    Casa updated = service.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Apaga uma casa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Apaga uma casa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> apagar(@PathVariable Long id) {
        return service.findById(id)
                .map(record -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
