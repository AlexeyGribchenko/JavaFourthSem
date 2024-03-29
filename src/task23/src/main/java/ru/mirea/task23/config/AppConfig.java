package ru.mirea.task23.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan()
@EnableJpaRepositories(basePackages = {"ru.mirea.task23.repos"})
public class AppConfig {
}
