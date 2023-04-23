package ru.mirea.task18.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages = {
        "ru.mirea.task18.entities",
        "ru.mirea.task18.controllers",
        "ru.mirea.task18.repos",
        "ru.mirea.task18.services",
})
@EnableJpaRepositories(basePackages = {"ru.mirea.task18.repos"})
public class AppConfig {
}
