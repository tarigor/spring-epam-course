package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

public class ConsoleEventLogger extends AbstractLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
