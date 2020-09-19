package com.nmw.example.springbatch.batch;

import com.nmw.example.springbatch.model.Film;
import com.nmw.example.springbatch.model.User;
import com.nmw.example.springbatch.repository.FilmRepository;
import com.nmw.example.springbatch.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmWriter implements ItemWriter<Film> {

    @Autowired
    public FilmRepository filmRepository;

    @Override
    public void write(List<? extends Film> films) throws Exception {

        filmRepository.saveAll(films);

        System.out.println("Data Saved for Films count: " + films.size());
    }
}
