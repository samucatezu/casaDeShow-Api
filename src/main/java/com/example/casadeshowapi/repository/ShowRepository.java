package com.example.casadeshowapi.repository;

import com.example.casadeshowapi.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}
