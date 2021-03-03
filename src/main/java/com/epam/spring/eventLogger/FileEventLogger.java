package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private final String fileName;
    private final File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public void init() {
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
