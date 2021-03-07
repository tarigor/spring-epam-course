package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

    @Value("${events.file:event.txt}")
    private String fileName;

    private File file;

    public FileEventLogger(){
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;

    }

    @PostConstruct
    public void init() {
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        if (file.exists() & !file.canWrite()) {
            throw new IllegalArgumentException("There is no access tofile");
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("Can't create a file", e);
            }
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, "\n" + event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
