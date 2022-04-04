package com.example.casadeshowapi.repository;

import com.example.casadeshowapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        User findByNome(String nome);

}