package com.example.casadeshowapi.services;

import java.util.List;

import com.example.casadeshowapi.entities.Casa;
import com.example.casadeshowapi.repository.CasaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CasaServiceTest {

    @Autowired
    CasaRepository repositorio;

    @Mock
    CasaService service;

    @Test
    public void CadastrarUmaCasa() {

        Casa a = new Casa();
        a.setIdCasa((Long) null);
        a.setNome("Pesada");
        a.setEndereco("true");

        try {
            repositorio.save(a);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void ListarAsCasas() {

        try {
            List<Casa> casas = repositorio.findAll();
            for (Casa a : casas) {
                System.out.println("Casa: " + a.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void ListarUmaCasaPorId() {
        Long busca = (long) 17;
        try {
            System.out.println("Casa: " + repositorio.findById(busca).get().getNome());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void ListarPorIdViaService() {
        try {
            Long busca =  (long) 17;
            Mockito.when(service.acharPorId(busca));
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void ErroBuscaCasaService() throws Exception {
        Long busca =  (long) 500;
        Mockito.when(service.acharPorId(busca)).thenThrow(new Exception("Não existe esta casa"));
    }

}