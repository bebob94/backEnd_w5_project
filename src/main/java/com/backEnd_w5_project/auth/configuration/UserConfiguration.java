package com.backEnd_w5_project.auth.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.backEnd_w5_project.auth.entity.User;
import com.github.javafaker.Faker;

@Configuration
@PropertySource("classpath:application.properties")
public class UserConfiguration {

    @Bean("FakeUser")
    @Scope("prototype")
    public User fakeUser() {
        Faker fake = Faker.instance(new Locale("it-IT"));
        User u = new User();
        u.setName(fake.name().firstName());
        int numRandom = (int) ((Math.random() * 100) + 1);
        u.setUsername((u.getName() + "." + numRandom));
        u.setEmail(u.getName() + "." + u.getUsername() + "@example.com");
        u.setPassword(fake.internet().password(6, 10, true, true));
        System.out.println("Utente creato");
        return u;


    }
}