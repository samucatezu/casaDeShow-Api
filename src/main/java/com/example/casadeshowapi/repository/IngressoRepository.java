package com.example.casadeshowapi.repository;

import com.example.casadeshowapi.entities.Ingressos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingressos, Long>{

}
