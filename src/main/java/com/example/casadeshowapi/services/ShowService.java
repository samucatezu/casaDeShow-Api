package com.example.casadeshowapi.services;

import java.util.List;
import java.util.Optional;

import com.example.casadeshowapi.entities.Show;
import com.example.casadeshowapi.exception.RecordNotFoundException;
import com.example.casadeshowapi.repository.ShowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ShowService {

    @Autowired
    ShowRepository repository;

    //Listar todos os shows
    public List<Show> findAll() {
        return repository.findAll();
    }

    //fim metodo

    //Buscar por id
    public Show acharPorId(Long id) throws RecordNotFoundException {
        Optional<Show> show = repository.findById(id);

        if(show.isPresent()) {
            return show.get();
        } else {
            throw new RecordNotFoundException("Não existe este show");
        }
    }

    // fim metodo

    //criar / atualizar os shows
    public Show criarAtualizarShow(Show entity) {
        if(entity.getId()  == null) {
            entity = repository.save(entity);

            return entity;
        }
        else {
            Optional<Show> show = repository.findById(entity.getId());

            if(show.isPresent()) {
                Show newEntity = show.get();
                newEntity.setShows(entity.getShows());
                newEntity.setLocal(entity.getLocal());
                newEntity.setData(entity.getData());
                newEntity.setValor(entity.getValor());
                newEntity.setCasa(entity.getCasa());
                newEntity.setIngRestante(entity.getIngRestante());
                newEntity.setCasa(entity.getCasa());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }
    // fim metodo

    //deletar
    public void apagarShow(Long id) throws RecordNotFoundException {
        Optional<Show> show = repository.findById(id);

        if(show.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Show não encontrado!");
        }
    }
    //fim do metodo


}