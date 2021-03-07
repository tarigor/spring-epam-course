package com.epam.spring.eventLogger;

import com.epam.spring.beans.Event;

import java.util.Collection;
import java.util.Collections;

public class CombinedEventLogger extends AbstractLogger {
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

    public Collection<EventLogger> getLoggers(){
        return Collections.unmodifiableCollection(loggers);
    }
}
