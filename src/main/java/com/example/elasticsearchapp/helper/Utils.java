package com.example.elasticsearchapp.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;

@Slf4j
public class Utils {

    public static String loadAsString(final String path){
        try{
            final File resource = new ClassPathResource(path).getFile();
            return Files.readAllBytes(resource.toPath()).toString();
        }catch (final Exception ex){
            log.error(ex.getMessage(),ex);
            return null;
        }
    }


}
