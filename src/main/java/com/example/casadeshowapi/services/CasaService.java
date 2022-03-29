package com.example.casadeshowapi.services;

import java.util.List;
import java.util.Optional;

import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.exception.RecordNotFoundException;
import com.example.casadeshowapi.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaService {

    @Autowired
    CasaRepository repositorio;

    public List<Casa> findAll() {
        return repositorio.findAll();
    }

    public Casa acharPorId(Long id) throws RecordNotFoundException {
        Optional<Casa> casa = repositorio.findById(id);

        if(casa.isPresent()) {
            return casa.get();
        } else {
            throw new RecordNotFoundException("Não existe esta casa");
        }
    }

    public Casa criarAtualizarCasa(Casa entidade) {
        if(entidade.getIdCasa() == null) {
            entidade = repositorio.save(entidade);

            return entidade;
        }
        else {
            Optional<Casa> casa = repositorio.findById(entidade.getIdCasa());

            if(casa.isPresent()) {
                Casa novaEntidade = casa.get();
                novaEntidade.setNome(entidade.getNome());
                novaEntidade.setEndereco(entidade.getEndereco());

                novaEntidade = repositorio.save(novaEntidade);

                return novaEntidade;
            } else {
                entidade = repositorio.save(entidade);

                return entidade;
            }
        }
    }

    public void apagarCasa(Long id) throws RecordNotFoundException {
        Optional<Casa> casa = repositorio.findById(id);

        if(casa.isPresent())
        {
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("Casa não encontrada!");
        }
    }

}

