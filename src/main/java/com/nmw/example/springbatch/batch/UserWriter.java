package com.nmw.example.springbatch.batch;

import com.nmw.example.springbatch.model.User;
import com.nmw.example.springbatch.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserWriter implements ItemWriter<User> {

    @Autowired
    public UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) throws Exception {

        userRepository.saveAll(users);

        System.out.println("Data Saved for Users: " + users);
    }
}
