package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
