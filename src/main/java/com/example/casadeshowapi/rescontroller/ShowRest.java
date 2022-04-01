package com.example.casadeshowapi.rescontroller;

import java.util.List;

import com.example.casadeshowapi.Dto.EventoDto;
import com.example.casadeshowapi.entities.Show;
import com.example.casadeshowapi.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/showcontroller")
public class ShowRest {

    @Autowired
    ShowRepository service;

    @ApiOperation(value = "Retorna uma lista de eventos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de eventos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<EventoDto>> getShows() {
        List<EventoDto> dto = EventoDto.converter(service.findAll());
        return ResponseEntity.ok(dto);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Retorna um evento por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um evento por id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
    public ResponseEntity buscarEventoPorID(@PathVariable Long id){
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Salva um novo evento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um novo evento"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    @ResponseStatus(value=HttpStatus.OK, reason="Sucesso!")
    public Show criarNovoEvento(@RequestBody Show show){
        return service.save(show);
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Atualiza um evento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza um evento"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping(value="/{id}")
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
    public ResponseEntity atualizarEvento(@PathVariable("id") Long id,
                                          @RequestBody Show show) {
        return service.findById(id)
                .map(record -> {
                    record.setShows(show.getShows());
                    record.setIngRestante(show.getIngRestante());
                    record.setCasa(show.getCasa());
                    record.setData(show.getData());
                    record.setEstilo(show.getEstilo());
                    record.setValor(show.getValor());
                    Show updated = service.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Apaga um evento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Apaga um evento"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
    public ResponseEntity<?> apagarEvento(@PathVariable Long id) {
        return service.findById(id)
                .map(record -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em decrescente pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/desc/nome")
    public ResponseEntity<List<EventoDto>> getCasasDesc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByNomeDesc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByNomeDesc() {
        return new Sort(Sort.Direction.DESC, "nome");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em crescente pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/asc/nome")
    public ResponseEntity<List<EventoDto>> getEventoAsc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByNomeAsc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByNomeAsc() {
        return new Sort(Sort.Direction.ASC, "nome");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em decrescente pelo valor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/desc/valor")
    public ResponseEntity<List<EventoDto>> getValorDesc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByValorDesc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByValorDesc() {
        return new Sort(Sort.Direction.DESC, "valor");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em crescente pelo valor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/asc/valor")
    public ResponseEntity<List<EventoDto>> getEventoValorAsc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByValorAsc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByValorAsc() {
        return new Sort(Sort.Direction.ASC, "valor");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em decrescente pelo valor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/desc/local")
    public ResponseEntity<List<EventoDto>> getLocalDesc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByLocalDesc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByLocalDesc() {
        return new Sort(Sort.Direction.DESC, "local");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em crescente pelo valor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/asc/local")
    public ResponseEntity<List<EventoDto>> getEventoLocalAsc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByLocalAsc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByLocalAsc() {
        return new Sort(Sort.Direction.ASC, "local");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em decrescente pela data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/desc/data")
    public ResponseEntity<List<EventoDto>> getDataDesc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByDataDesc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByDataDesc() {
        return new Sort(Sort.Direction.DESC, "data");
    }

    @ApiOperation(value = "Retorna uma lista de eventos ordenadas em crescente pela data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de casas de show ordenadas em decrescente"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @GetMapping("/asc/data")
    public ResponseEntity<List<EventoDto>> getEventoDataAsc() {
        List<EventoDto> dto = EventoDto.converter(service.findAll(sortByDataAsc()));
        return ResponseEntity.ok(dto);
    }

    private Sort sortByDataAsc() {
        return new Sort(Sort.Direction.ASC, "data");
    }

}

