package ru.mirea.task19.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan()
@EnableJpaRepositories(basePackages = {"ru.mirea.task19.repos"})
public class AppConfig {
}
