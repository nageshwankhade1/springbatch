package com.nmw.example.springbatch.repository;

import com.nmw.example.springbatch.model.Film;
import com.nmw.example.springbatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Integer> {
}
