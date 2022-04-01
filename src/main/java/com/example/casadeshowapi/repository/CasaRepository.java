package com.example.casadeshowapi.repository;

import java.util.List;

import com.example.casadeshowapi.entities.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CasaRepository extends JpaRepository<Casa, Long>{

}
