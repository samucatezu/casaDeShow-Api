package com.example.casadeshowapi.services;

import java.util.List;
import java.util.Optional;

import com.example.casadeshowapi.entities.Ingressos;
import com.example.casadeshowapi.exception.RecordNotFoundException;
import com.example.casadeshowapi.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngressoService {

    @Autowired
    IngressoRepository repositorio;

    public List<Ingressos> findAll() {
        return repositorio.findAll();
    }

    public Ingressos acharPorId(Long id) throws RecordNotFoundException {
        Optional<Ingressos> ingressos = repositorio.findById(id);

        if(ingressos.isPresent()) {
            return ingressos.get();
        } else {
            throw new RecordNotFoundException("Nenhum ingresso vendido.");
        }
    }

    public Ingressos criarAtualizarIngressos(Ingressos entidade) {
        if(entidade.getId() == null) {
            entidade = repositorio.save(entidade);

            return entidade;
        }
        else {
            Optional<Ingressos> ingressos = repositorio.findById(entidade.getId());

            if(ingressos.isPresent()) {
                Ingressos novaEntidade = ingressos.get();
                novaEntidade.setComprador(entidade.getComprador());
                novaEntidade.setEvento(entidade.getEvento());

                novaEntidade = repositorio.save(novaEntidade);

                return novaEntidade;
            } else {
                entidade = repositorio.save(entidade);

                return entidade;
            }
        }
    }

    public void apagarCasa(Long id) throws RecordNotFoundException {
        Optional<Ingressos> ingressos = repositorio.findById(id);

        if(ingressos.isPresent())
        {
            repositorio.deleteById(id);
        } else {
            throw new RecordNotFoundException("Nenhum ingresso encontrado!");
        }
    }

}
