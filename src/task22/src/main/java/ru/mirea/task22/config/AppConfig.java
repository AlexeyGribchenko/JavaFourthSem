package ru.mirea.task22.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan()
@EnableJpaRepositories(basePackages = {"ru.mirea.task22.repos"})
public class AppConfig {
}
