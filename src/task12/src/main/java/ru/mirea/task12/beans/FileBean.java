package ru.mirea.task12.beans;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileBean {

    @Autowired
    ApplicationArguments applicationArguments;

    @Value("${files.path}")
    String strPath;

    @PostConstruct
    public void init() throws IOException {
        String fileData = null;

        try {
            fileData = new String(Files.readAllBytes(Path.of(strPath + applicationArguments.getSourceArgs()[0])));
            System.out.println(fileData);
        } catch (IOException ignored) {
        } finally {
            File file = new File(strPath + applicationArguments.getSourceArgs()[1]);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileData == null ? "null" : ((Integer)fileData.hashCode()).toString());
            fileWriter.flush();
            fileWriter.close();
        }
    }

    @PreDestroy
    public void destroy() {

        File file = new File(strPath + applicationArguments.getSourceArgs()[0]);

        if (file.exists()) {
            file.delete();
        }
    }
}
