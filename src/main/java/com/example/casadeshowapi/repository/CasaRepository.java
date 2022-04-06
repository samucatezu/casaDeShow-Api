package com.example.casadeshowapi.repository;


import com.example.casadeshowapi.entities.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {

}
