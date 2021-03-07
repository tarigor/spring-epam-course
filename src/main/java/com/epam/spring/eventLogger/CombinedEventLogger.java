package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger {

    @Autowired
    private Collection<EventLogger> loggers;

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
