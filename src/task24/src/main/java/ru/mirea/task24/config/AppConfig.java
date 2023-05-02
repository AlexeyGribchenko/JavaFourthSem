package ru.mirea.task24.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan()
@EnableJpaRepositories(basePackages = {"ru.mirea.task24.repos"})
public class AppConfig {
}
